package com.example.a100musicalbum.data.repository

import android.content.Context
import com.example.a100musicalbum.data.local.AlbumDao
import com.example.a100musicalbum.data.local.AlbumEntity
import com.example.a100musicalbum.data.local.AlbumDb
import com.example.a100musicalbum.data.network.*
import com.example.a100musicalbum.data.network.dto.AlbumDto
import com.example.a100musicalbum.data.network.dto.FeedDtoWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository (private val albumDao: AlbumDao){

    /*companion object {
        *//* TODO: создание синглтона - в коин *//*
        @Volatile
        private var INSTANCE: AlbumRepository? = null

        fun getInstance(context: Context): AlbumRepository {
            return INSTANCE ?: synchronized(this) {
                val database = AlbumDb.getDatabase(context)
                AlbumRepository(database.albumDao()).also { INSTANCE = it }
            }
        }
    }*/

    suspend fun fetchAlbums(): List<AlbumDto> = withContext(Dispatchers.IO) {
        try {

            //
            val response: HttpResponse = KtorClient.client.get("https://rss.marketingtools.apple.com/api/v2/us/music/most-played/100/albums.json")
            if (response.status.value == 200) {
                val feedWrapper = response.body<FeedDtoWrapper>()
                val albums = feedWrapper.feed?.albums ?: emptyList()
                // TODO: лучше этот код, выделенный комментариями сверху и снизу, выделить в отдельный класс по аналогии с AlbumDao, только будет AlbumApi

                // TODO: маппинг лучше вынести отдельно чтобы не мешало читабельности кода в самом репозитории
                val entities = albums.map { album ->
                    AlbumEntity(
                        id = album.id,
                        name = album.name,
                        artistName = album.artistName,
                        artworkUrl = album.artworkUrl100,
                        releaseDate = album.releaseDate
                    )
                }
                albumDao.insertAlbums(entities)
                albums
            } else {
                throw Exception("${response.status.value}")
            }
        } catch (e: Exception) {
            val cachedEntities = albumDao.getAllAlbums()
            cachedEntities.map { entity ->
                AlbumDto(
                    id = entity.id,
                    name = entity.name,
                    artistName = entity.artistName,
                    artworkUrl100 = entity.artworkUrl,
                    releaseDate = entity.releaseDate,
                    artistId = "",
                    artistUrl = "",
                    genres = emptyList(),
                    kind = "",
                    url = ""
                )
            }
        }
    }
}