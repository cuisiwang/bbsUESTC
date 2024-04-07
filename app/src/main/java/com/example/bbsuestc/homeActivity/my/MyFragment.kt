package com.example.bbsuestc.homeActivity.my

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.HomeActivity
import com.example.bbsuestc.webViewActivity.WebViewActivity


class MyFragment : Fragment() {

    private lateinit var favourite : TextView
    private lateinit var posts : TextView
    private lateinit var draft : TextView
    private lateinit var reply : TextView
    private lateinit var histories : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_my, container, false)

        val myViewModel : MyViewModel = ViewModelProvider(this)[MyViewModel::class.java]
//        val textView: TextView = root.findViewById(R.id.my_text)
//        myViewModel.text.observe(viewLifecycleOwner){
//            textView.text=it
//        }

        favourite = root.findViewById(R.id.my_posts_favourites_tv)
        favourite.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}