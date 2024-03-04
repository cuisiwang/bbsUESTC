package com.example.bbsuestc.plateDetailActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.testUtils.TestData

class PlateDetailViewModel: ViewModel() {
    val pinnedPosts = TestData.pinnedPosts()

}