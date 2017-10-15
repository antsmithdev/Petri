package com.droidsmith.petri

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.droidsmith.petri.data.services.pubnub.PubNubService
import com.droidsmith.petri.injection.components.DaggerPetriApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class PetriApplication : Application(), HasActivityInjector {




    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>





    override fun onCreate() {
        super.onCreate()

        //val intent = Intent(this, PubNubService::class.java)
        //startService(intent)

        DaggerPetriApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

    }


    override fun activityInjector(): AndroidInjector<Activity>? = activityDispatchingAndroidInjector

}