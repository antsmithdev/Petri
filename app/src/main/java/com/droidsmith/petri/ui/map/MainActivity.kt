package com.droidsmith.petri.ui.map

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import com.droidsmith.petri.R
import com.droidsmith.petri.databinding.ActivityMainBinding
import com.droidsmith.petri.ui.feed.FeedViewModel
import com.droidsmith.petri.ui.map.adapters.TabPagerAdapter
import com.droidsmith.petri.util.ddiv
import com.droidsmith.petri.util.setStyle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var isChatVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.viewPager.adapter = TabPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.chatFab.setOnClickListener { handleClickAnim(it as FloatingActionButton) }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setStyle(this,R.raw.style_json)

        val sanfran = LatLng(37.790162, -122.393864)
        mMap.addMarker(MarkerOptions().position(sanfran).title("Marker in San Francisco"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfran,16f))
    }


    private fun handleClickAnim(fab: FloatingActionButton){
        if(!isChatVisible){
            showChatLayout()
            fab.setImageResource(R.drawable.fab_down)
            isChatVisible = true
        }else{
            hideChatLayout()
            fab.setImageResource(R.drawable.fab_up)
            isChatVisible = false
        }

    }

    private fun showChatLayout() {
        val center_x = chatView.width ddiv 2
        val center_y = chatView.height.toDouble()

        val final_radius = Math.hypot(center_x, center_y)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val anim: Animator = ViewAnimationUtils
                    .createCircularReveal(
                            chatView,
                            center_x.toInt(),
                            center_y.toInt(),
                            0f,
                            final_radius.toFloat())
            chatView.visibility = View.VISIBLE
            anim.start()
        }
    }

    private fun hideChatLayout() {
        val center_x = chatView.width ddiv 2
        val center_y = chatView.height.toDouble()

        val initial_radius = Math.hypot(center_x, center_y)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val anim: Animator = ViewAnimationUtils
                    .createCircularReveal(
                            chatView,
                            center_x.toInt(),
                            center_y.toInt(),
                            initial_radius.toFloat(),
                            0f
                    )

            anim.addListener( object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator){
                    super.onAnimationEnd(animation)
                    chatView.visibility = View.INVISIBLE
                }
            })

            anim.start()
        }



    }











}
