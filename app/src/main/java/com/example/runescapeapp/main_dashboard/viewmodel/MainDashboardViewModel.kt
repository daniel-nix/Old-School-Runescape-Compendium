package com.example.runescapeapp.main_dashboard.viewmodel

import android.content.Context
import com.example.runescapeapp.item.item_trie.use_case.ItemTrieUseCase
import com.example.runescapeapp.player.Player
import com.example.runescapeapp.runescape_gateway.user_api.RunescapeUserAPI
import kotlin.concurrent.thread

class MainDashboardViewModel(private val context: Context,
                             private val runescapeAPI: RunescapeUserAPI,
                             private val itemTrieUseCase: ItemTrieUseCase) {

    suspend fun fetchPlayer(username: String): Player = runescapeAPI.fetchPlayer(username)
    fun assembleItemTrie() {
        thread(start=true, name="ItemTreeBuilding") {
            itemTrieUseCase.buildItemTrie()
        }
    }

}