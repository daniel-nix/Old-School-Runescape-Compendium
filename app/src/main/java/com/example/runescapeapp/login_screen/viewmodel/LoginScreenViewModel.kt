package com.example.runescapeapp.login_screen.viewmodel

import android.content.Context
import android.view.ViewGroup
import com.example.runescapeapp.login_screen.builder.BackgroundBuilder

class LoginScreenViewModel(private val context: Context) {

    fun createBackground(screenContainerLayout: ViewGroup, backgroundContainerLayout: ViewGroup) {
        BackgroundBuilder(
            screenContainerLayout,
            backgroundContainerLayout,
            context
        ).build()
    }
}