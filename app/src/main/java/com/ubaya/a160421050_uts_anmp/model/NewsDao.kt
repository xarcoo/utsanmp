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
    fun insertNews(vararg news: News)

    @Query("SELECT * FROM news ORDER BY id DESC")
    fun selectAllNews(): List<News>

    @Query("SELECT * FROM news WHERE id = :id")
    fun selectNews(id:Int): News

    @Update
    fun updateNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Query("SELECT * FROM user WHERE id = :id")
    fun selectUser(id:Int): User

    @Update
    fun updateUser(user: User)
}