package com.example.bbsuestc.homeActivity.home.homeContents.hot.postsContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.home.homeContents.hot.headerServices.HeaderServicesAdapter

class PostsContentAdapter(private val data : List<PostsItem>) : RecyclerView.Adapter<PostsContentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //寻找view00
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_header_services,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
    }

    override fun getItemCount() = data.size

}