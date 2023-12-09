package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity()  {



    override fun onCreate(savedInstanceState: Bundle?) {

        //on create
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttons
        val openMapButton = findViewById<Button>(R.id.OpenMapButton)
        val openTutorialButton = findViewById<Button>(R.id.OpenTutorialButton)
        //event listeners
        openMapButton.setOnClickListener{ v ->
            Intent(this, MapActivity::class.java).also{
                startActivity(it)
            }
        }


        openTutorialButton.setOnClickListener{ v ->
            Intent(this, MainTutorial::class.java).also{
                startActivity(it)
            }
        }


        //change fonts
        val customTypeface = resources.getFont(R.font.oxygene1)
        val mainTitle = findViewById<TextView>(R.id.MainTitle)



        openTutorialButton.typeface = customTypeface
        openMapButton.typeface = customTypeface
        mainTitle.typeface = customTypeface


















    }//End on create




}