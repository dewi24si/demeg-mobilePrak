package com.example.demeg_flower.pertemuan_9

import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityFaqListBinding

/**
 * Pertemuan 9 – ListView dengan SimpleAdapter
 * Menampilkan daftar FAQ (pertanyaan + jawaban) memakai layout bawaan
 * android.R.layout.simple_list_item_2 (2 baris teks per item: text1 & text2).
 */
class FaqListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFaqListBinding

    private val faqList = listOf(
        mapOf("title" to "Bagaimana cara membuat pengaduan?", "desc" to "Buka menu Filter Pengaduan, isi form, lalu tekan Cari/Ajukan."),
        mapOf("title" to "Berapa lama pengaduan diproses?", "desc" to "Rata-rata 1-3 hari kerja tergantung kategori masalah."),
        mapOf("title" to "Apakah data saya aman?", "desc" to "Ya, data hanya diakses oleh perangkat desa yang berwenang."),
        mapOf("title" to "Bisakah pengaduan dibatalkan?", "desc" to "Bisa, hubungi kontak desa yang tertera di menu Kontak."),
        mapOf("title" to "Bagaimana cara reset password?", "desc" to "Gunakan tautan \"Lupa Password\" pada halaman Login."),
        mapOf("title" to "Apakah aplikasi ini gratis?", "desc" to "Ya, Bina Desa gratis digunakan oleh seluruh warga desa."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        // SimpleAdapter menghubungkan List<Map<String,String>> dengan layout simple_list_item_2
        val adapter = SimpleAdapter(
            this,
            faqList,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listViewFaq.adapter = adapter

        binding.listViewFaq.setOnItemClickListener { _, _, position, _ ->
            val item = faqList[position]
            Toast.makeText(this, "${item["title"]}: ${item["desc"]}", Toast.LENGTH_LONG).show()
        }
    }
}
