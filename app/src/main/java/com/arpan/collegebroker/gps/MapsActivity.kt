package com.arpan.collegebroker.gps

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.arpan.collegebroker.R
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,TaskLoadedCallback {
    private var mMap: GoogleMap? = null
    private var place1: MarkerOptions? = null
    private var place2: MarkerOptions? = null
    var getDirection: Button? = null
    private var currentPolyline: Polyline? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        getDirection=findViewById(R.id.btnGetDirection)
        getDirection!!.setOnClickListener {
            FetchURL(this@MapsActivity).execute(getUrl(place1!!.position, place2!!.position, "driving"), "driving")
        }


        //place1 = MarkerOptions().position(LatLng(27.658143, 85.3199503)).title("Location 1")
        place1 = MarkerOptions().position(LatLng(19.202731, 77.825419)).title("Location 1")
        //place2 = MarkerOptions().position(LatLng(27.667491, 85.3208583)).title("Location 2")
        place2 = MarkerOptions().position(LatLng(19.221192, 77.669026)).title("Location 2")
        val mapFragment: MapFragment = fragmentManager
                .findFragmentById(R.id.mapNearBy) as MapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        Log.d("mylog", "Added Markers")
        mMap!!.addMarker(place1)
        mMap!!.addMarker(place2)

        val australiaBounds = LatLngBounds(
                LatLng(19.202731, (77.825419)),  // SW bounds
                LatLng(19.221192, (77.669026)) // NE bounds
        )
        mMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(australiaBounds, 15))





    }

    private fun getUrl(origin: LatLng, dest: LatLng, directionMode: String): String? {
        // Origin of route
        val str_origin = "origin=" + origin.latitude + "," + origin.longitude
        // Destination of route
        val str_dest = "destination=" + dest.latitude + "," + dest.longitude
        // Mode
        val mode = "mode=$directionMode"
        // Building the parameters to the web service
        val parameters = "$str_origin&$str_dest&$mode"
        // Output format
        val output = "json"
        // Building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key)
    }

    override fun onTaskDone(vararg values: Any?) {
        if (currentPolyline != null) currentPolyline!!.remove()
        currentPolyline = mMap!!.addPolyline(values[0] as PolylineOptions?)
    }
}