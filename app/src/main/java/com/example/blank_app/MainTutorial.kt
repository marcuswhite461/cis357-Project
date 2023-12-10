package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainTutorial : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tutorial)
        //Buttons
        val openMainButton = findViewById<Button>(R.id.OpenMainButton)


        openMainButton.setOnClickListener{ v ->
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }


        //changes font
        val customTypeface = resources.getFont(R.font.oxygene1)
        val pinTutorial = findViewById<TextView>(R.id.pinTutorial)
        val winTutorial = findViewById<TextView>(R.id.winTutorial)

        openMainButton.typeface = customTypeface
        pinTutorial.typeface = customTypeface
        winTutorial.typeface = customTypeface


    }


}