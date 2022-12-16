 package com.example.zaplanuj_wyjazd

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kalendarz = findViewById<CalendarView>(R.id.kalendarz)

        kalendarz.minDate = Date().time
        kalendarz.maxDate = Date().time + 63072000000


    }
}