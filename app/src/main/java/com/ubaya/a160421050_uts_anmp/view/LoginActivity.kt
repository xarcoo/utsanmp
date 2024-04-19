package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.a160421050_uts_anmp.databinding.ActivityLoginBinding
import com.ubaya.a160421050_uts_anmp.model.User
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val accounts:ArrayList<User> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isLogin = sharedPreferences.getBoolean("login", false)
        if(isLogin){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(binding.root)
        }

        val q = Volley.newRequestQueue(this@LoginActivity)
        val url = "http://10.0.2.2/ANMP/users.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url, {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val playObj = data.getJSONObject(i)
                        val users = User(
                            playObj.getInt("id"),
                            playObj.getString("username"),
                            playObj.getString("fname"),
                            playObj.getString("lname"),
                            playObj.getString("email"),
                            playObj.getString("password")
                        )
                        accounts.add(users)
                    }
                }
                Log.d("cekisiarray", accounts.toString())
            },
            {
                Log.e("apiresult", it.message.toString())
            })
        q.add(stringRequest)

        binding.btnLogin.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var password = binding.txtPassword.text.toString()
            var status = false

            if (username.isEmpty()||password.isEmpty()){
                Toast.makeText(this,"Data cannot be empty", Toast.LENGTH_SHORT).show()
            }else{
                for (account in accounts) {
                    if (account.username == username && account.password==password) {
                        status = true
                        Toast.makeText(this,"${username} Sign In Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        var idAccount = account.id
                        sharedPreferences.edit().putBoolean("login", true).apply()
                        sharedPreferences.edit().putInt("id_user", idAccount).apply()
                        startActivity(intent)
                        finish()
                        break
                    }
                    else{
                        status = false
                    }
                }
                if (!status){
                    Toast.makeText(this,"Username or password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}