package com.droidsmith.petri.data.services.pubnub

import android.graphics.drawable.Drawable
import com.droidsmith.petri.ui.feed.FeedFragment
import org.json.JSONObject
import java.util.*


class FeedMessage {

    companion object {
        val DEVICETAG = "devicetag"
        val TYPE = "type"
        val FROM = "from"
        val SENT_ON = "sentOn"
        val EMOTICON = "emoticon"
        val MESSAGE_CONTENT = "messageContent"
        val MESSAGE_ID = "msgId"
        val SENDER_UUID = "uuid"
    }

    var deviceTag: String? = null
    var type: String? = null
    var from: String? = null
    var sentOn: Date? = null
    var emoticon: String? = null
    var messageContent: String? = null
    var senderUUID: String? = null


    fun create(): FeedMessage{

        val msg = FeedMessage()
        msg.messageContent = "This is a test"
        msg.from = "Anthony"
        msg.deviceTag = "my pc"

        return msg
    }

    //TODO: may change this to top level function
    @Throws(Exception::class)
    fun create(obj: JSONObject, timeToken: String): FeedMessage {
        val message = FeedMessage()
        val deviceTag = obj.getString(DEVICETAG)
        val type = obj.getString(TYPE)
        val from = obj.getString(FROM)
        val emoticon = obj.getString(EMOTICON)
        val messageContent = obj.getString(MESSAGE_CONTENT)
        val uuid = obj.getString(SENDER_UUID)

        message.deviceTag = deviceTag
        message.type = type
        message.from = from
        message.sentOn = Date()
        message.senderUUID = uuid
        message.emoticon = emoticon
        message.messageContent = messageContent

        return message
    }


}