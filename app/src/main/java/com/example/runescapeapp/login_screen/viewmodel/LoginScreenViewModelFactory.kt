package com.example.runescapeapp.login_screen.viewmodel

import android.content.Context
import com.example.runescapeapp.runescape_gateway.RunescapeAPIFactory

object LoginScreenViewModelFactory {
    @JvmStatic
    fun getInstance(context: Context): LoginScreenViewModel =
        LoginScreenViewModel(context,
            RunescapeAPIFactory.getInstance())
}