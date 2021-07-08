package com.arpan.collegebroker.fragment.createflat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arpan.collegebroker.CreateFlatActivity

import com.arpan.collegebroker.R

class CreateFlatWelcomeFragment : Fragment(), CreateFlatActivity.SubmitCallbackListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_flat_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as CreateFlatActivity).submitCallbackListener = this
    }

    override fun submitFlatDetails(progress: Int): Any = "Null?"

    override fun getProgress() = 0

    override fun reset() {}

    override fun validateInputs() = true
}
