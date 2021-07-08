package com.arpan.collegebroker.fragment.createflat


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arpan.collegebroker.CreateFlatActivity
import com.arpan.collegebroker.FurnishingItems

import com.arpan.collegebroker.R
import com.arpan.collegebroker.adapter.FurnishingSelectorAdapter
import kotlinx.android.synthetic.main.fragment_create_flat_furnishing.*

class CreateFlatFurnishingFragment : Fragment(), CreateFlatActivity.SubmitCallbackListener {

    private lateinit var furnishingSelectorAdapter: FurnishingSelectorAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_flat_furnishing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ((activity) as CreateFlatActivity).submitCallbackListener = this
        furnishingSelectorAdapter = FurnishingSelectorAdapter(context!!, FurnishingItems)
        furnishingSelectorRecyclerView.layoutManager = LinearLayoutManager(context)
        furnishingSelectorRecyclerView.adapter = furnishingSelectorAdapter
    }

    override fun submitFlatDetails(progress: Int): ArrayList<*> {
        return furnishingSelectorAdapter.furnishingCountArray
    }

    override fun getProgress() = 4

    override fun reset() {
        furnishingSelectorAdapter.reset()
    }

    override fun validateInputs() = furnishingSelectorAdapter.initialStateChanged
}
