package com.example.bbsuestc.homeActivity.home

import com.example.bbsuestc.utils.adjustScrollSensitivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.bbsuestc.R
import com.example.bbsuestc.newPostActivity.NewPostActivity
import com.example.bbsuestc.searchActivity.SearchActivity
import com.example.bbsuestc.utils.APIStatics
import com.example.bbsuestc.webViewActivity.WebViewActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var searchET: ImageView
    private lateinit var headerIV: ImageView
    private lateinit var toolbarTL: TabLayout
    private lateinit var contentVP: ViewPager2
    private lateinit var newPostFAB: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_home, container, false)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        (activity as AppCompatActivity).setSupportActionBar(root.findViewById(R.id.home_toolbar))

        searchET = root.findViewById(R.id.home_toolbar_search_iv)
        searchET.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
        headerIV = root.findViewById(R.id.home_toolbar_head_icon_iv)
        headerIV.setOnClickListener {
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra("url","http://222.197.183.89:65342/user/me"+ APIStatics.TOKEN)
            startActivity(intent)
        }

        toolbarTL = root.findViewById(R.id.home_toolbar_contents_tl)
        contentVP = root.findViewById(R.id.home_content_vp)
        contentVP.adapter = HomeContentVPAdapter(this)
        contentVP.adjustScrollSensitivity(contentVP, 2)
        contentVP.offscreenPageLimit = 3

        val titles = arrayOf("热门", "最新回复", "最新发表", "精华")
        TabLayoutMediator(toolbarTL, contentVP) { tab, position ->
            tab.setText(titles[position])
        }.attach()

        newPostFAB = root.findViewById(R.id.home_new_post_fab)
        newPostFAB.setOnClickListener {
            val intent = Intent(activity, NewPostActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}