package com.example.groceryapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ItemAdapter(private val context: Activity,private val arrayList: ArrayList<Item>):ArrayAdapter<Item>(context,R.layout.items,arrayList) {
    private var sum:Int=0
    internal var dataHandler:DataHandler= DataHandler(getContext())
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater= LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.items,null)

        val itemName:TextView=view.findViewById(R.id.itemName)
        val itemPrice:TextView=view.findViewById(R.id.itemPrice)
        val itemImage:ImageView=view.findViewById(R.id.itemImage)
        val btnAdd:Button=view.findViewById(R.id.btnAdd)

        itemImage.setImageResource(arrayList[position].imageId)
        itemName.text=arrayList[position].name
        itemPrice.text="₹"+arrayList[position].price.toString()

        btnAdd.setOnClickListener {
            if(dataHandler.addItem(arrayList[position].name,arrayList[position].price)>-1){
                Toast.makeText(getContext(),"Added to cart !!",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}