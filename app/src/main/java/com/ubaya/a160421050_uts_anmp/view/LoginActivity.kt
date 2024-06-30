package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160421050_uts_anmp.databinding.ActivityLoginBinding
import com.ubaya.a160421050_uts_anmp.model.User
import com.ubaya.a160421050_uts_anmp.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity(), ButtonLoginListener, ButtonSignUpListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel
    var accounts:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isLogin = sharedPreferences.getBoolean("login", false)
        if(isLogin){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(binding.root)
        }

        viewModel.fetchAll()

        binding.user = User("", "", "")
        binding.loginListener = this
        binding.signUpListener = this

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.allUserLD.observe(this, Observer { users ->
            users?.let {
                accounts = it as ArrayList<User>
            }
        })
    }

    override fun onButtonLoginClick(v: View) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        var username = binding.user!!.username!!
        var password = binding.user!!.password!!
        var status = false

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Data cannot be empty", Toast.LENGTH_SHORT).show()
        }else{
            for (account in accounts) {
                if (account.username == username && account.password == password) {
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

    override fun onButtonSignUpClick(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}