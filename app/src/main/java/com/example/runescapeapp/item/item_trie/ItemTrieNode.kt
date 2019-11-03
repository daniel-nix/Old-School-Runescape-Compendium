package com.example.runescapeapp.item.item_trie

class ItemTrieNode {
    val children: HashMap<Char, ItemTrieNode> = HashMap()

    var endOfName: Boolean = false
    // this number will correspond to a real item id if endOfName == true //
    var itemId: Long = -1

}