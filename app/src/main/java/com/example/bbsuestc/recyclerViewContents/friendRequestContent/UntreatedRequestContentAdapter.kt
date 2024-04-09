package com.example.bbsuestc.recyclerViewContents.friendRequestContent

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendRequestActivity.FriendRequestActivity
import com.example.bbsuestc.friendRequestActivity.FriendRequestViewModel

class UntreatedRequestContentAdapter(private var data:ArrayList<UntreatedRequestItem>,
                                     private val context:FriendRequestActivity,
                                     private val viewModel:FriendRequestViewModel) :
    RecyclerView.Adapter<UntreatedRequestContentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var untreatedUserName:TextView
        var untreatedUserIcon:ImageView
        var untreatedOption:ImageView
        var confirmText:TextView
        var refuseText:TextView
        init{
            untreatedUserIcon=itemView.findViewById(R.id.friend_request_untreated_userIcon_iv)
            untreatedUserName=itemView.findViewById(R.id.friend_request_untreated_userName_tv)
            untreatedOption=itemView.findViewById(R.id.friend_request_untreated_option_iv)
            confirmText=itemView.findViewById(R.id.friend_request_confirm_tv)
            refuseText=itemView.findViewById(R.id.friend_request_refuse_tv)
        }
        public fun setData(position: Int){
            untreatedUserName.text = data[position].untreatedRequestUserName

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
        //点击拒绝
        holder.refuseText.setOnClickListener{
            viewModel.deny(position)
            Toast.makeText(context, "已处理", Toast.LENGTH_SHORT).show()
        }
        //点击同意
        holder.confirmText.setOnClickListener{
            viewModel.confirm(position)
            Toast.makeText(context, "已处理", Toast.LENGTH_SHORT).show()
        }
        //点击侧边栏，弹出窗口
        holder.untreatedOption.setOnClickListener{
            popMenuUntreated(position)
        }
    }

    private fun popMenuUntreated(position: Int) {
        val layoutManager: RecyclerView.LayoutManager? = context.getUntreatedRv().layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.friend_request_untreated_option_iv)
        val popupWindowLayout= LayoutInflater.from(context).inflate(R.layout.popup_window_untreated_request,context.getUntreatedRv(),false)
        val ignoreTextView : TextView = popupWindowLayout.findViewById(R.id.untreated_request_ignore_pw_tv)
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

        ignoreTextView.setOnClickListener{
            viewModel.ignoreUntreated(position)
            popupWindow.dismiss()
        }
    }
}