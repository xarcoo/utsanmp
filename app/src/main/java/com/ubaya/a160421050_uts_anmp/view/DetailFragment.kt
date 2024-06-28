package com.ubaya.a160421050_uts_anmp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.databinding.FragmentDetailBinding
import com.ubaya.a160421050_uts_anmp.viewmodel.DetailViewModel

class DetailFragment : Fragment(), PageNextClickListener, PagePrevClickListener{
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
    
    var page = 0
    var length = 0

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
        binding.listenerNext = this
        binding.listenerPrev = this
        observeViewModel()


    }

    fun observeViewModel() {
        viewModel.newLD.observe(viewLifecycleOwner, Observer {
            binding.news = it
        })
        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                page = 0
                length = it.size

                for (i in it.indices) {
                    if (i == page) {
                        binding.page = it[i]
                        break
                    }
                }

                if (page+1 == 1) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = false
                } else if (page+1>1 && page+1<length) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = true
                } else if (page+1== length) {
                    binding.btnNext.isEnabled = false
                    binding.btnPrev.isEnabled = true
                }

            }
        })
    }

    override fun onPageNextClick(v: View) {
        this.page += 1

        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                length = it.size
                for (i in it.indices) {
                    if (i == page) {
                        binding.page = it[i]
                        break
                    }
                }

                if (page+1 == 1) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = false
                } else if (page+1>1 && page+1<length) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = true
                } else if (page+1== length) {
                    binding.btnNext.isEnabled = false
                    binding.btnPrev.isEnabled = true
                }

            }
        })

//                Picasso.get().load(viewModel.detailLD.value?.get(page)?.image).into(binding.imageView)
//                binding.txtTitle.text = viewModel.detailLD.value?.get(page)?.newsTitle
//                binding.txtAuthor.text = viewModel.detailLD.value?.get(page)?.author
//                binding.txtPageTitle.text = viewModel.detailLD.value?.get(page)?.title
//                binding.txtDesc.text = viewModel.detailLD.value?.get(page)?.descr

    }

    override fun onPagePrevClick(v: View) {
        page -= 1
        viewModel.detailLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                length = it.size

                for (i in it.indices) {
                    if (i == page) {
                        binding.page = it[i]
                        break
                    }
                }

                if (page+1 == 1) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = false
                } else if (page+1>1 && page+1<length) {
                    binding.btnNext.isEnabled = true
                    binding.btnPrev.isEnabled = true
                } else if (page+1== length) {
                    binding.btnNext.isEnabled = false
                    binding.btnPrev.isEnabled = true
                }

            }
        })
//        Picasso.get().load(viewModel.detailLD.value?.get(page)?.image).into(binding.imageView)
//        binding.txtTitle.text = viewModel.detailLD.value?.get(page)?.newsTitle
//        binding.txtAuthor.text = viewModel.detailLD.value?.get(page)?.author
//        binding.txtPageTitle.text = viewModel.detailLD.value?.get(page)?.title
//        binding.txtDesc.text = viewModel.detailLD.value?.get(page)?.descr

    }
}