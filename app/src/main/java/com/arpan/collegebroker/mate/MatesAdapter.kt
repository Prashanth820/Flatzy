package com.arpan.collegebroker.mate

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arpan.collegebroker.Notifications.FcmNotificationsSender
import com.arpan.collegebroker.Notifications.PushNotificationsActivity
import com.arpan.collegebroker.R
import kotlinx.android.synthetic.main.mate_items.view.*


class MatesAdapter(val arraylist: ArrayList<MatePost>, val context: Context):RecyclerView.Adapter<MatesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindItem(model: MatePost, position: Int){

            itemView.student_name.text=model.name
            itemView.student_college.text=model.college
            itemView.student_branch.text=model.branch
            itemView.student_phone.text=model.phone
            itemView.student_email.text=model.email




        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.mate_items, parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return  arraylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var token=arraylist[position].token
        holder.itemView.mate_item_card.setOnClickListener {
            val intent = Intent(context, PushNotificationsActivity::class.java)
            intent.putExtra("name", arraylist[position].name)
            intent.putExtra("branch", arraylist[position].branch)

            context.startActivity(intent)
        }


        holder.itemView.request_mate.setOnClickListener {

            val notifsender= FcmNotificationsSender(token, "Mate request",
                    arraylist[position].name + " " + "Requested you to be his mate", context)
            notifsender.SendNotifications()
            holder.itemView.request_mate.text="Requested"


        }

        holder.bindItem(arraylist[position], position)

    }
}