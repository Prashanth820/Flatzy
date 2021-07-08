package com.arpan.collegebroker

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.arpan.collegebroker.fragment.allflats.AllFlatsFragment
import com.arpan.collegebroker.fragment.allflats.FavouriteFlatsFragment
import com.arpan.collegebroker.fragment.allflats.IconFragment
import com.arpan.collegebroker.fragment.allflats.MyFlatsFragment
import kotlinx.android.synthetic.main.activity_all_flats.*

class AllFlatsActivity : AppCompatActivity() {

    private lateinit var mainFab: FloatingActionButton
    private lateinit var fragments: ArrayList<IconFragment>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_flats)

        fragments = arrayListOf(AllFlatsFragment(), MyFlatsFragment())
        viewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager)

        flatPagesViewPager.adapter = viewPagerAdapter
        flatsTabLayout.setupWithViewPager(flatPagesViewPager)

        var i = 0
        fragments.forEach {
            flatsTabLayout.getTabAt(i++)!!.icon = ContextCompat.getDrawable(this, it.getIconDrawable())
        }

    }
}