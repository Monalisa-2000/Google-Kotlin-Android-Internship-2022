package com.example.groceryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etPassword2: EditText
    lateinit var btnSubmit: Button
    internal var databaseHandler:DataHandler= DataHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        title="Sign Up"

        etName=findViewById(R.id.etName)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        etPassword2=findViewById(R.id.etPassword2)
        btnSubmit=findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            if(etName.text.toString() != "" && etEmail.text.toString() !="" && etPassword.text.toString() !="" && etPassword2.text.toString() !=""){
                if(etPassword.text.toString() == etPassword2.text.toString()){
                    databaseHandler.insert(etName.text.toString(),etEmail.text.toString(),etPassword.text.toString())
                    Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
                    Thread.sleep(1500)
                    finish()
                }
                else{
                    Toast.makeText(this,"Password doesn't match", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"Empty fields",Toast.LENGTH_SHORT).show()
            }
        }
    }
}