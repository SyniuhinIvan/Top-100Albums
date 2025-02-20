package com.example.a100musicalbum.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.network.KtorClient
import com.example.a100musicalbum.data.model.FeedDtoWrapper
import com.example.a100musicalbum.data.repository.Repository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository = Repository()) : ViewModel() {
    private val _albums = MutableStateFlow<FeedDtoWrapper?>(null)
    val albums: StateFlow<FeedDtoWrapper?> get() = _albums

    init {
        fetchAlbums()
    }
    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val data = repository.fetchAlbums()
                _albums.value = data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
