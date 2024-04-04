package com.example.bbsuestc.postsPertinentActivity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PostsPertinentVPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,fragments:ArrayList<Fragment>) : FragmentStateAdapter(fragmentManager,lifecycle) {
    private lateinit var fragments:ArrayList<Fragment>

    init{
        this.fragments=fragments
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }

}