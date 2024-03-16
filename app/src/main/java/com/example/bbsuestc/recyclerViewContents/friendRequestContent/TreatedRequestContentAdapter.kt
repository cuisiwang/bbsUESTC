package com.example.bbsuestc.recyclerViewContents.friendRequestContent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class TreatedRequestContentAdapter(private val data:ArrayList<TreatedRequestItem>,private val context:Context) : RecyclerView.Adapter<TreatedRequestContentAdapter.ViewHolder>(){


    public interface OnRecyclerViewClickListener{
        fun onOptionClick(position: Int)
    }
    private lateinit var mOnClickListener:OnRecyclerViewClickListener
    public fun setOnRecyclerViewClickListener(listener:OnRecyclerViewClickListener){
        mOnClickListener=listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var treatedUserName: TextView
        var treatedUserIcon: ImageView
        //侧边菜单
        var treatedOption: ImageView
        //显示已同意，或者已拒绝
        var stateTextView:TextView

        init{
            treatedUserIcon=itemView.findViewById(R.id.friend_request_treated_userIcon_iv)
            treatedUserName=itemView.findViewById(R.id.friend_request_treated_userName_tv)
            treatedOption=itemView.findViewById(R.id.friend_request_treated_option_iv)
            stateTextView=itemView.findViewById(R.id.friend_request_option_chose_tv)
        }
        public fun setData(position: Int){
            treatedUserName.setText(data[position].treatedRequestUserName)
            if(data[position].optionChosen==false){
                stateTextView.setText("已拒绝")
            }
            else{
                stateTextView.setText("已同意")
            }
            //TODO:设置头像
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.item_friend_request_treated_content,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
        holder.treatedOption.setOnClickListener{
            if(mOnClickListener!=null){
                mOnClickListener.onOptionClick(position)
            }
        }
    }

}