package com.arpan.collegebroker.requests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.arpan.collegebroker.CreateFlatActivity
import com.arpan.collegebroker.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_requests.*

class RequestsActivity : AppCompatActivity() {

    val auth: FirebaseAuth = FirebaseAuth.getInstance();
    internal  var selectedFragment: Fragment?=null
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                selectedFragment = RoomRequestsFragment()
            }

            R.id.mate -> {
                selectedFragment = MateRequestFragment()
            }


        }
        if(selectedFragment!=null){
            supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment!!
            ).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requests)

        back_image.setOnClickListener {
            startActivity(Intent(this,CreateFlatActivity::class.java))
        }

        val navView: BottomNavigationView =findViewById(R.id.nav_view)
        //val myToolbar = findViewById<Toolbar>(R.id.Maintoolbar)
        //myToolbar.title = ""
        //setSupportActionBar(myToolbar)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                RoomRequestsFragment()
        ).commit()

    }
}