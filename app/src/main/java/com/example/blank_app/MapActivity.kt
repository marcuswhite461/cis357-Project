package com.example.blank_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
    GoogleMap.OnMarkerClickListener {

    //Google map object: this is where we do all our runtime actions
    private var mGoogleMap:GoogleMap? = null
    //list of markers
    val gvsu = LatLng(42.9636004, -85.8892062)
    var coordList: ArrayList<LatLng> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)



        //button to exit map
        val MapBackButton = findViewById<Button>(R.id.MapBackButton)

        //event listener for exiting map
        MapBackButton.setOnClickListener{v ->
            //clear saved coordates when quitting
            coordList.clear()
            finish()
        }

        //map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //initialize the list of coordinates
        coordList.add(gvsu)
    }

    //on map ready setup long click listener
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap!!.setOnMapLongClickListener(this)

        //default screen on GVSU
        mGoogleMap!!.addMarker(MarkerOptions().position(gvsu))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.zoomTo(12.0F))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(gvsu))

        //marker/pin listener
        googleMap.setOnMarkerClickListener(this)

    }

    //Add marker when clicked
    override fun onMapLongClick(pointClicked: LatLng) {
        Log.d("DANS:", "lat is:" + pointClicked.toString())
        mGoogleMap!!.addMarker(MarkerOptions().position(pointClicked))
        //add marker to list
        coordList.add(pointClicked)
    }

    //marker click event listener
    override fun onMarkerClick(marker: Marker): Boolean {

        //if marker clicked do something, like show all possible choices of
        //coordidantes from coordList?
        Toast.makeText(
            this,
            "${marker.position.toString()}!",
            Toast.LENGTH_SHORT
        ).show()
        //Need to add a menu of all coords to click on. if good turn pin green
        
        return true
    }

    }