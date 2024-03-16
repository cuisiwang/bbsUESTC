package com.example.bbsuestc.plateDetailActivity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bbsuestc.plateDetailActivity.plateContents.digest.DigestFragment
import com.example.bbsuestc.plateDetailActivity.plateContents.hot.HotFragment
import com.example.bbsuestc.plateDetailActivity.plateContents.newRelease.NewReleaseFragment
import com.example.bbsuestc.plateDetailActivity.plateContents.newReply.NewReplyFragment


class PlateContentVPAdapter(activity: PlateDetailActivity): FragmentStateAdapter(activity) {
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