package com.example.a100musicalbum.di

import androidx.room.Room
import com.example.a100musicalbum.data.local.AlbumDao
import com.example.a100musicalbum.data.local.AlbumDb
import com.example.a100musicalbum.data.network.AlbumApi
import com.example.a100musicalbum.data.repository.AlbumRepository
import com.example.a100musicalbum.domain.LoadAlbumsUseCase
import com.example.a100musicalbum.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AlbumDao> {
        val database = get<AlbumDb>()
        database.albumDao()
    }
    single<AlbumDb> {
        Room.databaseBuilder(
            androidContext(),
            AlbumDb::class.java,
            "album_database"
        ).build()
    }
    single { AlbumApi() }
    single { AlbumRepository(get(), get()) }

    single { LoadAlbumsUseCase(albumRepository = get()) }
    viewModel {
        MainViewModel(
            loadAlbumsUseCase = get()
        )
    }
}
