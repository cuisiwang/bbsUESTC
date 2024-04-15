package com.example.bbsuestc.recyclerViewContents.postsRelativeContent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.postsRelativeActivity.PostsRelativeActivity

class PostsRelativeContentAdapter(private val data:ArrayList<PostsRelativeItem>,private val context:PostsRelativeActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //自定义viewType
    private val TYPE_REPLY=1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? =null
        // 根据 viewType 创建不同类型的 ViewHolder，其余view样式暂不明确
        if(viewType==TYPE_REPLY){
            holder=ReplyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_posts_relative_reply,parent,false))
            return holder
        }
        //返回缺省类型，一般不会出现这种情况
        holder=ReplyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_posts_relative_reply,parent,false))
        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type=getItemViewType(position)
        when(type){
            TYPE_REPLY->{
                (holder as ReplyViewHolder).setData(position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item=data[position]
        when(item){
            is PostsRelativeItem.PostsRelativeReplyItem -> {
                return TYPE_REPLY
            }
        }
    }
    //回复类型的viewholder
    inner class ReplyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userIcon: ImageView
        val option:ImageView
        val userName: TextView
        val daysBefore: TextView
        val postName: TextView
        val replyContent: TextView
        val likeCount: TextView
        init{
            userIcon=itemView.findViewById(R.id.posts_relative_reply_user_icon)
            option=itemView.findViewById(R.id.posts_relative_reply_option)
            userName=itemView.findViewById(R.id.posts_relative_reply_user_name)
            daysBefore=itemView.findViewById(R.id.posts_relative_reply_time)
            postName=itemView.findViewById(R.id.posts_relative_reply_post_name)
            replyContent=itemView.findViewById(R.id.posts_relative_reply_post_content)
            likeCount=itemView.findViewById(R.id.posts_relative_reply_like)
        }

        fun setData(position: Int){
            val dataItem=data[position] as PostsRelativeItem.PostsRelativeReplyItem
            userName.text=dataItem.userName
            daysBefore.text=dataItem.daysBefore.toString()+"天前"
            postName.text="回复了你的帖子"+dataItem.postName
            replyContent.text=dataItem.replyContent
            likeCount.text=dataItem.likeCount.toString()
            //头像未设置
        }
    }

}