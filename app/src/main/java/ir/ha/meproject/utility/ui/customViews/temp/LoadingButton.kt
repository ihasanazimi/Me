package ir.ha.meproject.utility.ui.customViews.temp

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import ir.ha.meproject.R
import ir.ha.meproject.databinding.LayoutLoadingButtonBinding

class LoadingButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private val TAG = LoadingButton::class.java.simpleName
    private var binding: LayoutLoadingButtonBinding
    private var buttonTitle: String
    private var loadingTitle: String

    init {
        // Inflate the layout
        binding = LayoutLoadingButtonBinding.inflate(LayoutInflater.from(context), this, true)

        // Make sure the custom view itself respects width/height passed from the layout
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        this.layoutParams = layoutParams

        // Load attributes
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingButton,
            0, 0
        ).apply {
            try {
                // Title and Loading Title
                buttonTitle = getString(R.styleable.LoadingButton_loadingBtnTitle) ?: "Confirm/OK"
                loadingTitle = getString(R.styleable.LoadingButton_loadingBtnTitle) ?: "Please Wait ..."

                // Background Tint
                val backgroundColor = getResourceId(R.styleable.LoadingButton_loadingBtnTBackgroundColor, -1)
                if (backgroundColor != -1) {
                    backgroundTint(backgroundColor)
                }

                // Text Color
                val textColor = getResourceId(R.styleable.LoadingButton_loadingBtnTextColor, -1)
                if (textColor != -1) {
                    textColor(textColor)
                }

                // Icon
                val iconResId = getResourceId(R.styleable.LoadingButton_loadingBtnIcon, -1)
                if (iconResId != -1) {
                    binding.btn.setIconResource(iconResId)
                }

                // Icon Tint
                val iconTint = getResourceId(R.styleable.LoadingButton_loadingBtnIconTint, -1)
                if (iconTint != -1) {
                    binding.btn.iconTint = ContextCompat.getColorStateList(context, iconTint)
                }

                // Icon Size
                val iconSize = getDimension(R.styleable.LoadingButton_loadingBtnIconSize, -1f)
                if (iconSize != -1f) {
                    binding.btn.iconSize = iconSize.toInt()
                }

                // Icon Padding
                val iconPadding = getDimension(R.styleable.LoadingButton_loadingBtnIconPadding, -1f)
                if (iconPadding != -1f) {
                    binding.btn.iconPadding = iconPadding.toInt()
                }

                // Button Gravity
                val buttonGravity = getInt(R.styleable.LoadingButton_loadingBtnGravity, 17)
                binding.btn.gravity = buttonGravity

                // Text Gravity
                val textGravity = getInt(R.styleable.LoadingButton_loadingBtnTextGravity, 17)
                binding.btn.textAlignment = textGravity

                // Icon Gravity
                val iconGravity = getInt(R.styleable.LoadingButton_loadingBtnGravity, 3)
                if (iconGravity == 3) {
                    binding.btn.iconGravity = MaterialButton.ICON_GRAVITY_START
                } else {
                    binding.btn.iconGravity = MaterialButton.ICON_GRAVITY_END
                }

                // Set initial text
                binding.btn.text = buttonTitle

            } finally {
                recycle()
            }


        }
    }

    // Methods to update attributes programmatically
    fun title(buttonTitle: String? = null, loadingTitle: String? = null) {
        this.buttonTitle = buttonTitle ?: this.buttonTitle
        this.loadingTitle = loadingTitle ?: this.loadingTitle
        binding.btn.text = this.buttonTitle
    }

    fun backgroundTint(color: Int) {
        binding.btn.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
    }

    fun textColor(color: Int) {
        binding.btn.setTextColor(ContextCompat.getColor(context, color))
    }

    fun showLoading(show: Boolean) {
        if (show) {
            binding.btn.text = loadingTitle
            binding.btn.isEnabled = false
        } else {
            binding.btn.text = buttonTitle
            binding.btn.isEnabled = true
        }
    }

    fun clickListener(callback: (() -> Unit)? = null) {
        binding.btn.setOnClickListener { callback?.invoke() }
    }
}
