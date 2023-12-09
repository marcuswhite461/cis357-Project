package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainTutorial : AppCompatActivity() {
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
    }


}