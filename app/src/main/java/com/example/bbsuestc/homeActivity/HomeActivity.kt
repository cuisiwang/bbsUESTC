package com.example.bbsuestc.homeActivity

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.home.HomeFragment
import com.example.bbsuestc.homeActivity.messages.MessagesFragment
import com.example.bbsuestc.homeActivity.my.MyFragment
import com.example.bbsuestc.homeActivity.plates.PlatesFragment
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var navView: BottomNavigationView
    private val vpAdapter : FragmentStateAdapter

    private val onNavigationItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        switchToFragment(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        navView = findViewById(R.id.activity_home_bottom_navview)
        navView.isItemActiveIndicatorEnabled = false
        viewPager = findViewById(R.id.activity_home_vp_nav_host)
        viewPager.apply {
            adapter = vpAdapter
            isUserInputEnabled = false
        }

        navView.setOnItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun switchToFragment(itemId: Int) {
        when(itemId){
            R.id.navigation_home -> viewPager.currentItem = 0
            R.id.navigation_plates -> viewPager.currentItem = 1
            R.id.navigation_messages -> viewPager.currentItem = 2
            R.id.navigation_my -> viewPager.currentItem = 3
        }
    }

    init {
        vpAdapter= object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> PlatesFragment()
                    2 -> MessagesFragment()
                    3 -> MyFragment()
                    else -> HomeFragment()
                }
            }
        }
    }
}