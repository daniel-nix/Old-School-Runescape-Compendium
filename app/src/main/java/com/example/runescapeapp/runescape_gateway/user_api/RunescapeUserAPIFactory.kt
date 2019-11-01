package com.example.runescapeapp.runescape_gateway.user_api

object RunescapeUserAPIFactory {
    @JvmStatic
    fun getInstance(): RunescapeUserAPI =
        RunescapeUserGateway()
}