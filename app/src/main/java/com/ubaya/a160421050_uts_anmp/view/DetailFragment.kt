package com.ubaya.a160421050_uts_anmp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.databinding.FragmentDetailBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idNews:Int

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        if (arguments != null) {
            idNews = DetailFragmentArgs.fromBundle(requireArguments()).newsId

            viewModel.fetch(idNews)
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            var page = 0
            var length = it.size

//            Picasso.get().load(viewModel.detailLD.value?.get(page)?.image).into(binding.imageView)
//            binding.txtTitle.text = viewModel.detailLD.value?.get(page)?.newsTitle
//            binding.txtAuthor.text = viewModel.detailLD.value?.get(page)?.author
//            binding.txtPageTitle.text = viewModel.detailLD.value?.get(page)?.title
//            binding.txtDesc.text = viewModel.detailLD.value?.get(page)?.descr

            if (page == 0) {
                binding.btnNext.isEnabled = true
                binding.btnPrev.isEnabled = false
            } else if (page+1 == length) {
                binding.btnNext.isEnabled = false
                binding.btnPrev.isEnabled = true
            }

            binding.btnPrev.setOnClickListener {
                page -= 1

//                Picasso.get().load(viewModel.detailLD.value?.get(page)?.image).into(binding.imageView)
//                binding.txtTitle.text = viewModel.detailLD.value?.get(page)?.newsTitle
//                binding.txtAuthor.text = viewModel.detailLD.value?.get(page)?.author
//                binding.txtPageTitle.text = viewModel.detailLD.value?.get(page)?.title
//                binding.txtDesc.text = viewModel.detailLD.value?.get(page)?.descr

                if (page == 0) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = false
                } else if (page+1 == length) {
                    binding.btnNext.isEnabled = false
                    binding.btnPrev.isEnabled = true
                }
            }

            binding.btnNext.setOnClickListener {
                page += 1

//                Picasso.get().load(viewModel.detailLD.value?.get(page)?.image).into(binding.imageView)
//                binding.txtTitle.text = viewModel.detailLD.value?.get(page)?.newsTitle
//                binding.txtAuthor.text = viewModel.detailLD.value?.get(page)?.author
//                binding.txtPageTitle.text = viewModel.detailLD.value?.get(page)?.title
//                binding.txtDesc.text = viewModel.detailLD.value?.get(page)?.descr

                if (page == 0) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = false
                } else if (page+1 == length) {
                    binding.btnNext.isEnabled = false
                    binding.btnPrev.isEnabled = true
                }
            }
        })
    }
}