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
import com.ubaya.a160421050_uts_anmp.model.User
import org.json.JSONObject

class UserViewModel(application: Application, savedStateHandle: SavedStateHandle) : AndroidViewModel(application) {
    val userLD = MutableLiveData<User>()
    val TAG = "volleyTag"

    private var queue: RequestQueue? = null

    fun fetch(id:Int) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/users.php"

        val stringRequest = StringRequest(
            Request.Method.POST, url, Response.Listener<String> {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")

                    for(i in 0 until data.length()) {
                        val playObj = data.getJSONObject(i)
                        if(playObj.getInt("id")==id){
                            userLD.value = User(
                                playObj.getInt("id"),
                                playObj.getString("username"),
                                playObj.getString("fname"),
                                playObj.getString("lname"),
                                playObj.getString("email"),
                                playObj.getString("password")
                            )
                        }
                    }
                }
//                userLD.value = result as User

                Log.d("show_volley", it)
            },
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun updateUser(id:Int, fname:String, lname:String, pw:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/update_user.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url, {
                Log.d("Update Success", it)
            }, {
                Log.d("Update Failed", it.message.toString()) }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = id.toString()
                params["fname"] = fname
                params["lname"] = lname
                params["password"] = pw
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}