package com.arpan.collegebroker.requests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpan.collegebroker.R
import com.arpan.collegebroker.adapter.Room_Request_Adapter
import com.arpan.collegebroker.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MateRequestFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<User>
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_mate_request, container, false)


        auth= FirebaseAuth.getInstance()

        arrayList=ArrayList()
        recyclerView=view.findViewById(R.id.recycler_mate_request_items)
        recyclerView.layoutManager= LinearLayoutManager(context)

        val usersRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("mates").child(auth.currentUser!!.uid)


        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0!=null){
                    arrayList.clear()
                    for (i in p0.children){

                        val use=i.getValue(User::class.java)
                        arrayList.add(use!!)



                    }
                    recyclerView.adapter = Room_Request_Adapter(arrayList,context!!)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return view
    }


}