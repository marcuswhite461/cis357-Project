package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.Typeface
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainTutorial : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tutorial)
        //Buttons
        val OpenMainButton = findViewById<Button>(R.id.OpenMainButton)


        OpenMainButton.setOnClickListener{v ->
            Intent(this, MainActivity::class.java).also{
                startActivity(it)
            }
        }


        //changes font
        val customTypeface = resources.getFont(R.font.oxygene1)
        val pinTutorial = findViewById<TextView>(R.id.pinTutorial)

        pinTutorial.typeface = customTypeface

    }


}