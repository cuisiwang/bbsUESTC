package com.example.bbsuestc.homeActivity.plates

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bbsuestc.R

//intent key所用常量
const val KEY_PLATE_ID = "plate_id"
const val KEY_PLATE_NAME = "plate_name"

class PlateChildAdapter(val plates: List<Plate>): Adapter<PlateChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_single_plate, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val plate_id = plates[position].id
        val plate_name = plates[position].name

        holder.plateNameTextView.text = plates[position].name
        //点击模块进入模块详情
        holder.plateRootLayout.setOnClickListener {
            val intent = Intent(context, PlateDetailActivity::class.java)
            //传入板块id和名称，方便确认打开哪个板块
            intent.putExtra(KEY_PLATE_ID, plate_id)
            intent.putExtra(KEY_PLATE_NAME, plate_name)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = plates.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val plateNameTextView: TextView
        val plateRootLayout: LinearLayout

        init {
            plateNameTextView = view.findViewById(R.id.plate_name) as TextView
            plateRootLayout = view.findViewById(R.id.plate_root_layout) as LinearLayout
        }
    }
}