package com.example.runescapeapp.login_screen

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.runescapeapp.R
import com.example.runescapeapp.login_screen.viewmodel.LoginScreenViewModel
import com.example.runescapeapp.login_screen.viewmodel.LoginScreenViewModelFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import android.widget.EditText
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import com.example.runescapeapp.main_dashboard.MainDashboard


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var loginScreenViewModel: LoginScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        loginScreenViewModel = LoginScreenViewModelFactory.getInstance(applicationContext)

        createLoginBackground()
        setNumberOfUsersText()
        setSubmitListener()
    }

    private fun createLoginBackground() {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.login_screen)
        val linearLayout = findViewById<LinearLayout>(R.id.login_background)
        loginScreenViewModel.createBackground(constraintLayout, linearLayout)
    }

    private fun setNumberOfUsersText() {
        launch(Dispatchers.IO) {
            val response = loginScreenViewModel.fetchNumberOnlinePlayers()
            withContext(Dispatchers.Main) {
                val numUsersBox = findViewById<View>(R.id.login_overlay).findViewById<TextView>(R.id.num_users)
                numUsersBox.text = getString(R.string.number_of_users_online, response)
            }
        }
    }

    private fun setSubmitListener() {
        val submitButton = findViewById<View>(R.id.login_overlay).findViewById<TextView>(R.id.submit_button)
        val textBox = findViewById<View>(R.id.login_overlay).findViewById<TextView>(R.id.username_text)
        submitButton.setOnClickListener { view: View? ->
            val intent = Intent(this, MainDashboard::class.java)
            intent.putExtra("username", textBox.text.toString())
            startActivity(intent)
        }
    }

    // This is to close the keyboard and un-focus when the text box is clicked off //
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}
