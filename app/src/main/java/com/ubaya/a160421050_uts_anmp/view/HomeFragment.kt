package com.ubaya.a160421050_uts_anmp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.FragmentHomeBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private val homeListAdapter = HomeAdapter(arrayListOf(), arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = homeListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            homeListAdapter.updateNewsList(it)
        })
    }
}