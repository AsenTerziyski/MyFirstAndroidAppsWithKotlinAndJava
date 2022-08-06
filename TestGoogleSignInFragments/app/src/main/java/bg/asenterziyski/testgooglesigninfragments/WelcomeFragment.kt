package bg.asenterziyski.testgooglesigninfragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import bg.asenterziyski.testgooglesigninfragments.databinding.FragmentWelcomeBinding

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
        binding.tvName.text = args.accountName
        binding.tvEmail.text = args.accountEmail
        binding.tvToken.text = args.token

        binding.buttonGoBack.setOnClickListener {
            onBack()
        }

        binding.buttonSignOut.setOnClickListener {
            signOut()
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