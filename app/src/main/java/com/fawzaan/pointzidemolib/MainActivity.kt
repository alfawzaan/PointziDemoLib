package com.fawzaan.pointzidemolib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fawzaan.demofablib.CustomFab

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomFab.initialize(this)
    }
}