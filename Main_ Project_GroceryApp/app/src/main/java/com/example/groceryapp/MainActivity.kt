package com.example.groceryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var tv_username: TextView
    lateinit var arrayList: ArrayList<Category>
    lateinit var itemList: ArrayList<Item>
    lateinit var list_category: ListView
    lateinit var shared: SharedPreferences
    internal var dataHandler:DataHandler=DataHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemList= ArrayList()
        arrayList= ArrayList()
        list_category=findViewById(R.id.list_category)
        tv_username=findViewById(R.id.tv_username)
        shared=getSharedPreferences("login", Context.MODE_PRIVATE)
        var username=shared.getString("username","Username")
        tv_username.text="Hi, $username"
        arrayList.add(Category(R.drawable.vegetable,"Vegetables"))
        arrayList.add(Category(R.drawable.fruits,"Fruits"))
        arrayList.add(Category(R.drawable.daily,"Daily Essentials"))

        list_category.adapter=CategoryAdapter(this,arrayList)

        list_category.onItemClickListener=object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==0){
                    val intent=Intent(this@MainActivity,ItemsActivity::class.java)
                    intent.putExtra("title","Vegetables")
                    startActivity(intent)
                }
                else if (position==1){
                    val intent=Intent(this@MainActivity,ItemsActivity::class.java)
                    intent.putExtra("title","Fruits")
                    startActivity(intent)
                }
                else if(position==2){
                    val intent=Intent(this@MainActivity,ItemsActivity::class.java)
                    intent.putExtra("title","Daily Essentials")
                    startActivity(intent)
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==R.id.cart){
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
            return true
        }
        else if(id==R.id.logout){
            dataHandler.deleteCart()
            val edit=shared.edit()
            edit.putBoolean("stay",false)
            edit.remove("username")
            edit.apply()
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}