package com.example.runescapeapp.item.item_trie.use_case

import android.content.Context

object ItemTrieUseCaseFactory {
    @JvmStatic
    fun getInstance(context: Context): ItemTrieUseCase =
        ItemTrieUseCase(context)
}