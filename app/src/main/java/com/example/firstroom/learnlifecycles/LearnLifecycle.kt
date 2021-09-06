package com.example.firstroom.learnlifecycles

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.widget.Chronometer
import androidx.annotation.RequiresApi
import com.example.firstroom.R

class LearnLifecycle : AppCompatActivity() {
    lateinit var chronometer: MyChronometer

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_lifecycle)
        chronometer = findViewById(R.id.meter)
        lifecycle.addObserver(chronometer)

        MyChronometer(this, null)

    }

//    override fun onPause() {
//        super.onPause()
//        elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
//        chronometer.stop()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
//        chronometer.start()
//
//    }
}