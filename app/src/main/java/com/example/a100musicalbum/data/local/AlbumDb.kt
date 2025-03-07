package com.example.a100musicalbum.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumDb : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}
