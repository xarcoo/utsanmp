package com.ubaya.a160421050_uts_anmp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160421050_uts_anmp.databinding.NewsItemBinding
import com.ubaya.a160421050_uts_anmp.model.News
import com.ubaya.a160421050_uts_anmp.model.User
import java.lang.Exception

class HomeAdapter(val newsList:ArrayList<News>, val userList:ArrayList<User>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(var binding: NewsItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(newsList[position].image).into(holder.binding.imageView)

        holder.binding.txtTitle.text = newsList[position].title
        val idUser = newsList[position].author;
        var authorName = ""
        for (user in userList) {
            if (user.id == idUser) {
                authorName = user.username!!
                break
            }
        }
        holder.binding.txtAuthor.text = authorName
        holder.binding.txtDesc.text = newsList[position].description

        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionDetail(newsList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateNewsList(newNewsList:List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }
    fun updateUserList(newUserList:List<User>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
}