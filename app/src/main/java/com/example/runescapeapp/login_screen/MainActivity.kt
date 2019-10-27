package com.example.runescapeapp.login_screen

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.runescapeapp.R
import com.example.runescapeapp.RunescapeGateway.RunescapeGateway
import com.example.runescapeapp.login_screen.viewmodel.LoginScreenViewModel
import com.example.runescapeapp.login_screen.viewmodel.LoginScreenViewModelFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

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
    }

    override fun onStart() {
        super.onStart()
//        val gateway = RunescapeGateway()
//        launch(Dispatchers.IO) {
//            val response = gateway.numberUsersOnline()
//            withContext(Dispatchers.Main) {
//                findViewById<TextView>(R.id.text).text = response
//            }
//        }
    }

    private fun createLoginBackground() {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.login_screen)
        val linearLayout = findViewById<LinearLayout>(R.id.login_background)
        loginScreenViewModel.createBackground(constraintLayout, linearLayout)
    }
}
