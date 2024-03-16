package com.example.bbsuestc.blackListActivity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlacklistContentAdapter
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlacklistItem

class BlackListActivity : AppCompatActivity() {
    //返回图标
    lateinit var blacklistBackIcon:ImageView
    lateinit var blacklistRecyclerView:RecyclerView
    //数据
    lateinit var blacklistList:ArrayList<BlacklistItem>
    lateinit var blacklistRvAdapter: BlacklistContentAdapter
    lateinit var blacklistViewModel: BlackListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blacklist)
        blacklistBackIcon=findViewById(R.id.blacklist_back_iv)
        blacklistRecyclerView=findViewById(R.id.blacklist_rv)

        blacklistViewModel= ViewModelProvider(this)[BlackListViewModel::class.java]

        initData()
    }

    private fun initData() {

        blacklistList= blacklistViewModel.blacklistContent

        blacklistRvAdapter= BlacklistContentAdapter(blacklistList,this)
        blacklistRvAdapter.setOnOptionClickListener(object :BlacklistContentAdapter.OnOptionClickListener{
            override fun onOptionItemClick(position: Int) {
//                println(position)
//                Log.d("ff",position.toString())
                popMenu(position)
            }
        })
        blacklistBackIcon.setOnClickListener{
            finish()
        }
        blacklistRecyclerView.adapter=blacklistRvAdapter
        blacklistRecyclerView.layoutManager=LinearLayoutManager(this)
    }
    //弹出菜单
    private fun popMenu(position:Int) {
        //获取item view
        val layoutManager: RecyclerView.LayoutManager? = blacklistRecyclerView.layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.blacklist_option_iv)
        val popupWindowLayout=LayoutInflater.from(this).inflate(R.layout.popup_window_blacklist,blacklistRecyclerView,false)
        val deleteTextView : TextView = popupWindowLayout.findViewById(R.id.blacklist_delete_pw_tv)
        val popupWindow=PopupWindow(this)
        popupWindow.isOutsideTouchable=true
        popupWindow.isFocusable=true
        popupWindow.contentView=popupWindowLayout
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))

        if(position!=blacklistList.size-1) {
            popupWindow.showAsDropDown(optionView)
        }
        else{
            popupWindow.showAsDropDown(optionView,0,-200)
        }

        //删除操作
        deleteTextView.setOnClickListener{
            blacklistList.removeAt(position)
            blacklistRvAdapter.notifyItemRemoved(position)
            popupWindow.dismiss()
        }

    }
}