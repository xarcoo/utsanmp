package com.ubaya.a160421050_uts_anmp.model

import com.google.gson.annotations.SerializedName
import java.sql.Date

data class User(
    var id:Int,
    var username:String?,
    var fname:String?,
    var lname:String?,
    var email:String?,
    var password:String?
)

data class News(
    var id:Int?,
    var title:String?,
    var author:String?,
    var description: String?,
    var image:String?,
    var released_date:String?
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