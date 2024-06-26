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
    var username:String?,
    var email:String?,
    var password:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["author"], onDelete = CASCADE)])
data class News(
    var title:String?,
    var description: String?,
    var category:String?,
    var author:Int?,
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(foreignKeys = [ForeignKey(entity = News::class, parentColumns = ["id"], childColumns = ["news_id"], onDelete = CASCADE)])
data class Page(
    var title:String?,
    var descr:String?,
    var image:String?,
    var news_id:Int?,
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}