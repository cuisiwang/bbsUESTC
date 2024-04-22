package com.example.bbsuestc.homeActivity.my

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.HomeActivity
import com.example.bbsuestc.webViewActivity.WebViewActivity


class MyFragment : Fragment() {

    private lateinit var myHeader : CardView
    private lateinit var favourite : TextView
    private lateinit var posts : TextView
    private lateinit var draft : TextView
    private lateinit var reply : TextView
    private lateinit var histories : TextView
    private val myViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_my, container, false)

//        val textView: TextView = root.findViewById(R.id.my_text)
//        myViewModel.text.observe(viewLifecycleOwner){
//            textView.text=it
//        }

        initView(root)

        return root
    }

    private fun initView(root:View) {
        val intent = Intent(context,WebViewActivity::class.java)
        myHeader=root.findViewById(R.id.my_header_card_cv)
        favourite = root.findViewById(R.id.my_posts_favourites_tv)
        posts=root.findViewById(R.id.my_posts_mypost_tv)
        draft=root.findViewById(R.id.my_posts_drafts_tv)
        reply=root.findViewById(R.id.my_posts_replies_tv)
        histories=root.findViewById(R.id.my_posts_histories_tv)

        myHeader.setOnClickListener{
            intent.putExtra("url","http://222.197.183.89:65342/user/me")
            startActivity(intent)
        }
        favourite.setOnClickListener {
            intent.putExtra("url","http://222.197.183.89:65342/user/me/favorites")
            startActivity(intent)
        }
        posts.setOnClickListener {
            intent.putExtra("url","http://222.197.183.89:65342/user/me/threads")
            startActivity(intent)
        }
        draft.setOnClickListener {
            intent.putExtra("url","")
            startActivity(intent)
        }
        reply.setOnClickListener {
            intent.putExtra("url","http://222.197.183.89:65342/user/me/replies")
            startActivity(intent) }
        histories.setOnClickListener {
            intent.putExtra("url","")
            startActivity(intent) }
    }
}