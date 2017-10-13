package com.droidsmith.petri.data.services

import android.app.Service
import android.content.Intent
import android.os.IBinder


class MapService: Service() {



    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }



    override fun onCreate() {

        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}