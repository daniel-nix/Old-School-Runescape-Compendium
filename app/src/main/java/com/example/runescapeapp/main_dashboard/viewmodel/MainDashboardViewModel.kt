package com.example.runescapeapp.main_dashboard.viewmodel

import android.content.Context
import com.example.runescapeapp.player.Player
import com.example.runescapeapp.runescape_gateway.RunescapeAPI

class MainDashboardViewModel(private val context: Context,
                             private val runescapeAPI: RunescapeAPI) {

    suspend fun fetchPlayer(username: String): Player = runescapeAPI.fetchPlayer(username)
}