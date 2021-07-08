package com.arpan.collegebroker.Notifications

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arpan.collegebroker.CreateFlatActivity
import com.arpan.collegebroker.R
import com.arpan.collegebroker.dataclass.User
import com.arpan.collegebroker.gps.MapsActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_push_notifications.*
import kotlinx.android.synthetic.main.activity_push_notifications.book_room
import kotlinx.android.synthetic.main.activity_push_notifications.fetch_map
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.*


class PushNotificationsActivity : AppCompatActivity() {
    private var token:String=""
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()

        setContentView(R.layout.activity_push_notifications)
        //val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("mates").child(auth.currentUser.toString())
//        var token: String? =null
//
//        FirebaseMessaging.getInstance().subscribeToTopic("all")
//
//        FirebaseInstanceId.getInstance().instanceId
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                         token = Objects.requireNonNull(task.result)!!.getToken()
//                    }
//                }
//
//
//        send_message.setOnClickListener {
//           val notifsender=FcmNotificationsSender("/topics/all", title1.text.toString(),
//                   message1.text.toString(), applicationContext)//, this)
//            notifsender.SendNotifications()
//        }
//
//        send_message_One.setOnClickListener {
//            val notifsender=FcmNotificationsSender(token!!, title1.text.toString(),
//                    message1.text.toString(), applicationContext)//, this)
//            notifsender.SendNotifications()
//
//        }
//        usersRef.addValueEventListener(object :ValueEventListener{
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0!=null){
//                    for (i in p0.children){
//                        for (j in i.children){
//                            val name=i.getValue(User::class.java)
//                        }
//
//
//                    }
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        token = Objects.requireNonNull(task.result)!!.getToken()
                    }
                }

        book_room.setOnClickListener {
            val notifsender= FcmNotificationsSender(token!!, "Booking Request",
                    "Received Room Renting Request", applicationContext)//, this)
            notifsender.SendNotifications()
            Toast.makeText(this,"Booking Request sent", Toast.LENGTH_LONG).show();

            startActivity(Intent(this, CreateFlatActivity::class.java))
        }
        fetch_map.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }



        getIncomingIntent();

    }


    private fun getIncomingIntent() {

        if (intent.hasExtra("name") && intent.hasExtra("branch")) {

            val name = intent.getStringExtra("name")
            val branch = intent.getStringExtra("branch")
            setImage(name!!, branch!!)
        }
    }

    private fun setImage(name: String, branch: String) {


//        send_message.text=name
//        send_message_One.text=branch
    }


}

