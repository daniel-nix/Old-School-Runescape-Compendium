package com.example.runescapeapp

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // used to measure height of the device WITHOUT the nav bar //
    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lock_screen)

        constraintLayout = findViewById(R.id.lock_screen)
        val linearLayout = findViewById<LinearLayout>(R.id.lock_background)
        createBackground(linearLayout)

        val drawable = ContextCompat.getDrawable(this, R.drawable.cracked_background)
        Log.e("HEIGHT", drawable?.intrinsicHeight.toString())
        Log.e("WIDTH", drawable?.intrinsicWidth.toString())

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        Log.e("device height", displayMetrics.heightPixels.toString())
        Log.e("device width", displayMetrics.widthPixels.toString())
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    private fun createBackground(linearLayout: LinearLayout) {
        val imagePanel = getNormalBackgroundPanel()
        linearLayout.addView(imagePanel)

        // Can only find the height of the placed drawable right before it's drawn //
        val viewTreeObserver = imagePanel.viewTreeObserver
        viewTreeObserver.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                imagePanel.viewTreeObserver.removeOnPreDrawListener(this)
                val backgroundPanelHeight = imagePanel.measuredHeight
                var backgroundHeightSum = backgroundPanelHeight
                while((backgroundHeightSum + backgroundPanelHeight) < constraintLayout.measuredHeight) {
                    val bgPanel = getNormalBackgroundPanel()
                    linearLayout.addView(bgPanel)

                    backgroundHeightSum += backgroundPanelHeight
                }
                if(backgroundHeightSum < constraintLayout.measuredHeight) {
                    val bgPanel = getFinalBackgroundPanel()
                    linearLayout.addView(bgPanel)
                }
                return true
            }

        })
    }

    private fun getNormalBackgroundPanel(): ImageView {
        val backgroundPanel = ImageView(this).apply {
            adjustViewBounds = true

            this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            setImageResource(R.drawable.cracked_background)
        }

        return backgroundPanel
    }

    private fun getFinalBackgroundPanel(): ImageView {
        return ImageView(this).apply {
            scaleType = ImageView.ScaleType.FIT_XY

            this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            setImageResource(R.drawable.cracked_background)
        }
    }
}
