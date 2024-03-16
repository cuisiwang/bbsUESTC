package com.example.bbsuestc.recyclerViewContents.blackListContent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class BlacklistContentAdapter(private val data:ArrayList<BlacklistItem>,private val context: Context)
    : RecyclerView.Adapter<BlacklistContentAdapter.ViewHolder>() {

    interface OnOptionClickListener{
        fun onOptionItemClick(position: Int)
    }
    private lateinit var mOnclickListener:OnOptionClickListener
    fun setOnOptionClickListener(onclickListener: OnOptionClickListener){
        mOnclickListener=onclickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var blacklistUserIcon:ImageView
        var blacklistUserName:TextView
        var blacklistOption:ImageView
        init {
            blacklistUserIcon=itemView.findViewById(R.id.blacklist_userIcon_iv)
            blacklistUserName=itemView.findViewById(R.id.blacklist_userName_tv)
            blacklistOption=itemView.findViewById(R.id.blacklist_option_iv)

        }
        public fun setData(position: Int){
            blacklistUserName.text = data[position].blacklistUserName
            //TODO:设置头像
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlacklistContentAdapter.ViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.item_blacklist_content,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: BlacklistContentAdapter.ViewHolder, position: Int) {
        holder.setData(position)
        holder.blacklistOption.setOnClickListener{
            if(mOnclickListener!=null){
                mOnclickListener.onOptionItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}