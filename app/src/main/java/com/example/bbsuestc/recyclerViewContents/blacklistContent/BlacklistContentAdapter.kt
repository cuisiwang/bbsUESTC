package com.example.bbsuestc.recyclerViewContents.blackListContent

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.blackListActivity.BlackListViewModel
import com.example.bbsuestc.blackListActivity.BlacklistActivity

class BlackListContentAdapter(
    private val data: ArrayList<BlackListItems>?,
    private val context: BlacklistActivity,
    private val viewModel: BlackListViewModel
) : RecyclerView.Adapter<BlackListContentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var blacklistUserIcon: ImageView
        var blacklistUserName: TextView
        var blacklistOption: ImageView

        init {
            blacklistUserIcon = itemView.findViewById(R.id.blacklist_userIcon_iv)
            blacklistUserName = itemView.findViewById(R.id.blacklist_userName_tv)
            blacklistOption = itemView.findViewById(R.id.blacklist_option_iv)

        }

        public fun setData(position: Int) {
            blacklistUserName.text = data?.get(position)?.blacklistUserName ?: "默认用户"
            //TODO:设置头像
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_blacklist_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)
        holder.blacklistOption.setOnClickListener {
            popMenu(position)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    //渲染一个在点击项目附近的弹窗，内含删除操作
    private fun popMenu(position: Int) {
        //获取item view
        val layoutManager: RecyclerView.LayoutManager? = context.getBlacklistRV().layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.blacklist_option_iv)
        val popupWindowLayout = LayoutInflater.from(context)
            .inflate(R.layout.popup_window_blacklist, context.getBlacklistRV(), false)
        val deleteTextView: TextView = popupWindowLayout.findViewById(R.id.blacklist_delete_pw_tv)
        val popupWindow = PopupWindow(context)
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.contentView = popupWindowLayout
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))

        if (position != (data?.size ?: -1) - 1) {
            popupWindow.showAsDropDown(optionView)
        } else {
            popupWindow.showAsDropDown(optionView, 0, -200)
        }

        //删除操作
        deleteTextView.setOnClickListener {
            //修改数据
            viewModel.removeFromBlacklist(position)
            popupWindow.dismiss()
        }

    }

}