package com.example.runescapeapp.main_dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.runescapeapp.main_dashboard.viewmodel.MainDashboardViewModel
import com.example.runescapeapp.main_dashboard.viewmodel.MainDashboardViewModelFactory
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.example.runescapeapp.R

class MainDashboard: AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var mainDashboardViewModel: MainDashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDashboardViewModel = MainDashboardViewModelFactory.getInstance(applicationContext)
        setContentView(R.layout.dashboard)

        mainDashboardViewModel.assembleItemTrie()
        setSupportActionBar(findViewById(R.id.dashboard_toolbar))
        setNumberOfUsersText()
    }

    private fun setNumberOfUsersText() {
        launch(Dispatchers.IO) {
            intent.getStringExtra("username")?.let {username ->
                val response = mainDashboardViewModel.fetchPlayer(username)
                withContext(Dispatchers.Main) {

                }
            }
        }
    }
}