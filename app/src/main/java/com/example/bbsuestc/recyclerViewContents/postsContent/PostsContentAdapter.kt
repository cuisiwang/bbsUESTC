package com.example.bbsuestc.recyclerViewContents.postsContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Random
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsContentAdapter(private val data: ArrayList<PostsItem>) :
    RecyclerView.Adapter<PostsContentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //寻找view
        val userIcon: ImageView
        val userID: TextView
        val postTime: TextView
        val postTitle: TextView
        val postContent: TextView
        val postPlate: TextView
        val postViewers: TextView
        val postReplies: TextView

        init {
            userIcon = itemView.findViewById(R.id.posts_user_icon_iv)
            userID = itemView.findViewById(R.id.posts_userID_tv)
            postTime = itemView.findViewById(R.id.posts_time_tv)
            postTitle = itemView.findViewById(R.id.posts_title_tv)
            postContent = itemView.findViewById(R.id.posts_content_tv)
            postPlate = itemView.findViewById(R.id.posts_plates_tv)
            postViewers = itemView.findViewById(R.id.posts_viewers_tv)
            postReplies = itemView.findViewById(R.id.posts_replys_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_posts_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
//        holder.userIcon.setImageURI(currentItem.posterIcon)  设置icon的url
        holder.userID.text = currentItem.posterID
        holder.postTime.text = currentItem.postTime
        holder.postTitle.text = currentItem.postTitle
        holder.postContent.text = currentItem.postContent
        holder.postPlate.text = " " + currentItem.plates
        holder.postViewers.text = " " + currentItem.viewers
        holder.postReplies.text = " " + currentItem.comments
        if(currentItem.posterIcon=="1") holder.userIcon.setImageResource(R.mipmap.testpic)
        else{
            val id: Int = Random().nextInt(500) + 1
            Picasso.get().load("https://picsum.photos/id/$id/100").into(holder.userIcon)
        }
    }

    override fun getItemCount() = data.size

}