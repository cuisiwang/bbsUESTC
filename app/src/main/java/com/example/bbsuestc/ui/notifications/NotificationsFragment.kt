package com.example.bbsuestc.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        val view: View = inflater.inflate(R.layout.fragment_notifications,container,false)

        val textView: TextView = view.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}