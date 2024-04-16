package com.ubaya.a160421050_uts_anmp.view

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.R
import com.ubaya.a160421050_uts_anmp.databinding.FragmentDetailBinding
import com.ubaya.a160421050_uts_anmp.model.News
import com.ubaya.a160421050_uts_anmp.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    private val detailListAdapter = DetailAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idNews:String
        val listNews:ArrayList<News>
        var page = 0
        var i = 0

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if (arguments != null) {
            idNews = DetailFragmentArgs.fromBundle(requireArguments()).newsId
            page = DetailFragmentArgs.fromBundle(requireArguments()).page

            viewModel.fetch(idNews)

            binding.recViewDetail.layoutManager = LinearLayoutManager(context)
            binding.recViewDetail.adapter = detailListAdapter
        }

        observeViewModel()
    }

//    ini perlu biar bisa muncul
    fun observeViewModel() {
        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            detailListAdapter.updatePagesList(it)
        })
    }
}