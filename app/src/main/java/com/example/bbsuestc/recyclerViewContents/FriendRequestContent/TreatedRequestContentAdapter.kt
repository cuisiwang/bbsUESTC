package com.example.bbsuestc.recyclerViewContents.FriendRequestContent

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendRequestActivity.FriendRequestActivity
import com.example.bbsuestc.friendRequestActivity.FriendRequestViewModel
import com.example.bbsuestc.recyclerViewContents.FriendContent.FriendContentAdapter

class TreatedRequestContentAdapter(private var data:ArrayList<TreatedRequestItem>,private val context:FriendRequestActivity,private val viewModel: FriendRequestViewModel) : RecyclerView.Adapter<TreatedRequestContentAdapter.ViewHolder>(){


    public interface OnRecyclerViewClickListener{
        fun onOptionClick(position: Int)
    }
    private lateinit var mOnClickListener:OnRecyclerViewClickListener
    public fun setOnRecyclerViewClickListener(listener:OnRecyclerViewClickListener){
        mOnClickListener=listener
    }
    public fun updateData(data:ArrayList<TreatedRequestItem>){
        this.data=data
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
    //初始化点击事件监听器
    public fun initListener(){
        this.setOnRecyclerViewClickListener(object :TreatedRequestContentAdapter.OnRecyclerViewClickListener{
            //弹出侧边栏
            override fun onOptionClick(position: Int) {
                popMenuTreated(position)
            }

        })
    }
    private fun popMenuTreated(position: Int) {
        val layoutManager: RecyclerView.LayoutManager? = context.getTreatedRequestRv().layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.friend_request_treated_option_iv)
        val popupWindowLayout= LayoutInflater.from(context).inflate(R.layout.popup_window_treated_request,context.getTreatedRequestRv(),false)
        val deleteTextView : TextView = popupWindowLayout.findViewById(R.id.treated_request_delete_pw_tv)
        val popupWindow= PopupWindow(context)
        popupWindow.isOutsideTouchable=true
        popupWindow.isFocusable=true
        popupWindow.contentView=popupWindowLayout
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))

        if(position!=data.size-1) {
            popupWindow.showAsDropDown(optionView)
        }
        else{
            popupWindow.showAsDropDown(optionView,0,-200)
        }

        //删除操作
        deleteTextView.setOnClickListener{
            viewModel.deleteTreated(position)
            popupWindow.dismiss()
        }
    }
}