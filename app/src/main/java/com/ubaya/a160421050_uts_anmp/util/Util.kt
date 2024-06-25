package com.ubaya.a160421050_uts_anmp.util

import android.content.Context
import com.ubaya.a160421050_uts_anmp.model.NewsDatabase

val DB_NAME = "NewsDB"

fun buildDb(context: Context): NewsDatabase {
    val db = NewsDatabase.buildDatabase(context)
    return db
}