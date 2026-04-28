package com.example.demeg_flower.pertemuan_2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.demeg_flower.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val alas = findViewById<EditText>(R.id.etAlas)
        val tinggi = findViewById<EditText>(R.id.etTinggi)
        val sisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnHitungKubus)

        val hasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)
        val hasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        btnSegitiga.setOnClickListener {
            val a = alas.text.toString().toDouble()
            val t = tinggi.text.toString().toDouble()
            val luas = 0.5 * a * t
            hasilSegitiga.text = "Hasil: $luas"
            Log.d("HITUNG", "Luas Segitiga = $luas")
        }

        btnKubus.setOnClickListener {
            val s = sisi.text.toString().toDouble()
            val volume = s * s * s
            hasilKubus.text = "Hasil: $volume"
            Log.d("HITUNG", "Volume Kubus = $volume")
        }
    }
}