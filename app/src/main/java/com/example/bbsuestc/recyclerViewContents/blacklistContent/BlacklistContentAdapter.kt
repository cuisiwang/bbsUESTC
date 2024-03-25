package com.example.bbsuestc.recyclerViewContents.blacklistContent

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.blacklistActivity.BlacklistActivity
import com.example.bbsuestc.blacklistActivity.BlacklistViewModel

class BlacklistContentAdapter(private var data:ArrayList<BlacklistItem>,private val context: BlacklistActivity,private val viewModel: BlacklistViewModel) : RecyclerView.Adapter<BlacklistContentAdapter.ViewHolder>() {

    //可用于重置数据
    private fun updateData(data:ArrayList<BlacklistItem>){
        this.data=data
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var blacklistUserIcon:ImageView
        private var blacklistUserName:TextView
        var blacklistOption:ImageView
        init {
            blacklistUserIcon=itemView.findViewById(R.id.blacklist_userIcon_iv)
            blacklistUserName=itemView.findViewById(R.id.blacklist_userName_tv)
            blacklistOption=itemView.findViewById(R.id.blacklist_option_iv)

        }
        public fun setData(position: Int){
            blacklistUserName.setText(data[position].blacklistUserName)
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
        //点击侧边栏弹窗
        holder.blacklistOption.setOnClickListener{
            popMenu(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    private fun popMenu(position:Int) {
        //获取item view
        val layoutManager: RecyclerView.LayoutManager? = context.getBlacklistRV().layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.blacklist_option_iv)
        val popupWindowLayout=LayoutInflater.from(context).inflate(R.layout.popup_window_blacklist,context.getBlacklistRV(),false)
        val deleteTextView : TextView = popupWindowLayout.findViewById(R.id.blacklist_delete_pw_tv)
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
            //修改数据
            viewModel.removeFromBlacklist(position)
            popupWindow.dismiss()
        }

    }

}