package com.example.a100musicalbum.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a100musicalbum.data.model.Album
import com.example.a100musicalbum.domain.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// TODO: более понятное название для useCase придумай
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
                // TODO: ошибку надо либо показать пользователю если она есть. можно тостом или снекбаром. также лучше залогировать её Timber.e
                e.printStackTrace()
            }
        }
    }
}

// TODO: заюзай автоформатирование текста через хоткей тут