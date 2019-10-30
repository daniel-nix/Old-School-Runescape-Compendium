package com.example.runescapeapp.player

class Player(private val username: String) {
    var hiscoreMap: HashMap<String, Score>? = null
    var ironmanMap: HashMap<String, Score>? = null
    var ultimateIronmanMap: HashMap<String, Score>? = null
}