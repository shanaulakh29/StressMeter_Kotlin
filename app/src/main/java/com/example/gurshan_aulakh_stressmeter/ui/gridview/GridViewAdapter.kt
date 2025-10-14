package com.example.gurshan_aulakh_stressmeter.ui.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.gurshan_aulakh_stressmeter.R
import android.widget.ImageView
class GridViewAdapter(private val context: Context, private val images: List<Int>): BaseAdapter(){
    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any? {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)

        imageView.setImageResource(images[position])

        return view
    }

}