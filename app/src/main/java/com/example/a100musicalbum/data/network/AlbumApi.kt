package com.example.a100musicalbum.data.network

import com.example.a100musicalbum.data.network.dto.AlbumDto
import com.example.a100musicalbum.data.network.dto.FeedDtoWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class AlbumApi {
    suspend fun fetchAlbums(): List<AlbumDto> {
        val response: HttpResponse =
            KtorClient.client.get("https://rss.marketingtools.apple.com/api/v2/us/music/most-played/100/albums.json")
        if (response.status.value == 200) {
            return response.body<FeedDtoWrapper>().feed?.albums ?: emptyList()
        } else {
            throw Exception("Ошибка: сервер вернул код ${response.status.value}")
        }
    }
}