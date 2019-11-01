package com.example.runescapeapp.main_dashboard.viewmodel

import android.content.Context
import com.example.runescapeapp.runescape_gateway.user_api.RunescapeUserAPIFactory

object MainDashboardViewModelFactory {
    @JvmStatic
    fun getInstance(context: Context): MainDashboardViewModel =
        MainDashboardViewModel(context, RunescapeUserAPIFactory.getInstance())
}