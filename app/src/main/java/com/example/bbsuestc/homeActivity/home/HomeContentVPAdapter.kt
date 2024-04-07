package com.example.bbsuestc.homeActivity.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bbsuestc.homeActivity.home.homeContents.digest.DigestFragment
import com.example.bbsuestc.homeActivity.home.homeContents.hot.HotFragment
import com.example.bbsuestc.homeActivity.home.homeContents.newRelease.NewReleaseFragment
import com.example.bbsuestc.homeActivity.home.homeContents.newReply.NewReplyFragment

class HomeContentVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> HotFragment.newInstance()
        1 -> NewReplyFragment.newInstance()
        2 -> NewReleaseFragment.newInstance()
        else -> DigestFragment.newInstance()
    }
}