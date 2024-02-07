package com.example.bbsuestc.homeActivity.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bbsuestc.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var searchET : ImageView
    private lateinit var headerIV : ImageView
    private lateinit var toolbarTL : TabLayout
    private lateinit var contentVP : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_home,container,false)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        (activity as AppCompatActivity).setSupportActionBar(root.findViewById(R.id.home_toolbar))

//        val textView: TextView = root.findViewById(R.id.text_home)
//        textView.setOnClickListener { homeViewModel.changeText() }
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it  //这里的.text指的是TextView控件的text属性，可以对应的修改textSize一类的属性
//            textView.textSize=it.length/1F
//        }


        searchET = root.findViewById(R.id.home_toolbar_search_iv)
        searchET.setOnClickListener{
            Toast.makeText(context, "此处点击逻辑改为启动搜索的Activity", Toast.LENGTH_SHORT).show()}

        toolbarTL = root.findViewById(R.id.home_toolbar_contents_tl)
        contentVP = root.findViewById(R.id.home_content_vp)
        contentVP.apply {
            adapter = HomeContentVPAdapter(this@HomeFragment)
        }

        val titles = arrayOf("热门", "最新回复", "最新发表", "精华", "统计数据")
        TabLayoutMediator(toolbarTL, contentVP) { tab, position ->
            tab.setText(titles[position])
        }.attach()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}