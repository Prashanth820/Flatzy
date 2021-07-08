package com.arpan.collegebroker.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.arpan.collegebroker.CreateFlatActivity
import com.arpan.collegebroker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*
import kotlin.collections.HashMap

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private val userMap = HashMap<String, Any>()
    private var token: String =""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        token = Objects.requireNonNull(task.result)!!.getToken()
                    }
                }

        auth = FirebaseAuth.getInstance()
        back_image.setOnClickListener {
            startActivity(Intent(this,CreateFlatActivity::class.java))
        }

        stud_info_submit.setOnClickListener {
            saveUserInfo(stud_name.text.toString(),stud_college.text.toString(),
             stud_branch.text.toString(),stud_phone.text.toString()
            ,stud_email.text.toString())
        }

    }




    private fun saveUserInfo(name: String, college: String, branch: String,phone:String,email:String) {
        val currentUserId = FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("mates")



        userMap["name"] = name
        userMap["college"] = college
        userMap["branch"] = branch

        userMap["phone"] = phone
        userMap["email"] = email
        userMap["token"]=token



            usersRef.child(currentUserId).push().setValue(userMap)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this,CreateFlatActivity::class.java))


                        }
                    }
        }





}