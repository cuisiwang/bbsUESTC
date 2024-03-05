package com.example.bbsuestc.recyclerViewContents.FriendRequestContent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R

class UntreatedRequestContentAdapter(private val data:ArrayList<UntreatedRequestItem>,private val context:Context) :
    RecyclerView.Adapter<UntreatedRequestContentAdapter.ViewHolder>() {


    public interface OnRecyclerViewItemClickListener{
        fun onOptionClick(position: Int)
        fun onConfirmClick(position: Int)
        fun onRefuseClick(position: Int)
    }
    private lateinit var mOnclickListener:OnRecyclerViewItemClickListener
    public fun setRecyclerViewItemClickListener(listener:OnRecyclerViewItemClickListener){
        mOnclickListener=listener
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var untreatedUserName:TextView
        var untreatedUserIcon:ImageView
        var untreatedOption:ImageView
        var confirmImage:ImageView
        var refuseImage:ImageView
        init{
            untreatedUserIcon=itemView.findViewById(R.id.friend_request_untreated_userIcon_iv)
            untreatedUserName=itemView.findViewById(R.id.friend_request_untreated_userName_tv)
            untreatedOption=itemView.findViewById(R.id.friend_request_untreated_option_iv)
            confirmImage=itemView.findViewById(R.id.friend_request_confirm_iv)
            refuseImage=itemView.findViewById(R.id.friend_request_refuse_iv)
        }
        public fun setData(position: Int){
            untreatedUserName.setText(data[position].untreatedRequestUserName)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View=LayoutInflater.from(context).inflate(R.layout.item_friend_request_untreated_content,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
        //设置各种监听事件
        holder.refuseImage.setOnClickListener{
            if(mOnclickListener!=null){
                mOnclickListener.onRefuseClick(position)
            }
        }
        holder.confirmImage.setOnClickListener{
            if(mOnclickListener!=null){
                mOnclickListener.onConfirmClick(position)
            }
        }
        holder.untreatedOption.setOnClickListener{
            if (mOnclickListener!=null){
                mOnclickListener.onOptionClick(position)
            }
        }
    }

}