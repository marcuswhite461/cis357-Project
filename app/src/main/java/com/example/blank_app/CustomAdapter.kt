package com.example.blank_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng



class CustomAdapter(
    private val coordinateList: List<LatLng>,
    private val onCoordinateClickListener: (LatLng) -> Unit
) : RecyclerView.Adapter<CustomAdapter.CoordinateViewHolder>() {

    private var selectedCoordinate: LatLng? = null

    inner class CoordinateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCoordinate: TextView = itemView.findViewById(R.id.textViewCoordinate)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onCoordinateClickListener(coordinateList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoordinateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_coordinate, parent, false)

        return CoordinateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoordinateViewHolder, position: Int) {
        val coordinate = coordinateList[position]
        val text = "Lat: ${coordinate.latitude}, Lng: ${coordinate.longitude}"
        holder.textViewCoordinate.text = text

        // Highlight the selected coordinate
        holder.itemView.isSelected = coordinate == selectedCoordinate
    }

    override fun getItemCount(): Int {
        return coordinateList.size
    }

    fun setSelectedCoordinate(selectedCoordinate: LatLng) {
        this.selectedCoordinate = selectedCoordinate
        notifyDataSetChanged()
    }
}