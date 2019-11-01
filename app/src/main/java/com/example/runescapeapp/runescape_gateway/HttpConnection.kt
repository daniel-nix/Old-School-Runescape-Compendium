package com.example.runescapeapp.runescape_gateway

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object HttpConnection {

    fun connect(url: String, requestMethod: String = "GET"): HttpURLConnection =
        (URL(url).openConnection() as HttpURLConnection).apply {
            this.requestMethod = requestMethod
            setRequestProperty("User-Agent", "Mozilla/5.0")
        }

    fun getResponse(connection: HttpURLConnection): String {
        val readIn = BufferedReader(InputStreamReader(connection.inputStream))
        val response = StringBuilder()
        var line = readIn.readLine()
        var first = true
        while (line != null) {
            if(first) {
                response.append(line)
                first = !first
            } else {
                response.append(" $line")
            }
            line = readIn.readLine()
        }
        readIn.close()
        return response.toString()
    }
}