package com.ubaya.a160421050_uts_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160421050_uts_anmp.model.News
import com.ubaya.a160421050_uts_anmp.model.User
import org.json.JSONObject

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val newsLD = MutableLiveData<ArrayList<News>>()
    val news:ArrayList<News> = ArrayList()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/basketball.php"

        val stringRequest = StringRequest(
            Request.Method.POST, url, Response.Listener<String> {
                val sType = object : TypeToken<List<News>>() {}.type
                val result = Gson().fromJson<List<News>>(it, sType)

                newsLD.value = result as ArrayList<News>

                Log.d("Home", it)
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}