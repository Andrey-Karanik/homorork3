package com.andreykaranik.homework3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EditTripFragment : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_trip, container, false)
    }

    override fun getTitleRes(): Int = R.string.edit_trip_fragment_title

}