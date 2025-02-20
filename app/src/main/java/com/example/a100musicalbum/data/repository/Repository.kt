package com.example.a100musicalbum.data.repository

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.model.FeedDtoWrapper
import com.example.a100musicalbum.data.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository {
    suspend fun fetchAlbums():FeedDtoWrapper = withContext(Dispatchers.IO) {
               val response:HttpResponse = KtorClient.client.get("https://rss.marketingtools.apple.com/api/v2/us/music/most-played/100/albums.json")
        if (response.status.value == 200) {
            response.body<FeedDtoWrapper>()
        } else {
            throw Exception("Ошибка: сервер вернул код ${response.status.value}")
        }
    }
}