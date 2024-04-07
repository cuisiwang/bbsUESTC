package com.example.bbsuestc.recyclerViewContents.messageContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class MessageContentAdapter(private val data: ArrayList<MessageItem>) :
    RecyclerView.Adapter<MessageContentAdapter.InnerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message_content, parent, false)
        return InnerHolder(view)
    }

    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userIcon: ImageView
        var userID: TextView
        var time: TextView //时间不确定格式
        var messageContent: TextView
        var unreadCount: TextView

        init {
            userIcon = itemView.findViewById(R.id.message_icon_iv)
            userID = itemView.findViewById(R.id.message_user_id)
            time = itemView.findViewById(R.id.message_user_time)
            messageContent = itemView.findViewById(R.id.message_user_message)
            unreadCount = itemView.findViewById(R.id.message_user_read_cnt)
        }
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        val currentItem = data[position]
        holder.messageContent.text = currentItem.messageContent
        holder.userID.text = currentItem.userID
        holder.time.text = currentItem.time
        //判断未读信息的条数，以设置合理的显示
        if (currentItem.unreadCount == 0) {
            holder.unreadCount.visibility = View.INVISIBLE
        } else if (currentItem.unreadCount > 99) {
            holder.unreadCount.visibility = View.VISIBLE;
            holder.unreadCount.text = "99+"
            holder.unreadCount.textSize = 10F
        } else {
            holder.unreadCount.visibility = View.VISIBLE;
            holder.unreadCount.text = currentItem.unreadCount.toString()
        }
    }

    override fun getItemCount() = data.size
}