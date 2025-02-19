package com.example.a100musicalbum.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.network.KtorClient
import com.example.a100musicalbum.network.model.FeedDtoWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumViewModel : ViewModel() {
    private val _albums = MutableStateFlow(FeedDtoWrapper())
    val albums = _albums.asStateFlow()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val response: HttpResponse =
                    KtorClient.client.get("https://rss.marketingtools.apple.com/api/v2/us/music/most-played/100/albums.json")
                if (response.status.value == 200) {
                    val data = response.body<FeedDtoWrapper>()
                    _albums.value = data
                }
            } catch (e: Exception) {
                Log.e("fetchAlbums","Err in response from JSON")
            }
        }
    }
}
