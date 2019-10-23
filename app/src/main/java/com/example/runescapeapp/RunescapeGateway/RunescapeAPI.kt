package com.example.runescapeapp.RunescapeGateway

import kotlinx.coroutines.Deferred

interface RunescapeAPI {

     suspend fun numberUsersOnline(): String


}