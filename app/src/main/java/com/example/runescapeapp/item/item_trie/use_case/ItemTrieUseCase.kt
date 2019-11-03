package com.example.runescapeapp.item.item_trie.use_case

import android.content.Context
import com.example.runescapeapp.item.JsonItemParser
import com.example.runescapeapp.item.item_trie.ItemTrieBuilder
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
            val itemTrieBuilder = ItemTrieBuilder.getInstance()
            itemList.forEach {
                itemTrieBuilder.addItem(it.first, it.second)
            }
        }
    }

    fun isTrieBuilding(): Boolean = if(itemTrieBuildingJob == null) false else itemTrieBuildingJob!!.isCompleted
}