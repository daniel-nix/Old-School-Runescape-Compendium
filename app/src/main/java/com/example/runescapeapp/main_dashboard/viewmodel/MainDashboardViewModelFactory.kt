package com.example.runescapeapp.main_dashboard.viewmodel

import android.content.Context
import com.example.runescapeapp.runescape_gateway.RunescapeAPIFactory

object MainDashboardViewModelFactory {
    @JvmStatic
    fun getInstance(context: Context): MainDashboardViewModel =
        MainDashboardViewModel(context, RunescapeAPIFactory.getInstance())
}