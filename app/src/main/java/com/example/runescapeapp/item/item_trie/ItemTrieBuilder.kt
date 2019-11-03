package com.example.runescapeapp.item.item_trie

class ItemTrieBuilder {

    // there is no need for multiple tries for runescape items //
    companion object {
        private var builder: ItemTrieBuilder? = null

        fun getInstance(): ItemTrieBuilder {
            if(builder == null)
                synchronized(ItemTrieBuilder::class) {
                    // just in case another thread didn't synchronize before current one //
                    if(builder == null)
                        builder = ItemTrieBuilder()
                }
            return builder!!
        }
    }

    private val itemTrie = ItemTrie()

    fun addItem(itemName: String, itemId: Long) {
        var trieNode = itemTrie
        for(character in itemName) {
            with(character.toLowerCase()) {
                if(!trieNode.children.containsKey(this))
                    trieNode.children[this] = ItemTrie()
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

    private fun displayTreeToList(trieNode: ItemTrie, nameString: String, nameList: ArrayList<Pair<String, Long>>) {
        if(trieNode.endOfName) {
            nameList.add(Pair(nameString, trieNode.itemId))
        }
        for(child in trieNode.children) {
            displayTreeToList(trieNode.children[child.key]!!, nameString + child.key, nameList)
        }
    }
}