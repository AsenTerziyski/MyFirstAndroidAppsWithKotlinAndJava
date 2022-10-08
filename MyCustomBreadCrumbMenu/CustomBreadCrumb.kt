package com.schwarzit.lovenpark

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class CustomBreadCrumb @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var stepCounter = 0
    private val ivNavCategory: ImageView
    private val ivNavLocation: ImageView
    private val lineLocationPhoto: ImageView
    private val ivNavPhoto: ImageView
    private val linePhotoDescription: ImageView
    private val ivNavDescription: ImageView
    private val ivNavOverview: ImageView
    private val lineDescriptionOverview: ImageView

    var ivNavCategoryClicked: (() -> Unit)? = null
    var ivNavLocationClicked: (() -> Unit)? = null
    var ivNavPhotoClicked: (() -> Unit)? = null
    var ivNavDescriptionClicked: (() -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.myconstrainglayout, this, false)
        val set = ConstraintSet()
        addView(view)
        set.clone(this)
        set.match(view, this)

        ivNavCategory = findViewById(R.id.iv_nav_category)
        ivNavLocation = findViewById(R.id.iv_nav_location)
        lineLocationPhoto = findViewById(R.id.line_location_photo)
        ivNavPhoto = findViewById(R.id.iv_nav_photo)
        linePhotoDescription = findViewById(R.id.line_photo_description)
        ivNavDescription = findViewById(R.id.iv_nav_description)
        ivNavOverview = findViewById(R.id.iv_nav_overview)
        lineDescriptionOverview = findViewById(R.id.line_description_overview)

        ivNavCategory.setOnClickListener {
            ivNavCategoryClicked?.invoke()
        }

        ivNavLocation.setOnClickListener {
            ivNavLocationClicked?.invoke()
        }

        ivNavPhoto.setOnClickListener {
            ivNavPhotoClicked?.invoke()
        }

        ivNavDescription.setOnClickListener {
            ivNavDescriptionClicked?.invoke()
        }

    }

    fun cancelSignal() {
        stepCounter = 0
        ivNavLocation.setImageResource(R.drawable.signal_elipse4)
        ivNavLocation.setImageResource(R.drawable.signal_elipse2)
        ivNavPhoto.setImageResource(R.drawable.signal_elipse4)
        ivNavDescription.setImageResource(R.drawable.signal_elipse4)
        ivNavOverview.setImageResource(R.drawable.signal_elipse4)

        lineLocationPhoto.setImageResource(R.drawable.signal_line_86_2)
        linePhotoDescription.setImageResource(R.drawable.signal_line_86_2)
        lineDescriptionOverview.setImageResource(R.drawable.signal_line_86_2)
    }

    fun nextStep() {
        stepCounter += 1

        when (stepCounter) {
            1 -> {
                ivNavCategory.isClickable
                ivNavLocation.isClickable
                ivNavLocation.setImageResource(R.drawable.signal_ellipse)
                lineLocationPhoto.setImageResource(R.drawable.signal_line_86)
                ivNavPhoto.setImageResource(R.drawable.signal_elipse2)
            }

            2 -> {
                ivNavPhoto.isClickable
                ivNavPhoto.setImageResource(R.drawable.signal_ellipse)
                linePhotoDescription.setImageResource(R.drawable.signal_line_86)
                ivNavDescription.setImageResource(R.drawable.signal_elipse2)
            }

            3 -> {
                ivNavDescription.isClickable
                ivNavOverview.isClickable
                ivNavDescription.setImageResource(R.drawable.signal_ellipse)
                lineDescriptionOverview.setImageResource(R.drawable.signal_line_86)
                ivNavOverview.setImageResource(R.drawable.signal_elipse2)
            }

            4 -> {
                cancelSignal()
            }
        }

    }
}

fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}