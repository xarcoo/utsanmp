package com.ubaya.a160421050_uts_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.databinding.DetailItemBinding
import com.ubaya.a160421050_uts_anmp.model.News
import com.ubaya.a160421050_uts_anmp.model.Page

class DetailAdapter(val pages:ArrayList<Page>): RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    class DetailViewHolder(var binding: DetailItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        var page = 0
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(pages[position].image).into(holder.binding.imageView)

        holder.binding.txtTitle.text = pages[position].newsTitle
        holder.binding.txtAuthor.text = pages[position].author
        holder.binding.txtPageTitle.text = pages[position].title
        holder.binding.txtDesc.text = pages[position].descr

        if (page == 0) {
            holder.binding.btnNext.isEnabled = true
            holder.binding.btnPrev.isEnabled = false
        } else if (page == itemCount) {
            holder.binding.btnNext.isEnabled = false
            holder.binding.btnPrev.isEnabled = true
        }

        holder.binding.btnPrev.setOnClickListener {
            val action = DetailFragmentDirections.actionPage(pages[position].id.toString(), page)
            page -= 1
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.btnNext.setOnClickListener {
            val action = DetailFragmentDirections.actionPage(pages[position].id.toString(), page)
            page += 1
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updatePagesList(newNewsList:ArrayList<Page>) {
        pages.clear()
        pages.addAll(newNewsList)
        notifyDataSetChanged()
    }

}