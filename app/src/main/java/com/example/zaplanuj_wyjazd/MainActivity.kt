 package com.example.zaplanuj_wyjazd

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

 class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kalendarz = findViewById<CalendarView>(R.id.kalendarz)
        val poczWyj = findViewById<Button>(R.id.PoczWyjButt)
        val konWyj = findViewById<Button>(R.id.KonWyjButt)
        val poczTxt = findViewById<TextView>(R.id.DataPoczTXT)
        val konTxt = findViewById<TextView>(R.id.DataKonTXT)
        val iloscDni = findViewById<TextView>(R.id.IloscDniTXT)

        kalendarz.minDate = Date().time
        kalendarz.maxDate = Date().time + 63072000000

        val selDate = arrayListOf<Int>(zmienNaDate(kalendarz.date)[0],zmienNaDate(kalendarz.date)[1],zmienNaDate(kalendarz.date)[2])
        kalendarz.setOnDateChangeListener() { CalendarView,d,m,y ->
            selDate[0] = d
            selDate[1] = m+1
            selDate[2] = y
        }
        poczWyj.setOnClickListener {
        }

        konWyj.setOnClickListener {

        }
    }
     fun zmienNaDate(czas : Long): List<Int> {
         val date = Date(czas)
         val dateFormat = SimpleDateFormat("dd/MM/yyyy")
         val formDate = dateFormat.format(date).split("/").map {
             it.toInt()
         }
         return formDate;
     }
}
