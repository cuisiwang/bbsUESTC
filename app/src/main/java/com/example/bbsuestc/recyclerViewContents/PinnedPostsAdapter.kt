package com.example.bbsuestc.recyclerViewContents

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.plateDetailActivity.PinnedPost
import kotlin.math.log

class PinnedPostsAdapter(private val posts: ArrayList<PinnedPost>) : RecyclerView.Adapter<PinnedPostsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val postTitleTextView: TextView
        init {
            postTitleTextView = itemView.findViewById(R.id.post_title)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pinned_post,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = posts[position]
        holder.postTitleTextView.text = currentPost.title
        Log.d("RV","成功binding")
    }

    override fun getItemCount() = posts.size
}