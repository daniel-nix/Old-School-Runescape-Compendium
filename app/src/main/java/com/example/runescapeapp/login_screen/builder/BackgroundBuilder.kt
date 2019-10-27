package com.example.runescapeapp.login_screen.builder

import android.content.Context
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.example.runescapeapp.R

class BackgroundBuilder(private val screenView: ViewGroup,
                        private val backgroundView: ViewGroup,
                        private val context: Context) {

    private val backgroundImageId = R.drawable.cracked_background;

    fun build() {
        createBackground()
    }

    private fun createBackground() {
        val imagePanel = getNormalBackgroundPanel()
        backgroundView.addView(imagePanel)

        // Can only find the height of the placed drawable right before it's drawn //
        val viewTreeObserver = imagePanel.viewTreeObserver
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                imagePanel.viewTreeObserver.removeOnPreDrawListener(this)
                val backgroundPanelHeight = imagePanel.measuredHeight
                var backgroundHeightSum = backgroundPanelHeight
                while ((backgroundHeightSum + backgroundPanelHeight) < screenView.measuredHeight) {
                    val bgPanel = getNormalBackgroundPanel()
                    backgroundView.addView(bgPanel)

                    backgroundHeightSum += backgroundPanelHeight
                }
                if (backgroundHeightSum < screenView.measuredHeight) {
                    val bgPanel = getFinalBackgroundPanel()
                    backgroundView.addView(bgPanel)
                }
                return true
            }

        })
    }

    private fun getNormalBackgroundPanel(): ImageView =
        ImageView(context).apply {
            adjustViewBounds = true

            this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            setImageResource(backgroundImageId)
        }

    private fun getFinalBackgroundPanel(): ImageView =
        ImageView(context).apply {
            scaleType = ImageView.ScaleType.FIT_XY

            this.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            setImageResource(backgroundImageId)
        }
}