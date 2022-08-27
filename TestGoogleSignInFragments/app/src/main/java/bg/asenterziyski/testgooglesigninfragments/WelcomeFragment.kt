package bg.asenterziyski.testgooglesigninfragments

import android.R.attr.data
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import bg.asenterziyski.testgooglesigninfragments.databinding.FragmentWelcomeBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


//https://basaransuleyman.medium.com/android-activity-lifecycle-and-fragment-lifecycle-states-and-method-descriptions-136efc3c2ff3
class WelcomeFragment : Fragment() {

    private val args: WelcomeFragmentArgs by navArgs()
    private lateinit var binding: FragmentWelcomeBinding

    private var googleSignInRepo: GoogleSignInRepository? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        googleSignInRepo = GoogleSignInRepository(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvName.text = args.accountName
//            Log.d("TAG", "AccountName: ${args.accountName}")
            tvEmail.text = args.accountEmail
//            Log.d("TAG", "AccountEmail: ${args.accountEmail}")
            tvToken.text = args.idToken
//            Log.d("TAG", "idToken: ${args.idToken}")
            tvAccountType.text = args.accountType
//            Log.d("TAG", "AccountType ${args.accountType}")
            tvExpired.text = args.expired.toString()
//            Log.d("TAG", "Expired: ${args.expired}")
            tvId.text = args.id.toString()
//            Log.d("TAG", "Id: ${args.id}")
            tvPhotoUrl.text = args.photoUrl.toString()
//            Log.d("TAG", "Photo url: ${args.photoUrl}")
            tvServerAuthType.text = args.serverAuthCode.toString()
//            Log.d("TAG", "Server auth type: ${args.serverAuthCode}")


            buttonGoBack.setOnClickListener {
                onBack()
            }

            buttonSignOut.setOnClickListener {
                signOut()
            }

            buttonGoBack.setOnClickListener {
                onBack()
            }

            buttonSignOut.setOnClickListener {
                signOut()
            }

        }

    }

    private fun signOut() {
        googleSignInRepo?.signOut()
            ?.addOnCompleteListener {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
    }

    private fun onBack() {
        activity?.onBackPressed()
    }

}