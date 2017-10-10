package com.droidsmith.petri.util

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.droidsmith.petri.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions


fun GoogleMap.setStyle(context: Context, resource: Int){
    try {
        // Customise the styling of the base map using a JSON object defined
        // in a raw resource file.
        val success = this.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        context, resource)
        )

        if (!success) {
            Log.e("MapCallback", "Style parsing failed.")
        }
    } catch (e: Resources.NotFoundException) {
        Log.e("MapCallback", "Can't find style. Error: ", e)
    }
}