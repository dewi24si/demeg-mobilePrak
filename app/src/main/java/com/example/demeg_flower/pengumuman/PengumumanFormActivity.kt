package com.example.demeg_flower.pengumuman

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demeg_flower.data.AppDatabase
import com.example.demeg_flower.data.entity.PengumumanEntity
import com.example.demeg_flower.databinding.ActivityPengumumanFormBinding
import kotlinx.coroutines.launch

class PengumumanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengumumanFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengumumanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Pengumuman"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Init DB
        db = AppDatabase.getInstance(this)

        binding.btnSavePengumuman.setOnClickListener {
            val judul = binding.etJudul.text.toString().trim()
            val isi = binding.etIsi.text.toString().trim()

            if (judul.isNotBlank() && isi.isNotBlank()) {
                lifecycleScope.launch {
                    val pengumuman = PengumumanEntity(
                        judul = judul,
                        isi = isi,
                        createdAt = System.currentTimeMillis()
                    )
                    db.pengumumanDao().insert(pengumuman)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
