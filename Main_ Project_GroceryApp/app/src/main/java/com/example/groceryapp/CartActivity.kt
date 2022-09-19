package com.example.groceryapp

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class CartActivity : AppCompatActivity() {

    lateinit var btnBuy:Button
    lateinit var noitem:TextView
    lateinit var cart_list:ListView
    lateinit var imgNoitem:ImageView
    lateinit var itemArray:ArrayList<Cart>
    lateinit var listId:ArrayList<Int>
    lateinit var receiver: BroadcastReceiver
    internal var dataHandler:DataHandler= DataHandler(this)
    var sum:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        title="Cart"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val filter =IntentFilter()
        filter.addAction("Change")
        receiver=object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                itemArray.clear()
                show()
                if(itemArray.isEmpty()){
                    noitem.visibility=View.VISIBLE
                    imgNoitem.visibility=View.VISIBLE
                }
            }
        }
        registerReceiver(receiver,filter)
        btnBuy=findViewById(R.id.btnBuy)
        noitem=findViewById(R.id.noitem)
        cart_list=findViewById(R.id.cart_list)
        imgNoitem=findViewById(R.id.imgNoitem)
        listId=ArrayList()
        itemArray= ArrayList()
        show()
        if(itemArray.isEmpty()){
            noitem.visibility=View.VISIBLE
            imgNoitem.visibility=View.VISIBLE
        }

        btnBuy.setOnClickListener {
            if(!itemArray.isEmpty()){
                val intent= Intent(this,paymentActivity::class.java)
                intent.putExtra("items",itemArray.size)
                intent.putExtra("total",sum)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"No item in cart !!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        itemArray.clear()
        show()
        if(itemArray.isEmpty()){
            noitem.visibility=View.VISIBLE
            imgNoitem.visibility=View.VISIBLE
        }
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun show(){
        var cursor=dataHandler.show()
        sum=0
        while(cursor.moveToNext()){
            var item=Cart(cursor.getString(cursor.getColumnIndex("item")),cursor.getInt(cursor.getColumnIndex("price")))
            itemArray.add(item)
            sum+=cursor.getInt(cursor.getColumnIndex("price"))
            listId.add(cursor.getInt(cursor.getColumnIndex("id")))
        }
        btnBuy.text="₹ "+sum.toString()+"\n Buy"
        cart_list.adapter=CartAdapter(this,itemArray)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}

