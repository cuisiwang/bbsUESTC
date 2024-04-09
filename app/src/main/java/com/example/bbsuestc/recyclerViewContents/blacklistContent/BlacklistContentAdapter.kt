package com.example.bbsuestc.recyclerViewContents.blackListContent

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.net.toUri
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
        private var blacklistUserIcon: ImageView
        private var blacklistUserName: TextView
        var blacklistOption: ImageView

        init {
            blacklistUserIcon = itemView.findViewById(R.id.blacklist_userIcon_iv)
            blacklistUserName = itemView.findViewById(R.id.blacklist_userName_tv)
            blacklistOption = itemView.findViewById(R.id.blacklist_option_iv)

        }
        fun setData(position: Int) {
            blacklistUserName.text = data?.get(position)?.blacklistUserName ?: "默认用户"
            //TODO:设置头像
//            blacklistUserIcon.setImageURI(data?.get(position)?.blackUserIcon?.toUri())
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
            popMenu(holder,position)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    private fun popMenu(holder:ViewHolder, position: Int) {
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_blacklist,null)
        val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val deleteTextView: TextView = popupView.findViewById(R.id.blacklist_delete_pw_tv)
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))
        // TODO: 当item在屏幕靠下位置，pw无法完全显示
        //删除操作
        deleteTextView.setOnClickListener {
            //修改数据
            viewModel.removeFromBlacklist(position)
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(holder.blacklistOption)
    }

}