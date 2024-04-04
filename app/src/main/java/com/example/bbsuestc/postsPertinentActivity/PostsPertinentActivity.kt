package com.example.bbsuestc.postsPertinentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewpager2.widget.ViewPager2
import com.example.bbsuestc.R
import com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.all.AllFragment
import com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.at.PostPertinentAtFragment
import com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.commentary.PostPertinentCommentaryFragment
import com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.remarks.PostPertinentRemarksFragment
import com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.reply.PostsPertinentReplyFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PostsPertinentActivity : AppCompatActivity() {
    private lateinit var backIcon:ImageView

    private lateinit var viewModel: PostsPertinentViewModel

    private lateinit var postViewPager2: ViewPager2

    private lateinit var fragments:ArrayList<Fragment>

    private lateinit var tabItems:ArrayList<PostTabItem>

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_pertinent)

        backIcon=findViewById(R.id.posts_pertinent_back_iv)

        backIcon.setOnClickListener{
            finish()
        }

        postViewPager2=findViewById(R.id.posts_content_vp)
        viewModel=ViewModelProvider(this).get(PostsPertinentViewModel::class.java)

        tabItems= viewModel.tabItems
        tabLayout=findViewById(R.id.posts_pertinent_contents_tl)
        initFragment()

    }

    private fun initFragment() {
        fragments= arrayListOf<Fragment>().apply {
            add(AllFragment.newInstance())
            add(PostsPertinentReplyFragment.newInstance())
            add(PostPertinentAtFragment.newInstance())
            add(PostPertinentCommentaryFragment.newInstance())
            add(PostPertinentRemarksFragment.newInstance())

        }
        postViewPager2.adapter=PostsPertinentVPAdapter(supportFragmentManager,lifecycle,fragments)
        TabLayoutMediator(tabLayout,postViewPager2,object: TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {

                tab.setCustomView(getTabView(position))

                val tabView=tab.customView
                if(tabItems[position].haveNotice) {
                    if (tabView != null) {
                        tabView.layoutParams = LinearLayout.LayoutParams(
                            250,
                            ViewGroup.LayoutParams.WRAP_CONTENT

                        )
                    }
                }
            }

        }).attach()

    }

    private fun getTabView(position: Int): View {
        val view:View=LayoutInflater.from(this).inflate(R.layout.posts_pertinent_tl_view,null)
        val textTitle : TextView=view.findViewById(R.id.posts_tab_note)
        val frameLayout:FrameLayout=view.findViewById(R.id.posts_pertinent_tl_fl)
        val cnt:TextView=view.findViewById(R.id.posts_pertinent_tl_cnt)
        textTitle.setText(tabItems[position].title)
        if(tabItems[position].haveNotice)
        {frameLayout.visibility=View.VISIBLE}
        else
        {frameLayout.visibility=View.GONE}
        cnt.setText(tabItems[position].noticeCnt.toString())
        return view
    }
}