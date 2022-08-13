package bg.asenterziyski.testgooglesigninfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import bg.asenterziyski.testgooglesigninfragments.databinding.FragmentGoogleSignInBinding


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
            println()
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
        Log.d("TAG", "${account?.email}")
        Log.d("TAG", "${account?.name}")
        val action = GoogleSignInFragmentDirections.actionGoogleSignInFragmentToWelcomeFragment(
            account?.name,
            account?.email,
            account?.id
        )
        navController.navigate(action)
    }

}