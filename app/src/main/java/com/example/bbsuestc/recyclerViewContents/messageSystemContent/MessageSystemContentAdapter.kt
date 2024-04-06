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
import com.example.bbsuestc.systemMessageActivity.SystemMessageViewModel

class MessageSystemContentAdapter(
    private val data: ArrayList<MessageSystemItem>,
    private val context: Context,
    private val viewModel: SystemMessageViewModel
) : RecyclerView.Adapter<MessageSystemContentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noticeDot: ImageView
        var messageTitle: TextView
        var messageContent: TextView
        var messageTime: TextView

        init {
            this.messageContent = itemView.findViewById(R.id.message_system_message_content)
            this.noticeDot = itemView.findViewById(R.id.message_system_notice_dot)
            this.messageTitle = itemView.findViewById(R.id.message_system_title)
            this.messageTime = itemView.findViewById(R.id.message_system_time)
            //条目点击事件，跳转待实现
            itemView.setOnClickListener {
                viewModel.readMessage(bindingAdapterPosition)
            }
        }

        public fun setData(position: Int) {
            val item: MessageSystemItem = data[position]
            if (!item.isNotRead) {
                noticeDot.visibility = View.GONE
            } else {
                noticeDot.visibility = View.VISIBLE
            }
            messageTime.text = item.messageTime
            messageTitle.text = item.messageTitle
            messageContent.text = item.messageContent
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageSystemContentAdapter.ViewHolder {
        val lf: LayoutInflater = LayoutInflater.from(context)
        val view: View = lf.inflate(R.layout.item_system_message_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageSystemContentAdapter.ViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return data.size;
    }

}