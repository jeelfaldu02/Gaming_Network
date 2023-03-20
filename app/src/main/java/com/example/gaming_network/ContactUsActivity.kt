package com.example.gaming_network

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gaming_network.databinding.ActivityContactUsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ContactUsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap:GoogleMap
    private lateinit var binding: ActivityContactUsBinding

    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityContactUsBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        getCurrentLocationUser()
    }

    private fun getCurrentLocationUser() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
            return
        }

        val getLocation=fusedLocationProviderClient.lastLocation.addOnSuccessListener { location->
            if(location!=null){
                currentLocation=location

                val mapFragment=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==permissionCode && grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getCurrentLocationUser()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng= LatLng(currentLocation.latitude,currentLocation.longitude)
        val markerOptions= MarkerOptions().position(latLng).title("Current Location")

        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f))
        googleMap?.addMarker(markerOptions)
    }
}