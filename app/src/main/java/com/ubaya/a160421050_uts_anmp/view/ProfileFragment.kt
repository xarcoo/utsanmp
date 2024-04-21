package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.FragmentProfileBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.UserViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnLogOut.setOnClickListener {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
            sharedPreferences.edit().putBoolean("login", false).apply()
            val logout = sharedPreferences.edit()
            logout.remove("id_user")
            logout.apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val iduser = sharedPreferences.getInt("id_user", 0)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(iduser)

        observeViewModel()
    }

    fun observeViewModel() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val iduser = sharedPreferences.getInt("id_user", 0)
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.txtUname.setText(viewModel.userLD.value?.username)
            binding.txtFname.setText(viewModel.userLD.value?.fname)
            binding.txtLname.setText(viewModel.userLD.value?.lname)

            val student = it
            binding.btnSave?.setOnClickListener {
                var fname = binding.txtFname.text.toString()
                var lname = binding.txtLname.text.toString()
                var oldPw = binding.txtOldPassword.text.toString()
                var newPw = binding.txtNewPassword.text.toString()
                var renewPw = binding.txtRePassword.text.toString()

                if (!oldPw.isEmpty()&&!newPw.isEmpty()&&!renewPw.isEmpty()) {
                    if (oldPw == student.password.toString()) {
                        if (newPw == renewPw) {
                            viewModel.updateUser(iduser, fname, lname, newPw)
                            Toast.makeText(activity, "User data successfully changed", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "New Password doesn't match with the reenter new password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(activity, "Old password doesn't match with the inputted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Please insert all data ", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}