package com.fawzaan.demofablib

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class CustomModal : DialogFragment() {

    var textViewCurrent: TextView? = null
    var installationTime: TextView? = null
    var date = ""
    var time = ""
    var installed = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_modal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        installationTime = view.findViewById(R.id.text_view_installation_time)
        installationTime?.text = "Library Installed on $installed"
        textViewCurrent = view.findViewById(R.id.text_view_time)
        textViewCurrent?.text = "$time $date"

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        date = arguments?.getString(CustomFab.DATE_BUNDLE_TAG)!!
        time = arguments?.getString(CustomFab.TIME_BUNDLE_TAG)!!
        installed = arguments?.getString(CustomFab.INSTALLED_BUNDLE_TAG)!!
        Log.i("INSTALLED", "Hello $installed")
    }
}