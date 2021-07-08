package com.arpan.collegebroker

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arpan.collegebroker.fragment.allflats.IconFragment

class ViewPagerAdapter(private val fragments: ArrayList<IconFragment>, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "All Flats"
            1 -> "Add Flat"
            else -> "Lol"
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}