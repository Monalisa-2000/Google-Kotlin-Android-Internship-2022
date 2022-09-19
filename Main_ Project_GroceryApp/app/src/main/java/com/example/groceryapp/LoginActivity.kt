package com.example.groceryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var btnLogin: Button
    lateinit var btnSignup: Button
    lateinit var shared: SharedPreferences
    internal var databaseHandler:DataHandler= DataHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor=Color.parseColor("#202020")

        et_email=findViewById(R.id.et_email)
        et_password=findViewById(R.id.et_password)
        btnLogin=findViewById(R.id.btnLogin)
        btnSignup=findViewById(R.id.btnSignup)
        shared=getSharedPreferences("login", Context.MODE_PRIVATE)
        check()

        btnLogin.setOnClickListener {
            var cursor=databaseHandler.checkCredentials(et_email.text.toString(),et_password.text.toString())
            if(et_email.text.toString() != "" && et_password.text.toString() !=""){
                if(cursor.count>0){
                    cursor.moveToFirst()
                    val username=cursor.getString(cursor.getColumnIndex("name"))
                    val edit=shared.edit()
                    edit.putBoolean("stay",true)
                    edit.putString("username",username)
                    edit.apply()
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show()
            }
        }

        btnSignup.setOnClickListener{
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun check() {
        val bol=shared.getBoolean("stay",false)
        if(bol){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}