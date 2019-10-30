package com.example.runescapeapp.runescape_gateway

import com.example.runescapeapp.constants.Constant
import com.example.runescapeapp.player.Player
import com.example.runescapeapp.player.Score

interface RunescapeAPI {

     suspend fun numberUsersOnline(): Int
     suspend fun fetchPlayer(username: String): Player
     suspend fun fetchScoreType(scoreType: Constant.ScoreType): HashMap<String, Score>

}