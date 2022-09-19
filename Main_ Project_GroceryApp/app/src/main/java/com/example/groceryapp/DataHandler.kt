package com.example.groceryapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    lateinit var db: SQLiteDatabase
    companion object{
        private val DATABASE_VERSION=1
        private val DATABASE_NAME="Grocery"
        private val TABLE_NAME="Users"
        private val TABLE_NAME2="Cart"
        private val KEY_ID="id"
        private val KEY_NAME="name"
        private val KEY_EMAIL="email"
        private val KEY_PASSWORD="password"
        private val KEY_ITEM="item"
        private val KEY_PRICE="price"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE=("CREATE TABLE "+ TABLE_NAME+"("+ KEY_ID+" INTEGER PRIMARY KEY,"+ KEY_NAME+" TEXT,"+ KEY_EMAIL+" TEXT,"+ KEY_PASSWORD+" TEXT)")
        val CREATE_TABLE2=("CREATE TABLE "+ TABLE_NAME2+"("+ KEY_ID+" INTEGER PRIMARY KEY,"+ KEY_ITEM+" TEXT,"+ KEY_PRICE+" INTEGER)")
        db?.execSQL(CREATE_TABLE)
        db?.execSQL(CREATE_TABLE2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }

    fun insert(name:String, email:String, password:String):Long{
        db=this.writableDatabase
        var cv=ContentValues()
        cv.put(KEY_NAME,name)
        cv.put(KEY_EMAIL,email)
        cv.put(KEY_PASSWORD,password)
        val success=db.insert(TABLE_NAME,null,cv)
        db.close()
        return success
    }

    fun addItem(item: String, price:Int):Long{
        db=this.writableDatabase
        var cv=ContentValues()
        cv.put(KEY_ITEM,item)
        cv.put(KEY_PRICE,price)
        val success=db.insert(TABLE_NAME2,null,cv)
        db.close()
        return success
    }

    fun deleteCart(){
        db=this.writableDatabase
        db.delete(TABLE_NAME2,null,null)
    }

    fun checkCredentials(email: String, password: String):Cursor{
        db=this.readableDatabase
        val query=("SELECT * FROM "+ TABLE_NAME+" WHERE "+ KEY_EMAIL+"='$email'"+" and "+ KEY_PASSWORD+"='$password'")
        val cursor=db.rawQuery(query, null)
        return cursor
    }

    fun show():Cursor{
        db=this.readableDatabase
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME2,null)
    }

    fun remove(id:String):Int{
        db=this.writableDatabase
        return db.delete(TABLE_NAME2,"ID=?", arrayOf(id))
    }

}