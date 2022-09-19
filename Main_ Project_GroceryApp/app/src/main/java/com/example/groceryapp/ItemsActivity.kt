package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView

class ItemsActivity : AppCompatActivity() {
    lateinit var arrayList: ArrayList<Item>
    lateinit var list_item: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        title=intent.getStringExtra("title")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        arrayList= ArrayList()
        list_item=findViewById(R.id.list_item)
        if(intent.getStringExtra("title").equals("Vegetables")){
            showVegetables()
        }
        else if(intent.getStringExtra("title").equals("Fruits")){
            showFruits()
        }
        else if(intent.getStringExtra("title").equals("Daily Essentials")){
            showDaily()
        }
        list_item.adapter=ItemAdapter(this,arrayList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==R.id.cart){
            val intent=Intent(this,CartActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun showVegetables(){
        arrayList.add(Item("Potato- 1 kg",40,R.drawable.potato))
        arrayList.add(Item("Tomato- 1 kg",35,R.drawable.tomato))
        arrayList.add(Item("Brinjal- 1 kg",24,R.drawable.brinjal))
        arrayList.add(Item("Cabbage- 1 kg",28,R.drawable.cabbage))
        arrayList.add(Item("Capsicum- 1 kg",40,R.drawable.capsicum))
        arrayList.add(Item("Spinach- 1 kg",20,R.drawable.spinach))
        arrayList.add(Item("Cauliflower- 1 kg",35,R.drawable.cauliflower))
    }

    fun showFruits(){
        arrayList.add(Item("Mango- 1 kg",70,R.drawable.mango))
        arrayList.add(Item("Apple- 1 kg",120,R.drawable.apple))
        arrayList.add(Item("Guava- 1 kg",40,R.drawable.guava))
        arrayList.add(Item("Banana- 1 dozen",50,R.drawable.banana))
        arrayList.add(Item("Pomogranate- 1 kg",90,R.drawable.pomogranate))
        arrayList.add(Item("Watermelon- 1 kg",45,R.drawable.watermelon))
        arrayList.add(Item("Papaya- 1 kg",40,R.drawable.papaya))
    }

    fun showDaily(){
        arrayList.add(Item("Fortune Kachi Ghani Mustard Oil Pouch",206,R.drawable.fortune))
        arrayList.add(Item("Haldiram's Khatta Meetha",155,R.drawable.haldiram))
        arrayList.add(Item("Sunfeast Dark Fantasy Choco Fills",90,R.drawable.darkfantasy))
        arrayList.add(Item("Kissan Fresh Tomato Ketchup",140,R.drawable.kissan))
        arrayList.add(Item("Dove Beauty Bar Soap",160,R.drawable.dove))
        arrayList.add(Item("NIVEA Men Face Wash",125,R.drawable.nivea))
        arrayList.add(Item("Aashirvaad Atta",104,R.drawable.aashirvaad))
    }
}