package com.example.groceryapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class CartAdapter(private val context: Activity, private val arrayList: ArrayList<Cart>): ArrayAdapter<Cart>(context,R.layout.cart,arrayList) {
    private var listId:ArrayList<Int> = ArrayList()
    internal var dataHandler:DataHandler= DataHandler(getContext())

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view:View=inflater.inflate(R.layout.cart,null)

        val cartItem: TextView =view.findViewById(R.id.cartItem)
        val cartPrice: TextView =view.findViewById(R.id.cartPrice)
        val btnDelete: ImageButton=view.findViewById(R.id.btnDelete)

        cartItem.text=arrayList[position].name
        cartPrice.text="₹"+arrayList[position].price.toString()
        btnDelete.setOnClickListener {
            listId=lst()
            dataHandler.remove(listId[position].toString())
            val intent=Intent(context,MyReceiver::class.java)
            context.sendBroadcast(intent)
        }

        return view
    }

    fun lst():ArrayList<Int>{
        var cursor=dataHandler.show()
        var lst:ArrayList<Int>
        lst= ArrayList()
        while(cursor.moveToNext()){
            lst.add(cursor.getInt(cursor.getColumnIndex("id")))
        }
        return lst
    }
}