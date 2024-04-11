package com.ubaya.a160421050_uts_anmp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}