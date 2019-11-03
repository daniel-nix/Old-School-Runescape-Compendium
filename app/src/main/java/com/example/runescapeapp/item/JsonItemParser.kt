package com.example.runescapeapp.item

import android.content.Context
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class JsonItemParser(private val context: Context) {

    private val itemJson = "names.json"

    // returns a list of 'ItemName' and 'ItemId'
    fun parse(): List<Pair<String, Long>> {
        val itemList = ArrayList<Pair<String, Long>>(3011)

        val itemJSONArray = getJSONFromFile()
        assembleNameIdPairs(itemList, itemJSONArray)

        return itemList
    }

    private fun getJSONFromFile(): JSONArray {
        val jsonParser = JSONParser()
        val bufferedReader = context.assets.open(itemJson).bufferedReader()

        val jsonArray = jsonParser.parse(bufferedReader) as JSONArray
        bufferedReader.close()

        return jsonArray
    }

    private fun assembleNameIdPairs(itemList: ArrayList<Pair<String, Long>>, jsonArray: JSONArray) {
        var itemJSONObject: JSONObject?
        jsonArray.forEach { element ->
            element?.let {
                itemJSONObject = it as JSONObject
                // itemJSONObject instantiated above ^ //
                itemJSONObject!!["name"]?.let { name ->
                    itemJSONObject!!["id"]?.let { id ->
                        itemList.add(Pair(name as String, id as Long))
                    }
                }
            }
        }
    }
}