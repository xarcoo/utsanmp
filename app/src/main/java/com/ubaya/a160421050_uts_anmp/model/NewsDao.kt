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

    @Query("SELECT * FROM news ORDER BY id")
    fun selectAllNews(): List<News>

    @Query("SELECT * FROM news WHERE id = :id")
    fun selectNews(id:Int): News

    @Update()
    fun updateNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Insert
    fun newUser(user: User)

    @Query("SELECT * FROM users ORDER BY id")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM users WHERE id = :id")
    fun selectUser(id:Int): User

    @Update()
    fun updateUser(user: User)

    @Query("SELECT * FROM pages WHERE news_id = :id")
    fun selectDetail(id:Int): List<Page>
    @Query("SELECT news.title, news.description, news.category, news.image, users.username as author " +
            "FROM news " +
            "INNER JOIN users ON news.author = users.id " +
            "WHERE news.id = :id")
    fun selectNewDetail(id:Int): New
}