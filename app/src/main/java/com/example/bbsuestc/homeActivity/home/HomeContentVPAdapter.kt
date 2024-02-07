package com.example.bbsuestc.homeActivity.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bbsuestc.homeActivity.home.homeContents.digest.DigestFragment
import com.example.bbsuestc.homeActivity.home.homeContents.hot.HotFragment
import com.example.bbsuestc.homeActivity.home.homeContents.newRelease.NewReleaseFragment
import com.example.bbsuestc.homeActivity.home.homeContents.newReply.NewReplyFragment
import com.example.bbsuestc.homeActivity.home.homeContents.statics.StaticsFragment

class HomeContentVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> HotFragment()
        1 -> NewReplyFragment()
        2 -> NewReleaseFragment()
        3 -> DigestFragment()
        else -> StaticsFragment()
    }
}