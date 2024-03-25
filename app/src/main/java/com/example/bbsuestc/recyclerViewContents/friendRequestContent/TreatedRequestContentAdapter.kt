package com.example.bbsuestc.recyclerViewContents.friendRequestContent

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendRequestActivity.FriendRequestActivity
import com.example.bbsuestc.friendRequestActivity.FriendRequestViewModel

class TreatedRequestContentAdapter(private var data:ArrayList<TreatedRequestItem>,private val context:FriendRequestActivity,private val viewModel: FriendRequestViewModel) : RecyclerView.Adapter<TreatedRequestContentAdapter.ViewHolder>(){


    fun updateData(data:ArrayList<TreatedRequestItem>){
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
        fun setData(position: Int){
            treatedUserName.text = data[position].treatedRequestUserName
            if(data[position].optionChosen==false){
                stateTextView.text = "已拒绝"
            }
            else{
                stateTextView.text = "已同意"
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
            //这里弹出侧边对话框
            popMenuTreated(position)
        }
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
        //避免对话框到屏幕外面去
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