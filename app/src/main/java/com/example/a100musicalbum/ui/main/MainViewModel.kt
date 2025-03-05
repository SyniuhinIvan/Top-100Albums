package com.example.a100musicalbum.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.network.dto.AlbumDto
import com.example.a100musicalbum.domain.LoadAlbumsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val loadAlbumsUseCase: LoadAlbumsUseCase) : ViewModel() {
    private val _albums = MutableStateFlow<List<AlbumDto>>(emptyList())
    val albums: StateFlow<List<AlbumDto>> get() = _albums

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albumList = loadAlbumsUseCase
                _albums.value = albumList
            } catch (e: Exception) {
                // TODO: ошибку надо либо показать пользователю если она есть. можно тостом или снекбаром. также лучше залогировать её Timber.e
                e.printStackTrace()
            }
        }
    }
}

