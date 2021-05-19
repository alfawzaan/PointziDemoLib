package com.fawzaan.demofablib

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class CustomFab : FloatingActionButton, View.OnClickListener {
    /** Core Items */
    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var defStyleAttr = 0

    //    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context) : super(context) {
        this.mContext = context
        setOnClickListener(this)
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        this.attrs = attrs
        setOnClickListener(this)
    }


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.mContext = context
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
        setOnClickListener(this)
    }


    companion object {
        private lateinit var appCompatActivity: AppCompatActivity
        private var currentDate = ""
        private var currentTime = ""
        private val INSTALLED_DATE_TAG = "installed_date"
        private val FIRST_LAUNCH_TAG = "first_launch"
        private val PREF_NAME_TAG = "modal_testing"
        val DATE_BUNDLE_TAG = "date"
        val TIME_BUNDLE_TAG = "time"
        val INSTALLED_BUNDLE_TAG = "installed_date"

        fun initialize(appCompatActivity: AppCompatActivity): CustomFab {
            this.appCompatActivity = appCompatActivity
            val sharedPreferences =
                this.appCompatActivity.getSharedPreferences(PREF_NAME_TAG, Context.MODE_PRIVATE)
            setInitialLaunch(sharedPreferences)
            return CustomFab(this.appCompatActivity)
        }

        fun getInstalledDate(): String {
            val sharedPreferences =
                this.appCompatActivity.getSharedPreferences(PREF_NAME_TAG, Context.MODE_PRIVATE)
            val isFirstLaunch = sharedPreferences.getBoolean(FIRST_LAUNCH_TAG, true)
            return if (isFirstLaunch) {
                setInitialLaunch(sharedPreferences)
                currentDate
            } else {
                sharedPreferences.getString(INSTALLED_DATE_TAG, currentDate)!!
            }
        }

        fun setInitialLaunch(sharedPreferences: SharedPreferences) {
            sharedPreferences.edit()
                .putString(INSTALLED_DATE_TAG, currentDate)
                .putBoolean(FIRST_LAUNCH_TAG, false)
                .apply()
        }
    }

    fun setInfo() {
        val date: Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        currentDate = dateFormat.format(date)
        val timeFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        currentTime = timeFormat.format(date)
    }

    override fun onClick(v: View?) {
        setInfo()
//        Toast.makeText(this.mContext, "Hello World", Toast.LENGTH_LONG).show()
        val fragmentManager = appCompatActivity.supportFragmentManager
        val customModal = CustomModal()
        val bundle = Bundle()
        bundle.putString(DATE_BUNDLE_TAG, currentDate)
        bundle.putString(TIME_BUNDLE_TAG, currentTime)
        bundle.putString(INSTALLED_BUNDLE_TAG, getInstalledDate())
        customModal.arguments = bundle
        customModal.show(fragmentManager, "custom_modal")
    }
}