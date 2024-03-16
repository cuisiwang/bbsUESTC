package com.example.bbsuestc.searchActivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem
import com.example.bbsuestc.recyclerViewContents.usersContent.UsersContentAdapter
import com.example.bbsuestc.recyclerViewContents.usersContent.UsersItem

class SearchActivity : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var search : ImageView
    private lateinit var searchType : Spinner
    private lateinit var searchContent : EditText
    private lateinit var resultNumber : TextView
    private lateinit var resultContent : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(findViewById(R.id.search_toolbar))

        initView()

    }

    private fun initView() {
        back = findViewById(R.id.search_toolbar_back_iv)
        search = findViewById(R.id.search_toolbar_search_iv)
        searchType = findViewById(R.id.search_toolbar_type_sp)
        searchContent = findViewById(R.id.search_toolbar_content_et)
        resultNumber = findViewById(R.id.search_result_numbers_tv)
        resultContent = findViewById(R.id.search_result_rv)

        back.setOnClickListener{ finish() }
        search.setOnClickListener{search(searchType.selectedItem.toString(), searchContent.text.toString())}

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("帖子","用户"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchType.adapter = adapter

        searchContent.post { searchContent.requestFocus() }
    }


    private fun search(searchType: String, searchText: String){
        if (searchText == ""){
            Toast.makeText(this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show()
            return
        }
        //隐藏键盘，失去焦点
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        searchContent.clearFocus()

        resultContent.adapter = null

        val resultNumbers :Int
        if(searchType == "帖子"){
            //模拟搜索帖子返回的结果
            //data应该从ViewModel里获取
            val data = arrayListOf<PostsItem>()
            for(i in 0..10){
                data.add(
                    PostsItem("","这是发帖人ID","2022-12-22","这是帖子的标题",
                        "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容",
                        "校园生活",114514,191)
                )
            }
            resultContent.layoutManager = LinearLayoutManager(this)
            resultContent.adapter = PostsContentAdapter(data)
            resultNumbers = data.size
        }else{
            //模拟搜索用户返回结果
            val data = arrayListOf<UsersItem>()
            for(i in 0..20){
                data.add(
                    UsersItem("","这是发帖人ID","这是一条个人简介")
                )
            }
            resultContent.layoutManager = LinearLayoutManager(this)
            resultContent.adapter = UsersContentAdapter(data)
            resultNumbers = data.size
        }
        resultNumber.text = "找到${resultNumbers}条结果"
        resultNumber.visibility = View.VISIBLE
    }
}