package com.example.runescapeapp.runescape_gateway.user_api

import com.example.runescapeapp.constants.Constant
import com.example.runescapeapp.player.Player
import com.example.runescapeapp.player.Score

interface RunescapeUserAPI {

     suspend fun numberUsersOnline(): Int
     suspend fun fetchPlayer(username: String): Player
     suspend fun fetchScoreType(scoreType: Constant.ScoreType): HashMap<String, Score>

}