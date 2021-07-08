package com.arpan.collegebroker.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arpan.collegebroker.Notifications.FcmNotificationsSender
import com.arpan.collegebroker.Notifications.PushNotificationsActivity
import com.arpan.collegebroker.R
import com.arpan.collegebroker.dataclass.User
import com.arpan.collegebroker.payments.PaymentActivity

import kotlinx.android.synthetic.main.room_request_iem.view.*

class Room_Request_Adapter(val arraylist: ArrayList<User>, val context: Context):RecyclerView.Adapter<Room_Request_Adapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bindItem(model: User, position: Int){

            itemView.user_name.text=model.name
            itemView.user_email.text=model.email
            itemView.user_phone.text=model.phone


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.room_request_iem, parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return  arraylist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.accept_pay.setOnClickListener {
            val intent = Intent(context,PaymentActivity::class.java)
            context.startActivity(intent)

        }
        holder.bindItem(arraylist[position], position)

    }

}