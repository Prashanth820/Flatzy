package com.arpan.collegebroker.payments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.arpan.collegebroker.R
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class PaymentActivity : AppCompatActivity(),PaymentResultListener {
    var btPay: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        btPay = findViewById(R.id.bt_pay)
        val sAmount = "100"
        val amount = Math.round(sAmount.toFloat() * 100)
        btPay?.setOnClickListener(View.OnClickListener {
            val checkout = Checkout()

            //set key id
            checkout.setKeyID("rzp_test_I8MHW62a3Lf597")
            checkout.setImage(R.drawable.rzp_logo)
            val `object` = JSONObject()
            try {
                `object`.put("name", "Prashanth Bijamwar")
                `object`.put("description", "Payment")
                `object`.put("theme.color", "#0093DD")
                `object`.put("currency", "INR")
                `object`.put("amount", amount)
                `object`.put("prefill.contact", "9307629562")
                `object`.put("prefill.email", "bprashanth8920@gmail.com")
                checkout.open(this, `object`)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        })
    }

    override fun onPaymentSuccess(s: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Payment ID")
        builder.setMessage(s)
        builder.show()
    }

    override fun onPaymentError(i: Int, s: String?) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }
}