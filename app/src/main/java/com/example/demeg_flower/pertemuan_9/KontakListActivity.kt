package com.example.demeg_flower.pertemuan_9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demeg_flower.databinding.ActivityKontakListBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Pertemuan 9 – ListView dengan CustomAdapter
 * Menampilkan daftar kontak perangkat desa lengkap dengan foto (Glide), nama, dan jabatan.
 */
class KontakListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKontakListBinding

    private val kontakList = listOf(
        KontakModel("Bapak Sutrisno", "Kepala Desa", "https://avatar.iran.liara.run/public/1"),
        KontakModel("Ibu Ratna Sari", "Sekretaris Desa", "https://avatar.iran.liara.run/public/2"),
        KontakModel("Bapak Joko Widodo", "Kepala Dusun 1", "https://avatar.iran.liara.run/public/3"),
        KontakModel("Ibu Siti Aminah", "Bendahara Desa", "https://avatar.iran.liara.run/public/4"),
        KontakModel("Bapak Ahmad Fauzi", "Staff Pengaduan", "https://avatar.iran.liara.run/public/5"),
        KontakModel("Ibu Dewi Lestari", "Staff Administrasi", "https://avatar.iran.liara.run/public/6"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKontakListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val adapter = KontakAdapter(this, kontakList)
        binding.listViewKontak.adapter = adapter

        binding.listViewKontak.setOnItemClickListener { _, _, position, _ ->
            val data = kontakList[position]
            Snackbar.make(
                binding.root,
                "${data.nama} - ${data.jabatan}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
