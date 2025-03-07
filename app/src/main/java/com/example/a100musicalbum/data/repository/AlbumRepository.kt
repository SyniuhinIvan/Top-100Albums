package com.example.a100musicalbum.data.repository

import android.content.Context
import com.example.a100musicalbum.data.local.AlbumDao
import com.example.a100musicalbum.data.local.AlbumEntity
import com.example.a100musicalbum.data.local.AlbumDb
import com.example.a100musicalbum.data.mapper.toAlbumEntity
import com.example.a100musicalbum.data.network.*
import com.example.a100musicalbum.data.network.dto.AlbumDto
import com.example.a100musicalbum.data.network.dto.FeedDtoWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(
    private val albumDao: AlbumDao,
    private val albumApi: AlbumApi
) {
    suspend fun fetchAlbums(): List<AlbumDto> = withContext(Dispatchers.IO) {
        try {
            val albums = albumApi.fetchAlbums()
            val entities = albums.map { it.toAlbumEntity() }
            albumDao.insertAlbums(entities)
            albums
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