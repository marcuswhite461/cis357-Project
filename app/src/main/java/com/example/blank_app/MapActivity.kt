package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private var mGoogleMap:GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)



        //button to exit map
        val MapBackButton = findViewById<Button>(R.id.MapBackButton)

        //event listener for exiting map
        MapBackButton.setOnClickListener{v ->
            finish()
        }

        //map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    //on map ready setup long click listener
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap!!.setOnMapLongClickListener(this)
        //default screen
        val gvsu = LatLng(42.9636004, -85.8892062)
        mGoogleMap!!.addMarker(MarkerOptions().position(gvsu))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(gvsu))
    }

    //what to do when long click
    override fun onMapLongClick(latLng: LatLng) {
        Log.d("DANS:", "lat is:" + latLng.toString())
    }
}