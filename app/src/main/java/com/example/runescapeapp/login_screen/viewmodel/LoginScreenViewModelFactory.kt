package com.example.runescapeapp.login_screen.viewmodel

import android.content.Context
import com.example.runescapeapp.runescape_gateway.user_api.RunescapeUserAPIFactory

object LoginScreenViewModelFactory {
    @JvmStatic
    fun getInstance(context: Context): LoginScreenViewModel =
        LoginScreenViewModel(context,
            RunescapeUserAPIFactory.getInstance())
}