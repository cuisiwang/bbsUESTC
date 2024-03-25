package com.example.bbsuestc.recyclerViewContents.messageContent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class MessageContentAdapter(private val data:ArrayList<MessageItem>):
    RecyclerView.Adapter<MessageContentAdapter.innerholder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): innerholder {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.item_message_content,parent,false)
        return innerholder(view)
    }

    class innerholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var userIcon : ImageView
        var userID : TextView
        var time : TextView //时间不确定格式
        var messageContent : TextView
        var unreadCount : TextView
        init {
            userIcon=itemView.findViewById(R.id.message_icon_iv)
            userID=itemView.findViewById(R.id.message_user_id)
            time=itemView.findViewById(R.id.message_user_time)
            messageContent=itemView.findViewById(R.id.message_user_message)
            unreadCount=itemView.findViewById(R.id.message_user_read_cnt)
        }
    }

    override fun onBindViewHolder(holder: innerholder, position: Int) {

        holder.messageContent.setText(data[position].messageContent)
        holder.userID.setText(data[position].userID)
        holder.time.setText(data[position].time)
        //判断未读信息的条数，以设置合理的显示
        if(data[position].unreadCount==0){
            holder.unreadCount.visibility=View.INVISIBLE
        }
        else if(data[position].unreadCount>99){
            holder.unreadCount.visibility=View.VISIBLE;
            holder.unreadCount.text = "99+"
        }
        else{
            holder.unreadCount.visibility=View.VISIBLE;
            holder.unreadCount.text = data[position].unreadCount.toString()
        }
    }

    override fun getItemCount() = data.size
}