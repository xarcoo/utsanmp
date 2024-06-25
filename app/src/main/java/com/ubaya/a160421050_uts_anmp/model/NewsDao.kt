package com.ubaya.a160421050_uts_anmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: News)

    @Query("SELECT * FROM news WHERE id = :id")
    fun selectNews(id:Int): News

    @Update()
    fun updateNews(news: News)

    @Delete
    fun deleteNews(news: News)
}