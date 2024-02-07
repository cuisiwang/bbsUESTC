package com.example.bbsuestc.homeActivity.home.homeContents.hot.headerServices

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R


class HeaderServicesAdapter(private val mContext: Context?) :
    RecyclerView.Adapter<HeaderServicesAdapter.LinearViewHolder>() {
    private val item_names : ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearViewHolder {
        return LinearViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hot_header_services,parent,false)
        )
    }

    override fun onBindViewHolder(holder: LinearViewHolder, position: Int) {
        // 设置TextView的内容
        holder.textView.text = item_names[position]
    }

    override fun getItemCount(): Int {
        // 返回列表项数量
        return 6
    }

    // 内部ViewHolder类
    inner class LinearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        init {
            textView = itemView.findViewById<TextView>(R.id.item_header_services_tv)
            item_names.apply {
                add("论坛服务")
                add("校历")
                add("服务大厅")
                add("网上报修")
                add("一卡通查询")
                add("校车时刻表")
            }
        }
    }
}

