package com.example.runescapeapp.main_dashboard.viewmodel

import android.content.Context
import com.example.runescapeapp.item.item_trie.use_case.ItemTrieUseCaseFactory
import com.example.runescapeapp.runescape_gateway.user_api.RunescapeUserAPIFactory

object MainDashboardViewModelFactory {
    @JvmStatic
    fun getInstance(context: Context): MainDashboardViewModel =
        MainDashboardViewModel(
            context,
            RunescapeUserAPIFactory.getInstance(),
            ItemTrieUseCaseFactory.getInstance(context)
        )
}