package com.example.bbsuestc.recyclerViewContents.headerServices

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class HeaderServicesAdapter(private val mContext: Context?,
                            private val itemNames : ArrayList<String>) :
    RecyclerView.Adapter<HeaderServicesAdapter.LinearViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearViewHolder {
        return LinearViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_header_services,
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: LinearViewHolder, position: Int) {
        // 设置TextView的内容
        holder.textView.text = itemNames[position]
    }

    override fun getItemCount(): Int {
        // 返回列表项数量
        return itemNames.size
    }

    // 内部ViewHolder类
    inner class LinearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        init {
            textView = itemView.findViewById<TextView>(R.id.item_header_services_tv)

        }
    }
}

