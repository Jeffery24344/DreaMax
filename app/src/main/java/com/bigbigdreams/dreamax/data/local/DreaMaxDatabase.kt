package com.bigbigdreams.dreamax.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bigbigdreams.dreamax.data.local.dao.DownloadDao
import com.bigbigdreams.dreamax.data.local.dao.GameDao
import com.bigbigdreams.dreamax.data.local.entity.DownloadEntity
import com.bigbigdreams.dreamax.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, DownloadEntity::class],
    version = 2,
    exportSchema = false
)
abstract class DreaMaxDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun downloadDao(): DownloadDao

    companion object {
        private const val DB_NAME = "dreamax.db"

        @Volatile
        private var INSTANCE: DreaMaxDatabase? = null

        fun getInstance(context: Context): DreaMaxDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): DreaMaxDatabase {
            return Room.databaseBuilder(context, DreaMaxDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
