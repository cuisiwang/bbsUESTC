package com.example.bbsuestc.homeActivity.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bbsuestc.homeActivity.my.MyFragment

class HomeContentVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    //test 乱写的
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return MyFragment()
    }
}