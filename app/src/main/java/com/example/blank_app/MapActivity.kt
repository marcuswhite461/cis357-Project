package com.example.blank_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
    GoogleMap.OnMarkerClickListener {

    //Google map object: this is where we do all our runtime actions
    private var mGoogleMap:GoogleMap? = null
    private var markerCount = 0
    private var lifeCount = 3
    //list of markers
    private var selectedMarker: Marker? = null
    private val gvsu = LatLng(42.9636004, -85.8892062)
    private val markerList: MutableList<Marker> = mutableListOf()
    private val markerColors: MutableMap<Marker, Float> = mutableMapOf()
    private var coordinateList: List<LatLng> = emptyList()
    //recycler view
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewContainer: LinearLayout
    private lateinit var customAdapter: CustomAdapter




    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //button to exit map
        val mapBackButton = findViewById<Button>(R.id.MapBackButton)

        //textview of life counter
        val lifeCounter = findViewById<TextView>(R.id.lifeCount)

        //event listener for exiting map
        mapBackButton.setOnClickListener{ v ->
            //clear saved coordinates when quitting
            markerList.clear()
            finish()
        }

        // Initialize the RecyclerView and Adapter
        recyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(markerList.map { it.position }) { selectedCoordinate ->
            handleCoordinateSelection(selectedCoordinate)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        recyclerViewContainer = findViewById(R.id.recyclerViewContainer)

        //map fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //changes font
        val customTypeface = resources.getFont(R.font.oxygene1)
        mapBackButton.typeface = customTypeface
        lifeCounter.typeface = customTypeface


        recyclerView.visibility = View.GONE

    }

    //on map ready setup long click listener
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
        mGoogleMap!!.setOnMapLongClickListener(this)

        //default screen on GVSU
        mGoogleMap!!.addMarker(MarkerOptions().position(gvsu)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.zoomTo(12.0F))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(gvsu))

        //marker/pin listener
        googleMap.setOnMarkerClickListener(this)

    }

    //Add marker when clicked
    override fun onMapLongClick(pointClicked: LatLng) {
        Log.d("DANS:", "lat is:$pointClicked")
        markerCount ++
        val newMarker = mGoogleMap!!.addMarker(MarkerOptions()
            .position(pointClicked)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            .title("Marker $markerCount"))
        //add marker to list
        if (newMarker != null) {
            markerList.add(newMarker)
            markerColors[newMarker] = BitmapDescriptorFactory.HUE_RED
            updateCoordinateList()
        }

    }

    //marker click event listener
    override fun onMarkerClick(marker: Marker): Boolean {
        // show markerList
        showRecyclerView()

        // set Marker
        if (selectedMarker == null) {
            // No marker is currently selected
            selectedMarker = marker
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            customAdapter.setSelectedCoordinate(marker.position)
        } else {
            // A marker is already selected
            if (marker.position == selectedMarker?.position) {
                // The clicked marker has the same coordinates as the selected marker
                // Player does not lose a life, and the marker turns green
                selectedMarker?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                markerColors[marker] = BitmapDescriptorFactory.HUE_GREEN
                checkWinCondition()
                selectedMarker = null
            } else {
                // The clicked marker has different coordinates from the selected marker
                // Player loses a life, and the marker turns blue
                selectedMarker?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                Toast.makeText(this, "Wrong coordinate, Lost a life!", Toast.LENGTH_SHORT).show()
                markerColors[marker] = BitmapDescriptorFactory.HUE_RED
                decreaseLifeCount()
                selectedMarker = null
            }

            hideRecyclerView()
        }

        return true
    }

    private fun handleCoordinateSelection(selectedCoordinate: LatLng) {
        val matchingMarker = markerList.find { it.position == selectedCoordinate }
        matchingMarker?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
    }

    private fun updateCoordinateList() {
        coordinateList = markerList.map { it.position }
        customAdapter.notifyDataSetChanged()
    }
    private fun showRecyclerView() {
        recyclerViewContainer?.visibility = View.VISIBLE
        recyclerView?.visibility = View.VISIBLE

    }

    private fun hideRecyclerView() {
        recyclerViewContainer?.visibility = View.GONE
        recyclerView?.visibility = View.GONE

    }

    private fun updateLifeCounter() {
        findViewById<TextView>(R.id.lifeCount).text = "LIVES: $lifeCount"
    }

    private fun decreaseLifeCount() {
        lifeCount--
        updateLifeCounter()
        if (lifeCount <= 0) {
            Toast.makeText(this, "You lost! Try again!", Toast.LENGTH_SHORT).show()
            gameOver()
        }
    }
    private fun checkWinCondition() {
        val allMarkersAreGreen = markerColors.values.all { it == BitmapDescriptorFactory.HUE_GREEN }

        if (allMarkersAreGreen) {
            // All markers are green, notify the player of the win
            Toast.makeText(this, "Congratulations! You won!", Toast.LENGTH_SHORT).show()
            gameOver()
        }
    }

    private fun gameOver() {
        finish()
    }
}