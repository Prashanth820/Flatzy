package com.arpan.collegebroker.adapter


import android.content.Context
import android.net.Uri
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arpan.collegebroker.R
import com.bumptech.glide.Glide

//import junit.framework.TestSuite.warning

class ImageSliderAdapter(private val context: Context, private val images: ArrayList<String>) : PagerAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val myImageLayout = inflater.inflate(R.layout.slide, view, false)
        val myImage = myImageLayout
                .findViewById(R.id.image) as ImageView

        Glide
                .with(context)
                .load(Uri.parse(images[position]))
                .placeholder(R.drawable.house)
                .into(myImage)

        view.addView(myImageLayout, 0)
        return myImageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}