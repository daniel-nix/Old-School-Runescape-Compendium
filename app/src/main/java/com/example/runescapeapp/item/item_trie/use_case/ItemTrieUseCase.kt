package com.example.runescapeapp.item.item_trie.use_case

import android.content.Context
import android.util.Log
import com.example.runescapeapp.item.JsonItemParser
import com.example.runescapeapp.item.item_trie.ItemTrie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ItemTrieUseCase(private val context: Context): CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var itemTrieBuildingJob: Job? = null

    fun buildItemTrie() {
        itemTrieBuildingJob = launch(Dispatchers.IO) {
            val itemList = JsonItemParser(context).parse()
            val itemTrie = ItemTrie.getInstance()
            itemList.forEach {
                itemTrie.addItem(it.first, it.second)
            }
        }
    }

    fun isTrieBuilding(): Boolean = itemTrieBuildingJob?.isCompleted ?: false
}