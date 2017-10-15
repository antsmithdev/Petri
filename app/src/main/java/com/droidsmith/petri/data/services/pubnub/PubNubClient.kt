package com.droidsmith.petri.data.services.pubnub

import android.os.Binder
import android.util.Log
import org.json.JSONObject
import java.util.*


class PubNubClient(val service: PubNubService): Binder() {


    fun getPetriService(): PubNubClient = this@PubNubClient

    fun publishFeed(channel: String, message: FeedMessage){

        try{

            val messageJSON = JSONObject()
            messageJSON.put(FeedMessage.DEVICETAG,message.deviceTag)
            messageJSON.put(FeedMessage.FROM,message.from)
            messageJSON.put(FeedMessage.SENT_ON, Date().time)
            messageJSON.put(FeedMessage.TYPE,message.type)
            messageJSON.put(FeedMessage.MESSAGE_CONTENT,message.messageContent)
            messageJSON.put(FeedMessage.DEVICETAG,message.deviceTag)

            service.getPubNub().publish()
                    .message(messageJSON)
                    .channel(channel)
                    .shouldStore(true)
                    .usePOST(true)
                    .async(PetriCallback())
        } catch (e: Exception){
            Log.e("messageJson","Exception thrown")
        }


    }

    fun isConnected() = service.isConnected

    fun stopPNService(){
        service.stopSelf()
    }















}