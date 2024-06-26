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
import com.ubaya.a160421050_uts_anmp.model.NewsDatabase
import com.ubaya.a160421050_uts_anmp.model.Page
import com.ubaya.a160421050_uts_anmp.model.User
import com.ubaya.a160421050_uts_anmp.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val detailLD = MutableLiveData<List<Page>>()
    val userLD = MutableLiveData<List<User>>()
    private var job = Job()

    fun fetch(id:Int) {
        launch {
            val db = NewsDatabase.buildDatabase(getApplication())
            detailLD.postValue(db.newsDao().selectDetail(id))
            userLD.postValue(db.newsDao().selectAllUser())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}