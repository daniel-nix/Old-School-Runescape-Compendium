package com.example.runescapeapp.runescape_gateway.item_api

import com.example.runescapeapp.constants.Constant
import com.example.runescapeapp.item.ItemResource
import com.example.runescapeapp.item.Trend
import com.example.runescapeapp.runescape_gateway.HttpConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import kotlin.coroutines.CoroutineContext

class RunescapeItemGateway: RunescapeItemAPI, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override suspend fun fetchItemDetails(itemId: Long): ItemResource = withContext(Dispatchers.IO) {
        val connection = HttpConnection.connect(Constant.GE_ITEM_DETAIL + itemId)
        val response = HttpConnection.getResponse(connection)

        assembleItemResource(response)
    }

    private fun assembleItemResource(itemResponse: String): ItemResource {
        val jsonParser = JSONParser()
        val itemJSON: JSONObject = (jsonParser.parse(itemResponse) as JSONObject)["item"] as JSONObject

        return ItemResource(itemJSON["icon"] as String, itemJSON["icon_large"] as String,
            itemJSON["id"] as Long, itemJSON["name"] as String, itemJSON["description"] as String,
            getOneDayTrend(itemJSON["current"] as JSONObject),
            getOneDayTrend(itemJSON["today"] as JSONObject),
            getQuarterlyTrends(itemJSON["day30"] as JSONObject, itemJSON["day90"] as JSONObject, itemJSON["day180"] as JSONObject)
        )
    }

    private fun getOneDayTrend(itemJSON: JSONObject): Trend =
        Trend(trend = itemJSON["trend"] as String, price = itemJSON["price"].toString())

    private fun getQuarterlyTrends(thirtyDayTrend: JSONObject, ninetyDayTrend: JSONObject, hundredEightyDayTrend: JSONObject): Triple<Trend, Trend, Trend> =
        Triple(Trend(trend = thirtyDayTrend["trend"] as String, change = thirtyDayTrend["change"].toString()),
            Trend(trend = ninetyDayTrend["trend"] as String, change = ninetyDayTrend["change"].toString()),
            Trend(trend = hundredEightyDayTrend["trend"] as String, change = hundredEightyDayTrend["change"].toString()))
}