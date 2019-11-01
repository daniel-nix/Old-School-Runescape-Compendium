package com.example.runescapeapp.runescape_gateway.item_api

object RunescapeItemAPIFactory {
    @JvmStatic
    fun getInstance(): RunescapeItemGateway =
        RunescapeItemGateway()
}