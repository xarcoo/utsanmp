package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.ActivityRegisterBinding
import com.ubaya.a160421050_uts_anmp.model.User
import com.ubaya.a160421050_uts_anmp.viewmodel.UserViewModel
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel
    var accounts:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(binding.root)

        viewModel.fetchAll()
        // error di sini, karena viewmodel alluserldnya null
//        accounts = viewModel.allUserLD.value!!

        binding.btnRegister.setOnClickListener {
            var username = binding.txtUsername.text.toString()
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
                if(!username.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&!repassword.isEmpty()){
                    if(password==repassword){
                        viewModel.newUser(User(username, email, password))

                    }else{
                        Toast.makeText(this, "Password and Repassword must be the same ", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Please insert all data", Toast.LENGTH_SHORT).show()
                }
            }

            Toast.makeText(this, "Sign Up Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}