package com.ubaya.a160421050_uts_anmp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.a160421050_uts_anmp.model.NewsDatabase

val DB_NAME = "NewsDB"

fun buildDb(context: Context): NewsDatabase {
    val db = NewsDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object : Migration (1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE news ADD COLUMN image TEXT not null")
    }
}