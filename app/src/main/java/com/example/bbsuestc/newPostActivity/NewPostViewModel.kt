package com.example.bbsuestc.newPostActivity

import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewPostViewModel : ViewModel() {
    val rootLayout = MutableLiveData<ArrayList<View>>()

}