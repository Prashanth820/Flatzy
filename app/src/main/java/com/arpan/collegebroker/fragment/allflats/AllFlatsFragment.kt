package com.arpan.collegebroker.fragment.allflats


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.arpan.collegebroker.Flat
import com.arpan.collegebroker.FlatsAdapter
import com.arpan.collegebroker.Prefs
import com.arpan.collegebroker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_all_flats.*


class AllFlatsFragment : IconFragment(), FlatsAdapter.FavouriteListener {

    private lateinit var allFlatsAdapter: FlatsAdapter
    private val flats = ArrayList<Flat>()


    private val flatsReference = FirebaseDatabase.getInstance().reference.child("flats")
    private val currentUserReference = FirebaseDatabase.getInstance().reference.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid)

    private var userFavouriteFlats = ArrayList<String>()

    private lateinit var prefs: Prefs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        prefs = Prefs(context!!)
        val root = inflater.inflate(R.layout.fragment_all_flats, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        flats.clear()
        allFlatsAdapter = FlatsAdapter(context, flats)
        allFlatsRV.setEmptyView(allFlatsPlaceHolder)
        allFlatsRV.adapter = allFlatsAdapter
        allFlatsAdapter.favouriteListener = this
        allFlatsAdapter.favouriteIdsList = userFavouriteFlats
        allFlatsRV.layoutManager = LinearLayoutManager(context)
//
//        userFavouriteFlats = prefs.prefs.getStringSet("favs", null)

//        updateUserFavs()
        updateFlats()
    }

    private fun updateFlats() {
        flatsReference.addChildEventListener(object : ChildEventListener {


            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                flats.add(p0!!.getValue(Flat::class.java)!!)
                allFlatsAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Error: ${p0!!.code}", Toast.LENGTH_SHORT).show()

            }

        })
    }

    override fun getIconDrawable(): Int {
        return R.drawable.numbered_list_24dp
    }



    override fun toggleFavourite(flatId: String, newState: Boolean) {
        currentUserReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                p0!!.children.forEach {
                    if (it.key == "favouriteFlatIds") {
                        userFavouriteFlats.clear()
                        userFavouriteFlats.addAll(it.value as ArrayList<String>)

                        if (userFavouriteFlats.contains(flatId)) userFavouriteFlats.remove(flatId)
                        else userFavouriteFlats.add(flatId)

                        currentUserReference.updateChildren(mapOf("favouriteFlatIds" to userFavouriteFlats))

                        allFlatsAdapter.notifyDataSetChanged()
                        return
                    }
                }

                //favouriteFlatIds not found, create it and update the list online
                userFavouriteFlats.add(flatId)
                currentUserReference.updateChildren(mapOf("favouriteFlatIds" to userFavouriteFlats))
                allFlatsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
