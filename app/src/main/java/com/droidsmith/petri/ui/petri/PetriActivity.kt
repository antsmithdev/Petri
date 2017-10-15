package com.droidsmith.petri.ui.petri

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewAnimationUtils
import com.droidsmith.petri.R
import com.droidsmith.petri.data.services.pubnub.PubNubService
import com.droidsmith.petri.databinding.ActivityMainBinding
import com.droidsmith.petri.ui.common.ConnectionFragment
import com.droidsmith.petri.ui.petri.adapters.TabPagerAdapter
import com.droidsmith.petri.util.ddiv
import com.droidsmith.petri.util.setStyle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class PetriActivity : AppCompatActivity(), HasSupportFragmentInjector, OnMapReadyCallback {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var map: GoogleMap
    private var isChatVisible = false
    private var conFragment: ConnectionFragment? = null
    private var pubnubConnection: ServiceConnection? = null


    //==========================
    //Activity Lifecycle
    //==========================
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        createConnectionFragment()
        pubnubConnection = conFragment?.connection


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.viewPager.adapter = TabPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.chatFab.setOnClickListener { handleClickAnim(it as FloatingActionButton) }
        binding.testButton.setOnClickListener{ sendPubNubTest() }

    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, PubNubService::class.java)
        bindService(intent, pubnubConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        if(isFinishing){
            val fm = supportFragmentManager
            fm.beginTransaction().remove(conFragment).commit()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(pubnubConnection)
    }


    //==========================
    //Map
    //==========================
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setStyle(this,R.raw.style_json)

        val sanfran = LatLng(37.790162, -122.393864)
        map.addMarker(MarkerOptions().position(sanfran).title("Marker in San Francisco"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sanfran,16f))
    }



    //==========================
    //Retained/Headless fragment
    //==========================
    private fun createConnectionFragment(){
        val fm = supportFragmentManager
        conFragment = fm.findFragmentByTag("conf") as ConnectionFragment?

        if(conFragment == null){
            conFragment = ConnectionFragment()
            fm.beginTransaction().add(conFragment,"conf").commit()
            fm.executePendingTransactions()
        }
    }

    private fun sendPubNubTest(){
        if(conFragment != null){
            conFragment?.testPubNub()
        }
    }




    //==========================
    //Show Feed/Chat toggle
    //==========================
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





    //==========================
    //Injection
    //==========================
    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentDispatchingAndroidInjector



}
