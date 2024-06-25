package com.ubaya.a160421050_uts_anmp.model

import androidx.room.RoomDatabase


abstract class TodoDatabase:RoomDatabase() {
    annotation class Database
}