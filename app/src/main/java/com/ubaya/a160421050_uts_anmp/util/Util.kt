package com.ubaya.a160421050_uts_anmp.util

import android.content.Context

val DB_NAME = "NewsDB"

fun buildDb(context: Context): TodoDatabase {
    val db = TodoDatabase.buildDatabase(context)
    return db
}