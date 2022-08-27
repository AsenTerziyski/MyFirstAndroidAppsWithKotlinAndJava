package bg.asenterziyski.testgooglesigninfragments

import android.R.attr.data
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import bg.asenterziyski.testgooglesigninfragments.databinding.FragmentGoogleSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class GoogleSignInFragment : Fragment() {

    private lateinit var binding: FragmentGoogleSignInBinding
    private lateinit var navController: NavController

    private var googleSignInRepo: GoogleSignInRepository? = null
    private val resultLauncher = activityResultLauncher()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        googleSignInRepo = GoogleSignInRepository(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (googleSignInRepo?.hasLastSignInAccount(requireContext()) == true) {
            navigateToWelcomeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewSignIn.setOnClickListener {
            signInWithGoogle()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoogleSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun signInWithGoogle() {
        val intent = googleSignInRepo?.getSignInIntent()
        resultLauncher.launch(intent)
    }

    private fun activityResultLauncher() =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                navigateToWelcomeFragment()
            }
        }

    private fun navigateToWelcomeFragment() {
        val context = context ?: return
        navController = findNavController()
        NavigationUI.setupActionBarWithNavController(context as AppCompatActivity, navController)
        val account = googleSignInRepo?.getAccount(context)

        Log.d("TAG", "Name: ${account?.name}")
        Log.d("TAG", "Email: ${account?.email}")
        Log.d("TAG", "Id: ${account?.id}")
        Log.d("TAG", "IdToken: ${account?.idToken}")
        Log.d("TAG", "Expired: ${account?.expired}")
        Log.d("TAG", "AccountType: ${account?.accountType}")
        Log.d("TAG", "ServerAuthCode: ${account?.serverAuthCode}")
        Log.d("TAG", "PhotoUrl: ${account?.photoUrl}")

        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(googleSignInRepo?.getSignInIntent())
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            val authCode = account.serverAuthCode
            Log.d("TAG", "ServerAuthCode: ${account?.serverAuthCode}")
            // TODO: send code to server and exchange for access/refresh/ID tokens
        } catch (e: ApiException) {
            Log.d("TAG", "ApiException: $e")
        }


        val action: NavDirections = account?.let {
            GoogleSignInFragmentDirections.actionGoogleSignInFragmentToWelcomeFragment(
                it.name,
                it.email,
                it.id,
                it.idToken,
                it.expired,
                it.accountType,
                it.serverAuthCode,
                it.photoUrl
            )
        }
            ?: GoogleSignInFragmentDirections.actionGoogleSignInFragmentToWelcomeFragment(
                "N/A",
                "N/A",
                "N/A",
                "N/A",
                true,
                "N/A",
                "N/A",
                "N/A"
            )
        navController.navigate(action)
    }

}