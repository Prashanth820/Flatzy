package com.arpan.collegebroker.Notifications

import android.app.Activity
import android.content.Context
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class FcmNotificationsSender(var userFcmToken: String, var title: String, var body: String, var mContext: Context){//, var mActivity: Activity) {
    private var requestQueue: RequestQueue? = null
    private val postUrl = "https://fcm.googleapis.com/fcm/send"
    private val fcmServerKey = "AAAAMwMc-4E:APA91bFUeEMMzfZ_cb0pdrhdPrPaZFlznNHK0LfOPBqstnMzXZQ2dJAHQrR9W0BKyNFRuDppCfKIPe8VWfRJp2wzkz3D3iIMJpvvw7RWUDI3r4HUxh-UJrpi1XWMiUqChO4PkB6XjBI5"
    fun SendNotifications() {
        requestQueue = Volley.newRequestQueue(mContext)
        val mainObj = JSONObject()
        try {
            mainObj.put("to", userFcmToken)
            val notiObject = JSONObject()
            notiObject.put("title", title)
            notiObject.put("body", body)
            notiObject.put("icon", "house") // enter icon that exists in drawable only
            mainObj.put("notification", notiObject)
            val request: JsonObjectRequest = object : JsonObjectRequest(Method.POST, postUrl, mainObj, Response.Listener {
                // code run is got response
            }, Response.ErrorListener {
                // code run is got error
            }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val header: MutableMap<String, String> = HashMap()
                    header["content-type"] = "application/json"
                    header["authorization"] = "key=$fcmServerKey"
                    return header
                }
            }
            requestQueue!!.add(request)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}