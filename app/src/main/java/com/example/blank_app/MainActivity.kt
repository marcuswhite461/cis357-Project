package com.example.blank_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button

class MainActivity : AppCompatActivity()  {



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //buttons
        val OpenMapButton = findViewById<Button>(R.id.OpenMapButton)
        //event listeners
        OpenMapButton.setOnClickListener{v ->
            setContentView(R.layout.activity_map)
        }







    }//End on create




}