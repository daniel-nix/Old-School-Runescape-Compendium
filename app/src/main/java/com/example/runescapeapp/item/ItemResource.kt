package com.example.runescapeapp.item

data class ItemResource(val iconURL: String, val largeIconURL: String,
                        val id: Long, val name: String, val description: String,
                        val trendCurrent: Trend, val trendToday: Trend, val trendQuarterly: Triple<Trend, Trend, Trend>)

data class Trend(val trend: String, val price: String? = null, val change: String? = null)