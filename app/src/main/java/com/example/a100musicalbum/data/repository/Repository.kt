package com.example.a100musicalbum.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.local.AlbumDao
import com.example.a100musicalbum.data.local.AlbumEntity
import com.example.a100musicalbum.data.local.AppDatabase
import com.example.a100musicalbum.data.model.Album
import com.example.a100musicalbum.data.model.FeedDtoWrapper
import com.example.a100musicalbum.data.network.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Repository (private val albumDao: AlbumDao){

    companion object {
        // Синглтон для репозитория
        @Volatile private var INSTANCE: Repository? = null

        fun getInstance(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val database = AppDatabase.getDatabase(context)
                Repository(database.albumDao()).also { INSTANCE = it }
            }
        }
    }

    // Получение данных из сети с обновлением базы или кеширование при ошибке
    suspend fun fetchAlbums(): List<Album> = withContext(Dispatchers.IO) {
        try {
            val response: HttpResponse = KtorClient.client.get("https://rss.marketingtools.apple.com/api/v2/us/music/most-played/100/albums.json")
            if (response.status.value == 200) {
                val feedWrapper = response.body<FeedDtoWrapper>()
                // Преобразуем данные в список альбомов
                val albums = feedWrapper.feed?.albums ?: emptyList()
                // Сохраняем в базу (предварительно преобразуем в AlbumEntity)
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
                throw Exception("Ошибка: сервер вернул код ${response.status.value}")
            }
        } catch (e: Exception) {
            // При ошибке получения данных из сети – возвращаем данные из локальной базы
            val cachedEntities = albumDao.getAllAlbums()
            // Преобразуем из AlbumEntity в Result (зависит от того, как устроены ваши модели)
            cachedEntities.map { entity ->
                Album(
                    id = entity.id,
                    name = entity.name,
                    artistName = entity.artistName,
                    artworkUrl100 = entity.artworkUrl,
                    releaseDate = entity.releaseDate,
                    // Заполните остальные поля, если они требуются, или оставьте пустыми
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