package com.example.bbsuestc.recyclerViewContents.messageSystemContent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class MessageSystemContentAdapter(private val data:ArrayList<MessageSystemItem>,private val context:Context): RecyclerView.Adapter<MessageSystemContentAdapter.ViewHolder>() {
    lateinit var mOnItemClickListener:OnItemClickListener
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noticeDot:ImageView
        var messageTitle:TextView
        var messageContent:TextView
        var messageTime:TextView

        init {
            this.messageContent=itemView.findViewById(R.id.message_system_message_content)
            this.noticeDot=itemView.findViewById(R.id.message_system_notice_dot)
            this.messageTitle=itemView.findViewById(R.id.message_system_title)
            this.messageTime=itemView.findViewById(R.id.message_system_time)
            itemView.setOnClickListener { mOnItemClickListener.onItemClick(adapterPosition) }
        }

        public fun setData(position: Int){
            val item:MessageSystemItem=data[position]
            if(!item.isNotRead){
                noticeDot.visibility=View.GONE
            }
            else{
                noticeDot.visibility=View.VISIBLE
            }
            messageTime.text = item.messageTime
            messageTitle.text = item.messageTitle
            messageContent.text = item.messageContent
        }

    }
    public interface OnItemClickListener{
        fun onItemClick(position: Int);
    }
    public fun setOnItemClickListener(onItemClickListener:OnItemClickListener){
        this.mOnItemClickListener=onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageSystemContentAdapter.ViewHolder {
        val lf:LayoutInflater= LayoutInflater.from(context)
        val view:View=lf.inflate(R.layout.item_system_message_content,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageSystemContentAdapter.ViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return data.size;
    }

}