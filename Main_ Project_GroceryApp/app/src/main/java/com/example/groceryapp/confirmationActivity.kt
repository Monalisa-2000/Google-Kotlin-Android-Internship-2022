package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class confirmationActivity : AppCompatActivity() {

    lateinit var rating:RatingBar
    lateinit var tv_rating:TextView
    lateinit var btnSend:Button
    lateinit var btnBack:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)
        title="Confirmation"

        rating=findViewById(R.id.rating)
        tv_rating=findViewById(R.id.tv_rating)
        btnSend=findViewById(R.id.btnSend)
        btnBack=findViewById(R.id.btnBack)

        rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            tv_rating.text="Rating: "+rating.toString()
        }

        btnSend.setOnClickListener {
            Toast.makeText(this,"You rated "+rating.rating.toString(),Toast.LENGTH_SHORT).show()
        }

        btnBack.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}