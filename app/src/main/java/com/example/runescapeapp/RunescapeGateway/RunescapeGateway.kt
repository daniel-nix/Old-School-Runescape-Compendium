package com.example.runescapeapp.RunescapeGateway

import android.util.Log
import androidx.annotation.UiThread
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

private const val URL_NUM_USERS_ONLINE = "http://www.runescape.com/player_count.js?varname=iPlayerCount&callback=jQuery000000000000000_0000000000&_=0"
class RunescapeGateway : RunescapeAPI, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @UiThread
    override suspend fun numberUsersOnline(): String = withContext(Dispatchers.IO) {
        val connection = URL(URL_NUM_USERS_ONLINE).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")

        val response = getResponse(connection)
        connection.disconnect()

        response
    }

    private fun getResponse(connection: HttpURLConnection): String {
        val readIn = BufferedReader(InputStreamReader(connection.inputStream))
        val response = StringBuilder()
        var line = readIn.readLine()
        while (line != null) {
            response.append(line)
            line = readIn.readLine()
        }
        return response.toString()
    }

}