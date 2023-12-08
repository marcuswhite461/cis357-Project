package com.example.blank_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)



        //button
        val MapBackButton = findViewById<Button>(R.id.MapBackButton)
        //event listener
        MapBackButton.setOnClickListener{v ->
            setContentView(R.layout.activity_main)
        }




    }
}