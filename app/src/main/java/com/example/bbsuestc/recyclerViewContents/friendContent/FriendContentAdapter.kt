package com.example.bbsuestc.recyclerViewContents.friendContent

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendActivity.FriendActivity
import com.example.bbsuestc.friendActivity.FriendViewModel

class FriendContentAdapter(
    private val data: ArrayList<FriendItem>,
    private val context: FriendActivity,
    private val viewModel: FriendViewModel
) :
    RecyclerView.Adapter<FriendContentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userIcon: ImageView
        val userName: TextView
        val option: ImageView

        init {
            userIcon = itemView.findViewById(R.id.friend_userIcon_iv)
            userName = itemView.findViewById(R.id.friend_userName_tv)
            option = itemView.findViewById(R.id.friend_options_iv)
        }

        fun setData(position: Int) {
//            userIcon.setImageURI(data[position].userIcon.toUri())
            userName.text = data[position].userName
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendContentAdapter.ViewHolder {
        val view: View = View.inflate(parent.context, R.layout.item_friend_content, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendContentAdapter.ViewHolder, position: Int) {
        holder.setData(position)
        holder.option.setOnClickListener {
            popMenu(holder, position)
        }
    }

    private fun popMenu(holder: ViewHolder, position: Int) {
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_window_friend, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val deleteTv: TextView = popupView.findViewById(R.id.friend_delete_pw_tv)
        val blackListTv: TextView = popupView.findViewById(R.id.friend_blacklist_pw_tv)
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))
        // TODO: 当item在屏幕靠下位置，pw无法完全显示
        // TODO: 实现点击功能 
        deleteTv.setOnClickListener {
            //修改数据
//            viewModel.removeFromBlacklist(position)
            popupWindow.dismiss()
        }

        blackListTv.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.showAsDropDown(holder.option)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}