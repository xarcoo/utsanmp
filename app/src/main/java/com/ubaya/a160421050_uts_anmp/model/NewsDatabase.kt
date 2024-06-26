package com.ubaya.a160421050_uts_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.a160421050_uts_anmp.util.DB_NAME
import com.ubaya.a160421050_uts_anmp.util.MIGRATION_1_2

@Database(entities = arrayOf(User::class, News::class, Page::class), version = 2)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile private var instance: NewsDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java,
            DB_NAME)
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}