package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.graphics.Typeface


class MainActivity : AppCompatActivity()  {



    override fun onCreate(savedInstanceState: Bundle?) {

        //on create
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttons
        val OpenMapButton = findViewById<Button>(R.id.OpenMapButton)
        val OpenTutorialButton = findViewById<Button>(R.id.OpenTutorialButton)
        //event listeners
        OpenMapButton.setOnClickListener{v ->
            Intent(this, MapActivity::class.java).also{
                startActivity(it)
            }
        }


        OpenTutorialButton.setOnClickListener{v ->
            Intent(this, MainTutorial::class.java).also{
                startActivity(it)
            }
        }


    }//End on create




}