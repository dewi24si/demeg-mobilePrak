package com.example.demeg_flower.info

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demeg_flower.BaseActivity
import com.example.demeg_flower.data.AppDatabase
import com.example.demeg_flower.data.entity.WargaEntity
import com.example.demeg_flower.databinding.ActivityWargaFormBinding
import com.example.demeg_flower.utils.NotificationHelper
import kotlinx.coroutines.launch

class WargaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWargaFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWargaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Warga"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Init DB
        db = AppDatabase.getInstance(this)

        binding.btnSaveWarga.setOnClickListener {
            val nama = binding.etNamaWarga.text.toString().trim()
            val jabatan = binding.etJabatan.text.toString().trim()
            val avatarIndex = (1..70).random()
            val avatarUrl = "https://avatar.iran.liara.run/public/$avatarIndex"

            if (nama.isNotBlank() && jabatan.isNotBlank()) {
                lifecycleScope.launch {
                    val warga = WargaEntity(
                        namaWarga = nama,
                        jabatan = jabatan,
                        avatarUrl = avatarUrl
                    )
                    db.wargaDao().insert(warga)

                    // Notifikasi setelah simpan warga
                    val intent = Intent(this@WargaFormActivity, BaseActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        putExtra("navigate_to", "info")
                    }
                    NotificationHelper.showNotification(
                        context = this@WargaFormActivity,
                        title = "Data Warga Tersimpan",
                        message = "$nama berhasil ditambahkan sebagai $jabatan",
                        intent = intent
                    )

                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
