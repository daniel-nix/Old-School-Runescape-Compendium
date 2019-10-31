package com.example.runescapeapp.item.item_trie

class ItemTrie {
    val children: HashMap<Char, ItemTrie> = HashMap()

    var endOfName: Boolean = false
    // this number will correspond to a real item id if endOfName == true //
    var itemId: Long = -1

}