package com.example.demeg_flower.pertemuan_9

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityInformasiListBinding

/**
 * Pertemuan 9 – ListView dengan ArrayAdapter
 * Menampilkan daftar topik informasi desa memakai layout bawaan
 * android.R.layout.simple_list_item_1 (hanya 1 baris teks per item).
 */
class InformasiListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformasiListBinding

    private val dataList = listOf(
        "Tentang Aplikasi Bina Desa",
        "Cara Mengajukan Pengaduan",
        "Jam Pelayanan Kantor Desa",
        "Syarat Surat Pengantar",
        "Jadwal Musyawarah Desa",
        "Program Bantuan Sosial",
        "Struktur Perangkat Desa",
        "Peta Wilayah Desa",
        "Kontak Darurat",
        "Kebijakan Privasi Data Warga"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformasiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // ArrayAdapter menghubungkan dataList dengan layout simple_list_item_1
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            dataList
        )
        binding.listViewInformasi.adapter = adapter

        binding.listViewInformasi.setOnItemClickListener { _, _, position, _ ->
            val selected = dataList[position]
            Toast.makeText(this, "Buka topik: $selected", Toast.LENGTH_SHORT).show()
        }
    }
}
