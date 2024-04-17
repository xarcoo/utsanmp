package com.ubaya.a160421050_uts_anmp.viewmodel

import android.app.Application
import android.preference.PreferenceManager
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
import com.ubaya.a160421050_uts_anmp.model.Page
import com.ubaya.a160421050_uts_anmp.view.MainActivity
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val detailLD = MutableLiveData<ArrayList<Page>>()
//    val detailAllLD = MutableLiveData<ArrayList<Page>>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/detail.php?id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<List<Page>>() {}.type
                val result = Gson().fromJson<List<Page>>(it, sType)

                detailLD.value = result as ArrayList<Page>

                Log.d("Detail", it)
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}