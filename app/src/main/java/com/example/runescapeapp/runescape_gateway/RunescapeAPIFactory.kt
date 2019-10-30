package com.example.runescapeapp.runescape_gateway

object RunescapeAPIFactory {
    @JvmStatic
    fun getInstance(): RunescapeAPI =
        RunescapeGateway()
}