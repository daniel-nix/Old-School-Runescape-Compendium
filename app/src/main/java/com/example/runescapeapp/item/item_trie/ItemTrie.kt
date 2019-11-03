package com.example.runescapeapp.item.item_trie

class ItemTrie {

    // there is no need for multiple tries for runescape items //
    companion object {
        private var trie: ItemTrie? = null

        fun getInstance(): ItemTrie {
            if(trie == null)
                synchronized(ItemTrie::class) {
                    // just in case another thread didn't synchronize before current one //
                    if(trie == null)
                        trie = ItemTrie()
                }
            return trie!!
        }
    }

    private val itemTrie = ItemTrieNode()

    fun addItem(itemName: String, itemId: Long) {
        var trieNode = itemTrie
        for(character in itemName) {
            with(character.toLowerCase()) {
                if(!trieNode.children.containsKey(this))
                    trieNode.children[this] = ItemTrieNode()
                trieNode = trieNode.children[this]!!
            }
        }
        trieNode.apply {
            endOfName = true
            this.itemId = itemId
        }
    }

    fun getItem(itemName: String): Long {
        var trieNode = itemTrie
        for(character in itemName) {
            with(character.toLowerCase()) {
                if(!trieNode.children.containsKey(this))
                    return -1
                trieNode = trieNode.children[this]!!
            }
        }
        if(trieNode.endOfName)
            return trieNode.itemId
        return -1
    }

    fun getAlikeNames(partialName: String): List<Pair<String, Long>> {
        val nameList = ArrayList<Pair<String, Long>>()
        var trieNode = itemTrie
         for(character in partialName) {
            with(character.toLowerCase()) {
                if(!trieNode.children.containsKey(this))
                    return nameList
                trieNode = trieNode.children[this]!!
            }
        } // go through all of the characters of partial name to start at the end
        displayTreeToList(trieNode, partialName, nameList)
        return nameList
    }

    private fun displayTreeToList(trieNode: ItemTrieNode, nameString: String, nameList: ArrayList<Pair<String, Long>>) {
        if(trieNode.endOfName) {
            nameList.add(Pair(nameString, trieNode.itemId))
        }
        for(child in trieNode.children) {
            displayTreeToList(trieNode.children[child.key]!!, nameString + child.key, nameList)
        }
    }
}