package com.ubaya.a160421050_uts_anmp.view

import android.view.View

interface ButtonReadClickListener{
    fun onButtonReadClick(v: View)
}
interface UsernameAuthor{
    fun getUsername(authorId:Int) :String
}