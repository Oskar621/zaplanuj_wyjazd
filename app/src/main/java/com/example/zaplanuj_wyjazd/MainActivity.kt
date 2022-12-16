 package com.example.zaplanuj_wyjazd

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

 class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
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

        val firstDay = mutableListOf(0,0,0)
        val lastDay = mutableListOf(0,0,0)
        val selDate = arrayListOf(zmienNaDate(kalendarz.date)[0],zmienNaDate(kalendarz.date)[1],zmienNaDate(kalendarz.date)[2])
        kalendarz.setOnDateChangeListener{ _, d, m, y ->
            selDate[0] = d
            selDate[1] = m+1
            selDate[2] = y
        }
        poczWyj.setOnClickListener {
            if(lastDay[0] <= selDate[0] || lastDay[1] <= selDate[1] || lastDay[2] <= selDate[2]) for (i in 0 until 3)
                firstDay[i] = selDate[i]
            poczTxt.text = "Poczatek Wyjazdu: ${System.lineSeparator()}${firstDay[0]}-${firstDay[1]}-${firstDay[2]}"


            if(lastDay[0] != 0 && firstDay[0] != 0)
                if(firstDay[2] > lastDay[2] && firstDay[1] == lastDay[1])
                    Toast.makeText(applicationContext, "Nie mozesz najpierw wrocic potem wyjechac", Toast.LENGTH_SHORT).show()
                else
                    policz(lastDay, firstDay, iloscDni)
        }


        konWyj.setOnClickListener {
            for (i in 0 until 3)
                lastDay[i] = selDate[i]
            konTxt.text = "Koniec Wyjazdu: ${System.lineSeparator()}${lastDay[0]}-${lastDay[1]}-${lastDay[2]}"


            if(lastDay[0] != 0 && firstDay[0] != 0)
                if(firstDay[2] > lastDay[2] && firstDay[1] == lastDay[1])
                    Toast.makeText(applicationContext, "Nie mozesz najpierw wrocic potem wyjechac", Toast.LENGTH_SHORT).show()
                else
                    policz(lastDay, firstDay, iloscDni)
        }
    }

     @SuppressLint("SetTextI18n")
     private fun policz(lastDay: MutableList<Int>, firstDay : MutableList<Int>, TxtView : TextView) {
         val departureDay = (firstDay[0]*360) + (firstDay[1]*30) + firstDay[2]
         val backDay = (lastDay[0]*360) + (lastDay[1]*30) + lastDay[2]
         val diff =  departureDay.toChar() - backDay.toChar()
         TxtView.text = "Ilosc dni: ${System.lineSeparator()}${diff.absoluteValue+1}"
     }

     @SuppressLint("SimpleDateFormat")
     private fun zmienNaDate(czas : Long): List<Int> {
         val date = Date(czas)
         val dateFormat = SimpleDateFormat("yyyy/MM/dd")
         val formDate = dateFormat.format(date).split("/").map {
             it.toInt()
         }
         return formDate
     }
}
