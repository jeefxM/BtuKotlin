package com.example.fragmentproject.Fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentproject.R

class DashboardFragment: Fragment(R.layout.fragment_dashboard) {
    private lateinit var text: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text = view.findViewById(R.id.textView)
        val name = DashboardFragmentArgs.fromBundle(requireArguments()).name

        text.text = name
    }


}