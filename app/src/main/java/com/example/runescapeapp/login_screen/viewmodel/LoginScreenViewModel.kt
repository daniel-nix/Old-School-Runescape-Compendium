package com.example.runescapeapp.login_screen.viewmodel

import android.content.Context
import android.view.ViewGroup
import com.example.runescapeapp.runescape_gateway.RunescapeAPI
import com.example.runescapeapp.login_screen.builder.BackgroundBuilder

class LoginScreenViewModel(private val context: Context,
                           private val runescapeAPI: RunescapeAPI) {

    fun createBackground(screenContainerLayout: ViewGroup, backgroundContainerLayout: ViewGroup) {
        BackgroundBuilder(
            screenContainerLayout,
            backgroundContainerLayout,
            context
        ).build()
    }

    suspend fun fetchNumberOnlinePlayers(): Int {
        return runescapeAPI.numberUsersOnline()
    }
}