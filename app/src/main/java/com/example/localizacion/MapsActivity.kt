package com.example.localizacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.localizacion.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var standardButton: Button
    private lateinit var sateliteButton: Button
    private lateinit var hybridButton: Button
    private lateinit var polyLineButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        
        standardButton = findViewById(R.id.standard_map_button)
        sateliteButton = findViewById(R.id.satellite_map_button)
        hybridButton = findViewById(R.id.hybrid_map_button)
        polyLineButton = findViewById(R.id.poly_map_button)

        standardButton.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }

        sateliteButton.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }

        hybridButton.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        }

        polyLineButton.setOnClickListener {
            showPolyLine()
        }
    }

    private fun showPolyLine() {
        mMap.addPolyline(
            PolylineOptions().geodesic(true)
                .add(LatLng(20.73882, -103.40063))
                .add(LatLng(20.69676, -103.37541))
                .add(LatLng(20.67806, -103.34673))
                .add(LatLng(20.64047, -103.31154))
        )
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val colomos = LatLng(20.703449015037453, -103.39199606558483)
        mMap.addMarker(MarkerOptions().position(colomos).title("Lago de colomos"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colomos))
    }


}