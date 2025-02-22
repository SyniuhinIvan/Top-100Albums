package com.example.a100musicalbum.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.model.Album
import com.example.a100musicalbum.domain.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: UseCase) : ViewModel() {
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> get() = _albums

    init {
        fetchAlbums()
    }
    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albumList = useCase()
                _albums.value = albumList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
