package com.example.bbsuestc.homeActivity.plates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bbsuestc.R

//intent key所用常量
//TODO 常量统一放在某个位置
const val EXTRA_PLATE_ID = "plate_id"
const val EXTRA_PLATE_NAME = "plate_name"

//对应 模块详情 页面
class PlateDetailActivity : AppCompatActivity() {
    private lateinit var plateIdTextView: TextView
    private lateinit var plateNameTextView: TextView

    private var plate_id = 0
    private var plate_name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate_detail)

        plateIdTextView = findViewById(R.id.plate_id_text_view)
        plateNameTextView = findViewById(R.id.plate_name_text_view)

        plate_id = intent.getIntExtra(EXTRA_PLATE_ID, 0)
        plate_name = intent.getStringExtra(EXTRA_PLATE_NAME).toString()
        plateIdTextView.text = plate_id.toString()
        plateNameTextView.text = plate_name
    }


}