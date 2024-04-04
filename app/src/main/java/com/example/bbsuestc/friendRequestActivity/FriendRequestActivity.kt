package com.example.bbsuestc.friendRequestActivity

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
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestItem

class FriendRequestActivity : AppCompatActivity() {
    //返回按钮
    private lateinit var friendRequestBackIv:ImageView
    private lateinit var treatedRequestRv:RecyclerView
    private lateinit var treatedRequestList:ArrayList<TreatedRequestItem>
    private lateinit var untreatedRequestRv:RecyclerView
    private lateinit var untreatedRequestList:ArrayList<UntreatedRequestItem>
    private lateinit var requestViewModel:FriendRequestViewModel
    private lateinit var treatedAdapter:TreatedRequestContentAdapter
    private lateinit var untreatedAdapter:UntreatedRequestContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_request)

        friendRequestBackIv=findViewById(R.id.friend_request_back_iv)

        treatedRequestRv=findViewById(R.id.friend_request_treated_rv)
        untreatedRequestRv=findViewById(R.id.friend_request_untreated_rv)

        requestViewModel=ViewModelProvider(this).get(FriendRequestViewModel::class.java)


        //返回按钮
        friendRequestBackIv.setOnClickListener{
            finish()
        }
        initData();
    }

    private fun initData() {
        //初始化适配器和数据项
        untreatedRequestList= arrayListOf<UntreatedRequestItem>()
        treatedRequestList= arrayListOf<TreatedRequestItem>()
        untreatedRequestList=requestViewModel.untreatedRequestList
        untreatedAdapter= UntreatedRequestContentAdapter(untreatedRequestList,this)
        treatedAdapter= TreatedRequestContentAdapter(treatedRequestList,this)

        //设置各种监听事件
        //未处理列表监听
        untreatedAdapter.setRecyclerViewItemClickListener(object :UntreatedRequestContentAdapter.OnRecyclerViewItemClickListener{
            override fun onOptionClick(position: Int) {
                //点击侧边菜单，弹出窗口
                popMenuUntreated(position)
            }

            override fun onConfirmClick(position: Int) {

                //点击了同意,插入已处理列表
                treatedRequestList.add(TreatedRequestItem(untreatedRequestList[position].untreatedRequestUserIcon,untreatedRequestList[position].untreatedRequestUserName,true))
                //删除这一项
                untreatedRequestList.removeAt(position)
                untreatedAdapter.notifyItemRemoved(position)
                untreatedAdapter.notifyItemRangeChanged(position,untreatedRequestList.size)

                treatedAdapter.notifyItemInserted(treatedRequestList.size-1)
                treatedAdapter.notifyItemRangeChanged(treatedRequestList.size-1,treatedRequestList.size)

            }

            override fun onRefuseClick(position: Int) {
                //点击了拒绝,插入已处理列表
                treatedRequestList.add(TreatedRequestItem(untreatedRequestList[position].untreatedRequestUserIcon,untreatedRequestList[position].untreatedRequestUserName,false))
                //删除这一项
                untreatedRequestList.removeAt(position)
                untreatedAdapter.notifyItemRemoved(position)
                untreatedAdapter.notifyItemRangeChanged(position,untreatedRequestList.size)

                treatedAdapter.notifyItemInserted(treatedRequestList.size-1)
                treatedAdapter.notifyItemRangeChanged(treatedRequestList.size-1,treatedRequestList.size)
            }

        })
        //已处理列表监听
        treatedAdapter.setOnRecyclerViewClickListener(object :TreatedRequestContentAdapter.OnRecyclerViewClickListener{
            override fun onOptionClick(position: Int) {
               popMenuTreated(position)
            }

        })

        //设置未处理数据
        untreatedRequestRv.adapter=untreatedAdapter
        untreatedRequestRv.layoutManager=LinearLayoutManager(this)
        //设置已处理数据
        treatedRequestRv.adapter=treatedAdapter
        treatedRequestRv.layoutManager=LinearLayoutManager(this)

        treatedRequestRv.itemAnimator=null;

        untreatedRequestRv.itemAnimator=null;

    }

    private fun popMenuTreated(position: Int) {
        val layoutManager: RecyclerView.LayoutManager? = treatedRequestRv.layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.friend_request_treated_option_iv)
        val popupWindowLayout= LayoutInflater.from(this).inflate(R.layout.popup_window_treated_request,treatedRequestRv,false)
        val deleteTextView : TextView = popupWindowLayout.findViewById(R.id.treated_request_delete_pw_tv)
        val popupWindow= PopupWindow(this)
        popupWindow.isOutsideTouchable=true
        popupWindow.isFocusable=true
        popupWindow.contentView=popupWindowLayout
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))

        if(position!=treatedRequestList.size-1) {
            popupWindow.showAsDropDown(optionView)
        }
        else{
            popupWindow.showAsDropDown(optionView,0,-200)
        }

        //删除操作
        deleteTextView.setOnClickListener{
            treatedRequestList.removeAt(position)
            treatedAdapter.notifyItemRemoved(position)
            treatedAdapter.notifyItemRangeChanged(position,treatedRequestList.size)
            popupWindow.dismiss()
        }
    }

    private fun popMenuUntreated(position: Int) {
        val layoutManager: RecyclerView.LayoutManager? = untreatedRequestRv.layoutManager
        val itemView: View? = layoutManager?.findViewByPosition(position)
        val optionView: ImageView? = itemView?.findViewById(R.id.friend_request_untreated_option_iv)
        val popupWindowLayout= LayoutInflater.from(this).inflate(R.layout.popup_window_untreated_request,untreatedRequestRv,false)
        val ignoreTextView : TextView = popupWindowLayout.findViewById(R.id.untreated_request_ignore_pw_tv)
        val popupWindow= PopupWindow(this)
        popupWindow.isOutsideTouchable=true
        popupWindow.isFocusable=true
        popupWindow.contentView=popupWindowLayout
        popupWindow.setBackgroundDrawable(ColorDrawable(0x00000000))

        if(position!=untreatedRequestList.size-1) {
            popupWindow.showAsDropDown(optionView)
        }
        else{
            popupWindow.showAsDropDown(optionView,0,-200)
        }

        //删除操作
        ignoreTextView.setOnClickListener{
            untreatedRequestList.removeAt(position)
            untreatedAdapter.notifyItemRemoved(position)
            untreatedAdapter.notifyItemRangeChanged(position,untreatedRequestList.size)
            popupWindow.dismiss()
        }
    }
}