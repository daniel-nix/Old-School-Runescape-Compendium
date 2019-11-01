package com.example.runescapeapp.runescape_gateway.item_api

import com.example.runescapeapp.item.ItemResource

interface RunescapeItemAPI {

    suspend fun fetchItemDetails(itemId: Long): ItemResource

}