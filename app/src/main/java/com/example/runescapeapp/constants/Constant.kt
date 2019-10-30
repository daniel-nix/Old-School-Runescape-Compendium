package com.example.runescapeapp.constants

object Constant {
    val PLAYER_SKILLS = arrayOf(
        "attack", "defense", "strength", "hitpoints",
        "ranged", "prayer", "magic", "cooking", "woodcutting",
        "fletching", "fishing", "firemaking", "crafting",
        "smithing", "mining", "herblore", "agility", "thieving",
        "slayer", "farming", "runecrafting", "hunter",
        "construction"
    )

    val BASE_URL = "https://services.runescape.com"

    val SCORE_URLS = hashMapOf(
        ScoreType.HIGHSCORE to "/m=hiscore_oldschool/index_lite.ws",
        ScoreType.IRONMAN to "/m=hiscore_oldschool_ironman/index_lite.ws",
        ScoreType.ULTIMATE_IRONMAN to "/m=hiscore_oldschool_ultimate/index_lite.ws",
        ScoreType.DEADMAN to "/m=hiscore_oldschool_deadman/index_lite.ws",
        ScoreType.ULTIMATE to "/m=hiscore_oldschool_ultimate/index_lite.ws",
        ScoreType.SEASONAL to "/m=hiscore_oldschool_seasonal/index_lite.ws"
    )

    enum class ScoreType {
        HIGHSCORE, IRONMAN, ULTIMATE_IRONMAN,
        DEADMAN, ULTIMATE, SEASONAL
    }

    fun getUrl(scoreType: ScoreType): String = BASE_URL + SCORE_URLS[scoreType]
}