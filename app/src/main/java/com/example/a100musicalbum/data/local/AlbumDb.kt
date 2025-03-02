package com.example.a100musicalbum.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumDb : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var INSTANCE: AlbumDb? = null

        fun getDatabase(context: Context): AlbumDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDb::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
