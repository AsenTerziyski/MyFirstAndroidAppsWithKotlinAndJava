package com.schwarzit.lovenpark.signal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.button.MaterialButton
import com.schwarzit.lovenpark.CustomBreadCrumb
import com.schwarzit.lovenpark.MapsFragment
import com.schwarzit.lovenpark.R
import com.schwarzit.lovenpark.databinding.FragmentSignalRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignalRootFragment : Fragment() {

    private var category: String = ""
    private var binding: FragmentSignalRootBinding? = null

    private val mapFragment = MapsFragment()
    private val signalCategoryFragment = SignalCategoryFragment()
    private var  customView: CustomBreadCrumb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignalRootBinding.inflate(inflater, container, false)
//        customView = binding?.signalCustom!!
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setBreadCrumbMenuClickListeners()
        displayFragment(signalCategoryFragment)
        navigateToLocation()
        navigateForward()
        customView = binding?.signalCustom
        customView?.ivNavCategoryClicked = {
            displayFragment(signalCategoryFragment)
        }
    }

    private fun navigateForward() {
        binding?.buttonForward?.setOnClickListener{
//            forwardSignalCreationStep()
            customView?.nextStep()

        }
    }

    private fun navigateToLocation() {
        signalCategoryFragment.categorySelectedListener = { aCategory: String ->
            this.category = aCategory
            Log.d("TAG", this.category)
//            displayFragment(mapFragment)
//            forwardSignalCreationStep()
            binding?.apply {
                buttonForward.isVisible = true
                tvPressAndDragPin.isVisible = true
            }
        }
    }

    private fun displayFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.contentFragment, fragment)
            addToBackStack(null)
        }
    }

    private fun disableForwardButton(button: MaterialButton) {
        button.isClickable = false
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun enableForwardButton(button: MaterialButton) {
        button.isClickable = true
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.invalid_red))
    }

//    private fun setBreadCrumbMenuClickListeners() {
//        binding?.apply {
//            ivNavCategory.setOnClickListener {
//                displayFragment(signalCategoryFragment)
//                tvPressAndDragPin.isVisible = false
//            }
//            ivNavLocation.setOnClickListener {
//                displayFragment(mapFragment)
//                tvPressAndDragPin.isVisible = true
//            }
//        }
//    }

//    private fun forwardSignalCreationStep() {
//        forwardCounter += 1
//        binding?.apply {
//
//            if (forwardCounter == 1) {
//                ivNavCategory.isClickable
//                ivNavLocation.isClickable
//                ivNavLocation.setImageResource(R.drawable.signal_ellipse)
//                lineLocationPhoto.setImageResource(R.drawable.signal_line_86)
//                ivNavPhoto.setImageResource(R.drawable.signal_elipse2)
//            }
//
//            if (forwardCounter == 2) {
//                ivNavPhoto.isClickable
//                ivNavPhoto.setImageResource(R.drawable.signal_ellipse)
//                linePhotoDescription.setImageResource(R.drawable.signal_line_86)
//                ivNavDescription.setImageResource(R.drawable.signal_elipse2)
//            }
//
//            if (forwardCounter == 3) {
//                binding?.buttonForward?.text = "TEST"
//                binding.apply {
//                    disableForwardButton(buttonForward)
//                }
//                ivNavDescription.isClickable
//                ivNavOverview.isClickable
//                ivNavDescription.setImageResource(R.drawable.signal_ellipse)
//                lineDescriptionOverview.setImageResource(R.drawable.signal_line_86)
//                ivNavOverview.setImageResource(R.drawable.signal_elipse2)
//                binding?.contentFragment?.isVisible = false
//            }
//        }
//
//        if (forwardCounter == 4) {
//            cancelSignalCreation()
//        }
//    }

//    private fun cancelSignalCreation() {
//        forwardCounter = 0
//        binding?.apply {
//            ivNavLocation.setImageResource(R.drawable.signal_elipse4)
//            lineLocationPhoto.setImageResource(R.drawable.signal_line_86_2)
//            ivNavPhoto.setImageResource(R.drawable.signal_elipse4)
//            linePhotoDescription.setImageResource(R.drawable.signal_line_86_2)
//            ivNavDescription.setImageResource(R.drawable.signal_elipse4)
//            lineDescriptionOverview.setImageResource(R.drawable.signal_line_86_2)
//            ivNavOverview.setImageResource(R.drawable.signal_elipse4)
//        }
//    }

}