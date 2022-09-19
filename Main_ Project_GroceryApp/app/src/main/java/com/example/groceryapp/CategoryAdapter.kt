package com.example.groceryapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CategoryAdapter(private val context: Activity, private val arrayList: ArrayList<Category>):ArrayAdapter<Category>(context,R.layout.category,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.category,null)

        val catImage:ImageView=view.findViewById(R.id.catImage)
        val tvCategory:TextView=view.findViewById(R.id.tvCategory)

        catImage.setImageResource(arrayList[position].imageId)
        tvCategory.setText(arrayList[position].category)

        return view
    }
}