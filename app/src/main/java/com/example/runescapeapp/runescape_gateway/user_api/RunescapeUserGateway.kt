package com.example.runescapeapp.runescape_gateway.user_api

import androidx.annotation.UiThread
import com.example.runescapeapp.constants.Constant
import com.example.runescapeapp.player.Player
import com.example.runescapeapp.player.Score
import com.example.runescapeapp.runescape_gateway.HttpConnection
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext

private const val URL_NUM_USERS_ONLINE = "http://www.runescape.com/player_count.js?varname=iPlayerCount&callback=jQuery000000000000000_0000000000&_=0"

class RunescapeUserGateway : RunescapeUserAPI, CoroutineScope {

    private val NUM_USERS_REGEX = Regex("""\(([0-9]+)\);""")

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @UiThread
    override suspend fun numberUsersOnline(): Int = withContext(Dispatchers.IO) {
        val connection = HttpConnection.connect(URL_NUM_USERS_ONLINE)

        val response = HttpConnection.getResponse(connection)
        connection.disconnect()

        val result = NUM_USERS_REGEX.find(response, 0)
        result?.groupValues?.get(1)?.toInt() ?: 0
    }

    override suspend fun fetchPlayer(username: String): Player =
        Player(username).apply {
            val urlEnding = "?player=$username"
            hiscoreMap = fetchScores(Constant.getUrl(Constant.ScoreType.HIGHSCORE) + urlEnding)
            ironmanMap = fetchScores(Constant.getUrl(Constant.ScoreType.IRONMAN) + urlEnding)
            ultimateIronmanMap = fetchScores(Constant.getUrl(Constant.ScoreType.ULTIMATE_IRONMAN) + urlEnding)
        }

    private suspend fun fetchScores(url: String): HashMap<String, Score> = withContext(Dispatchers.IO) {
        val connection = HttpConnection.connect(url)

        if(connection.responseCode == 200) {
            val response = HttpConnection.getResponse(connection)
            connection.disconnect()

            assembleMap(response)
        } else {
            HashMap()
        }
    }

    private fun assembleMap(response: String): HashMap<String, Score> {
        val scoreMap = HashMap<String, Score>()
        if(response.isNotEmpty()) {
            val skillTokens = response.split(" ")
            var scoreTokens: List<String>?

            for (index: Int in Constant.PLAYER_SKILLS.indices) {
                scoreTokens = skillTokens[index].split(",")
                if (scoreTokens.size == 3)
                    scoreMap[Constant.PLAYER_SKILLS[index]] = Score(
                        scoreTokens[0].toInt(),
                        scoreTokens[1].toInt(),
                        scoreTokens[2].toInt()
                    )
            }
        }

        return scoreMap
    }

    override suspend fun fetchScoreType(scoreType: Constant.ScoreType): HashMap<String, Score> =
        if(Constant.SCORE_URLS.containsKey(scoreType)) fetchScores(Constant.SCORE_URLS[scoreType]!!)
        else HashMap()

}