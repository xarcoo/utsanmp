package com.ubaya.a160421050_uts_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import java.sql.Date

@Entity
data class User(
    @ColumnInfo("username")
    var username:String?,
    @ColumnInfo("first_name")
    var fname:String?,
    @ColumnInfo("last_name")
    var lname:String?,
    @ColumnInfo("email")
    var email:String?,
    @ColumnInfo("password")
    var password:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("author"), onDelete = CASCADE)])
data class News(
    @ColumnInfo("judul")
    var title:String?,
    @ColumnInfo("deskripsi_singkat")
    var description: String?,
    @ColumnInfo("category")
    var category:String?,
    @ColumnInfo("pengarang")
    var author:Int?,
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

data class UserAndNews(
    @Embedded
    var author: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "author"
    )
    var news: List<News>
)

data class Page(
    var id:Int?,
    var newsTitle:String?,
    var author: String?,
    var title:String?,
    var descr:String?,
    var news_id:Int?,
    var image:String?
)