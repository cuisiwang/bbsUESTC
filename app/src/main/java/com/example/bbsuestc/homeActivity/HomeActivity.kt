package com.example.bbsuestc.homeActivity

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.home.HomeFragment
import com.example.bbsuestc.homeActivity.messages.MessagesFragment
import com.example.bbsuestc.homeActivity.my.MyFragment
import com.example.bbsuestc.homeActivity.plates.PlatesFragment
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    private val fragments: HashMap<Int, Fragment> = hashMapOf()
    init {
        fragments[R.id.navigation_home] = HomeFragment()
        fragments[R.id.navigation_plates] = PlatesFragment()
        fragments[R.id.navigation_messages] = MessagesFragment()
        fragments[R.id.navigation_my] = MyFragment()
    }

    private val onNavigationItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        switchToFragment(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.activity_home_bottom_navview)
        navView.isItemActiveIndicatorEnabled = false

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener(onNavigationItemSelectedListener)
        switchToFragment(R.id.navigation_home)

    }

    private fun switchToFragment(itemId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (fragment in fragments.values) {
            transaction.hide(fragment)
        }
        val fragment = fragments[itemId]
        if (!fragment!!.isAdded) {
            transaction.add(R.id.nav_host_fragment_activity_home, fragment)
        }
        transaction.show(fragment)
        transaction.commit()
    }

}