package com.arpan.collegebroker.mate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpan.collegebroker.CreateFlatActivity
import com.arpan.collegebroker.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_find_mate.*

class FindMate : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<MatePost>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_mate)

        back_image.setOnClickListener {
            startActivity(Intent(this,CreateFlatActivity::class.java))
        }

        arrayList=ArrayList()
        recyclerView=findViewById(R.id.recycler_mate_items)
        recyclerView.layoutManager= LinearLayoutManager(this)


        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("mates")
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    arrayList.clear()
                    for(i in p0.children){
                        for (j in i.children){
                            val mate=j.getValue(MatePost::class.java)

                            arrayList.add(mate!!)


                        }




                    }
                    recyclerView.adapter = MatesAdapter(arrayList,this@FindMate)


                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //val sell_req=FirebaseDatabase.getInstance().reference.child("availablebuyers").child(curruserID).child("requirements")

            }
        })





    }
}