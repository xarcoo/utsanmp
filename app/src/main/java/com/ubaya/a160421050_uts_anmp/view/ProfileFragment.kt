package com.ubaya.a160421050_uts_anmp.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ubaya.a160421050_uts_anmp.databinding.FragmentProfileBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.UserViewModel

class ProfileFragment : Fragment(), ButtonLogoutListener {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val iduser = sharedPreferences.getInt("id_user", 0)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.fetch(iduser)

        binding.logoutListener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.txtUname.setText(viewModel.userLD.value?.username)

            binding.user = it
            binding.btnSave?.setOnClickListener {
                var oldPw = binding.txtOldPassword.text.toString()
                var newPw = binding.txtNewPassword.text.toString()
                var renewPw = binding.txtRePassword.text.toString()

                if (!oldPw.isEmpty()&&!newPw.isEmpty()&&!renewPw.isEmpty()) {
                    if (oldPw == binding.user!!.password.toString()) {
                        if (newPw == renewPw) {
                            binding.user!!.password = renewPw
                            viewModel.updateUser(binding.user!!)
                            Toast.makeText(activity, "User data successfully changed", Toast.LENGTH_SHORT).show()
                            binding.txtOldPassword.setText("")
                            binding.txtNewPassword.setText("")
                            binding.txtRePassword.setText("")
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

    override fun onButtonLogoutClick(v: View) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sharedPreferences.edit().putBoolean("login", false).apply()
        val logout = sharedPreferences.edit()
        logout.remove("id_user")
        logout.apply()

        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}