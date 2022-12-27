package com.andreykaranik.homework3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TripFragment : Fragment(), HasCustomTitle {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editButton = view.findViewById<Button>(R.id.edit_trip_button)
        editButton.setOnClickListener {
            navigator().showEditTripFragment()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getTitleRes(): Int {
        return R.string.trip_fragment_title
    }
}