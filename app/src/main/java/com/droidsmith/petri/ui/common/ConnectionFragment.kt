package com.droidsmith.petri.ui.common

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.util.Log
import com.droidsmith.petri.data.services.pubnub.FeedMessage
import com.droidsmith.petri.data.services.pubnub.PubNubClient
import com.droidsmith.petri.data.services.pubnub.PubNubService
import java.util.*


/*
* A retained headless fragment meant to keep the
* service connection through configuration changes
*/
open class ConnectionFragment: Fragment() {

    lateinit var pnClient: PubNubClient

    val connection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            pnClient = service as PubNubClient
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("ConnectionFragment","Disconnecting from service")
        }

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun testPubNub(){
        val msg = FeedMessage()
        pnClient.publishFeed("Channel-jqu1hpzs7", msg.create())
    }


    override fun onDestroy() {
        super.onDestroy()
        pnClient.stopPNService()
    }
}