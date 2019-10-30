package com.example.runescapeapp.main_dashboard

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.runescapeapp.main_dashboard.viewmodel.MainDashboardViewModel
import com.example.runescapeapp.main_dashboard.viewmodel.MainDashboardViewModelFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainDashboard: Activity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var mainDashboardViewModel: MainDashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDashboardViewModel = MainDashboardViewModelFactory.getInstance(applicationContext)

        setNumberOfUsersText()
    }

    private fun setNumberOfUsersText() {
        launch(Dispatchers.IO) {
            val response = mainDashboardViewModel.fetchPlayer(intent.getStringExtra("username"))
            withContext(Dispatchers.Main) {

            }
        }
    }
}