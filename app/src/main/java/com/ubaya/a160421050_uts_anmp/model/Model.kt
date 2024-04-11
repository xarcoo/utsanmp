package com.ubaya.a160421050_uts_anmp.model

import com.google.gson.annotations.SerializedName

data class User(
    var id:String?,
    var username:String?,
    var fname:String?,
    var lname:String?,
    var email:String?,
    var password:String?
)

data class News(
    var id:String?,
    var title:String?,
    var author:String?,
    var description: String?,
    @SerializedName("news")
    var newsPage:List<String>?,
    @SerializedName("images")
    var image:String?
)