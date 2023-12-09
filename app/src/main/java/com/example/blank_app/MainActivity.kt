package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.graphics.Typeface
import android.widget.TextView


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

<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes
        //change fonts
        val customTypeface = resources.getFont(R.font.oxygene1)
        val MainTitle = findViewById<TextView>(R.id.MainTitle)


        OpenTutorialButton.typeface = customTypeface
        OpenMapButton.typeface = customTypeface
        MainTitle.typeface = customTypeface

<<<<<<< Updated upstream
=======










>>>>>>> Stashed changes
    }//End on create




}