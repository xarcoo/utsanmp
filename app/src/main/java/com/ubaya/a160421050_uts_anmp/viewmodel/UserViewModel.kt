package com.ubaya.a160421050_uts_anmp.viewmodel

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160421050_uts_anmp.model.NewsDatabase
import com.ubaya.a160421050_uts_anmp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val allUserLD = MutableLiveData<List<User>>()
    val userLD = MutableLiveData<User>()
    private var job = Job()

    fun fetchAll() {
        launch {
            val db = NewsDatabase.buildDatabase(getApplication())
            allUserLD.postValue(db.newsDao().selectAllUser())
        }
    }

    fun fetch(id:Int) {
        launch {
            val db = NewsDatabase.buildDatabase(getApplication())
            userLD.postValue(db.newsDao().selectUser(id))
        }
    }

    fun newUser(user: User) {
        launch {
            val db = NewsDatabase.buildDatabase(getApplication())
            db.newsDao().newUser(user)
        }
    }

    fun updateUser(user: User) {
        launch {
            val db = NewsDatabase.buildDatabase(getApplication())
            db.newsDao().updateUser(user)
            userLD.postValue(db.newsDao().selectUser(user.id))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}