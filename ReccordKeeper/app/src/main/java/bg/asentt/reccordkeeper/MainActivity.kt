package bg.asentt.reccordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import bg.asentt.reccordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportFragmentManager.commit {
////            add(R.id.frame_content, RunningFragment())
//            replace(R.id.frame_content, RunningFragment())
//        }



        binding.buttonCycling.setOnClickListener {
            onCyclingClicked()
        }

        binding.buttonRunning.setOnClickListener {
            onRunningClicked()
        }
    }

    private fun onRunningClicked() {
        supportFragmentManager.commit {
//            add(R.id.frame_content, RunningFragment())
            replace(R.id.frame_content, RunningFragment())
        }
    }

    private fun onCyclingClicked() {
        supportFragmentManager.commit {
//            add(R.id.frame_content, CyclingFragment())
            replace(R.id.frame_content, CyclingFragment())
        }
    }
}