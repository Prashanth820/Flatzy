package com.arpan.collegebroker.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arpan.collegebroker.R
import kotlinx.android.synthetic.main.item_furnishing.view.*
import java.util.*

class FurnishingSelectorAdapter(val context: Context,
                                private val itemsHashMap: HashMap<String, Int>)
    : RecyclerView.Adapter<FurnishingSelectorAdapter.FurnishingViewHolder>() {

    val furnishingCountArray = ArrayList<Int>(Collections.nCopies(itemsHashMap.size, 0))
    var initialStateChanged = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnishingViewHolder {
        return FurnishingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_furnishing, parent, false))
    }

    override fun getItemCount() = itemsHashMap.size

    override fun onBindViewHolder(holder: FurnishingViewHolder, position: Int) {
        holder.bindData(itemsHashMap.keys.elementAt(position), itemsHashMap.values.elementAt(position))
    }

    fun reset() {
        for (i in 0 until furnishingCountArray.size) {
            furnishingCountArray[i] = 0
        }

        notifyDataSetChanged()
    }

    inner class FurnishingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.counterPlusButton.setOnClickListener {
                initialStateChanged = true
                furnishingCountArray[adapterPosition]++
                itemView.furnitureCountLabel.text = furnishingCountArray[adapterPosition].toString()
            }

            itemView.counterMinusButton.setOnClickListener {
                initialStateChanged = true
                if (furnishingCountArray[adapterPosition] > 0) {
                    furnishingCountArray[adapterPosition]--
                    itemView.furnitureCountLabel.text = furnishingCountArray[adapterPosition].toString()
                }
            }

            this.setIsRecyclable(false)
        }

        fun bindData(name: String, resourceId: Int) {
            itemView.furnitureNameLabel.text = name
            itemView.imageHolder_RV.setImageResource(resourceId)
        }
    }

}

