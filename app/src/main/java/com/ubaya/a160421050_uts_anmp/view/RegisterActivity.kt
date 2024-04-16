package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.ActivityRegisterBinding
import com.ubaya.a160421050_uts_anmp.model.User
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val accounts:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val q = Volley.newRequestQueue(this@RegisterActivity)
        val url = "http://10.0.2.2/ANMP/users.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url, Response.Listener<String> {
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
            Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            })
        q.add(stringRequest)

        binding.btnRegister.setOnClickListener {
            var username = binding.txtUsername.text.toString()
            var fname = binding.txtFname.text.toString()
            var lname = binding.txtLname.text.toString()
            var email = binding.txtEmail.text.toString()
            var password = binding.txtPassword.text.toString()
            var repassword = binding.txtRePassword.text.toString()
            var usrSame=false

            for (account in accounts) {
                if (account.username == username) {
                    usrSame = true
                    break
                }
            }
            if (usrSame){
                Toast.makeText(this,"Username has been used", Toast.LENGTH_SHORT).show()
            }else{
                if(!username.isEmpty()&&!fname.isEmpty()&&!lname.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&!repassword.isEmpty()){
                    if(password==repassword){
                        val q = Volley.newRequestQueue(this@RegisterActivity)
                        val url = "http://10.0.2.2/ANMP/new_user.php"
                        val stringRequest = object : StringRequest(Request.Method.POST, url,
                            Response.Listener {
                                Log.d("cekparams", it)
                                Toast.makeText(this, "${username} Sign Up Success", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            }, Response.ErrorListener {
                                Log.d("cekparams", it.message.toString()) }) {
                            override fun getParams(): MutableMap<String, String> {
                                val params = HashMap<String, String>()
                                params["username"] = username
                                params["fname"] = fname
                                params["lname"] = lname
                                params["email"] = email
                                params["password"] = password
                                return params
                            }
                        }
                        q.add(stringRequest)

                    }else{
                        Toast.makeText(this, "Password and Repassword must be the same ", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Please insert all data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}