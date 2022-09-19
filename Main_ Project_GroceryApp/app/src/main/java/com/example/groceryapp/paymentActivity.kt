package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text
import java.util.*

class paymentActivity : AppCompatActivity() {

    lateinit var rgPayment:RadioGroup
    lateinit var btnOrder:Button
    lateinit var tv_date:TextView
    lateinit var tv_items:TextView
    lateinit var tv_total:TextView
    internal var dataHandler:DataHandler= DataHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        title="Payment"

        val c= Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var month=c.get(Calendar.MONTH)+1
        var day=c.get(Calendar.DAY_OF_MONTH)

        btnOrder=findViewById(R.id.btnOrder)
        rgPayment=findViewById(R.id.rgPayment)
        tv_date=findViewById(R.id.tv_date)
        tv_items=findViewById(R.id.tv_items)
        tv_total=findViewById(R.id.tv_total)

        tv_date.text="Date :  $day/$month/$year"
        tv_items.text="Items :  "+intent.getIntExtra("items",0).toString()
        tv_total.text="Total Amount :  ₹"+intent.getIntExtra("total",0).toString()

        btnOrder.setOnClickListener {
            if(rgPayment.checkedRadioButtonId!=-1){
                val intent= Intent(this,confirmationActivity::class.java)
                startActivity(intent)
                dataHandler.deleteCart()
                finish()
            }
            else{
                Toast.makeText(this,"Select payment method",Toast.LENGTH_SHORT).show()
            }
        }
    }
}