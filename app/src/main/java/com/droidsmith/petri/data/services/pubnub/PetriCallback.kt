package com.droidsmith.petri.data.services.pubnub

import android.util.Log
import com.pubnub.api.callbacks.PNCallback
import com.pubnub.api.models.consumer.PNPublishResult
import com.pubnub.api.models.consumer.PNStatus


class PetriCallback : PNCallback<PNPublishResult>() {



    override fun onResponse(result: PNPublishResult?, status: PNStatus?) {

        if(status!!.isError){
            Log.e("callbackError","Exception thrown")
        }else{
            Log.d("callbackSuccess","Exception thrown")
        }
    }


}