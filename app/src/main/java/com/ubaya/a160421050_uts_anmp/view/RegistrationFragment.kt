package com.ubaya.a160421050_uts_anmp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.FragmentRegistrationBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment() {
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}