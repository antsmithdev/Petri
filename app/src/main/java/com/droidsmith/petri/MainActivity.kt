package com.droidsmith.petri

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        styleMap(mMap)


        val sanfran = LatLng(37.790162, -122.393864)
        mMap.addMarker(MarkerOptions().position(sanfran).title("Marker in San Francisco"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfran,16f))
    }


    private fun styleMap(googleMap: GoogleMap){
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json))

            if (!success) {
                Log.e("MapCallback", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("MapCallback", "Can't find style. Error: ", e)
        }
    }








}
