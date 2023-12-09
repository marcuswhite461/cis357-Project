package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button

class MainActivity : AppCompatActivity()  {



    override fun onCreate(savedInstanceState: Bundle?) {

        //on create
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttons
        val OpenMapButton = findViewById<Button>(R.id.OpenMapButton)
        //event listeners
        OpenMapButton.setOnClickListener{v ->
            Intent(this, MapActivity::class.java).also{
                startActivity(it)
            }
        }
        



    }//End on create




}