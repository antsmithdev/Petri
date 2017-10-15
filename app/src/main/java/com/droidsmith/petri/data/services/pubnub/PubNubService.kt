package com.droidsmith.petri.data.services.pubnub

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.droidsmith.petri.R
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNLogVerbosity


class PubNubService: Service() {

    val listeners = mutableMapOf<String, MutableList<PetriCallback>>()

    lateinit var pubnub: PubNub

    var isConnected = false




    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY

    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service Starting...", Toast.LENGTH_SHORT).show()
        Log.d("PNService","STARTING!!!!!!!")
    }


    fun getPubNub(): PubNub {
        val pnConfig = PNConfiguration()
        pnConfig.subscribeKey = getString(R.string.pn_sub_key)
        pnConfig.publishKey = getString(R.string.pn_publish_key)
        pnConfig.logVerbosity = PNLogVerbosity.BODY //Debug
        pnConfig.subscribeTimeout = 200000
        pubnub = PubNub(pnConfig)

        return pubnub

    }


    override fun onBind(intent: Intent?): IBinder = PubNubClient(this)

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Shutting Down...", Toast.LENGTH_SHORT).show()
    }



}


























