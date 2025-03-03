package com.example.a100musicalbum.domain

import com.example.a100musicalbum.data.repository.Repository
import com.example.a100musicalbum.data.model.Album

/* TODO: придумай более понятное название */
class UseCase(private val repository: Repository) {

    suspend operator fun invoke(): List<Album> {
        return repository.fetchAlbums()
    }
}
