package com.example.demeg_flower.pengumuman

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.demeg_flower.BaseActivity
import com.example.demeg_flower.data.AppDatabase
import com.example.demeg_flower.data.entity.PengumumanEntity
import com.example.demeg_flower.databinding.ActivityPengumumanFormBinding
import com.example.demeg_flower.utils.NotificationHelper
import com.example.demeg_flower.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class PengumumanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengumumanFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengumumanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Pengumuman"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

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

                    val intent = Intent(this@PengumumanFormActivity, BaseActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        putExtra("navigate_to", "pengumuman")
                    }
                    NotificationHelper.showNotification(
                        context = this@PengumumanFormActivity,
                        title = "Pengumuman Baru",
                        message = "\"$judul\" berhasil ditambahkan. Klik untuk lihat.",
                        intent = intent
                    )

                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSetReminder.setOnClickListener {
            val judul = binding.etJudul.text.toString().trim()
            val menitStr = binding.etReminderMenit.text.toString().trim()

            if (judul.isBlank()) {
                Toast.makeText(this, "Isi judul pengumuman dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (menitStr.isBlank()) {
                Toast.makeText(this, "Masukkan jumlah menit reminder!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val menit = menitStr.toIntOrNull()
            if (menit == null || menit <= 0) {
                Toast.makeText(this, "Jumlah menit tidak valid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, menit)
            }

            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder Pengumuman",
                message = "Jangan lupa tindak lanjuti: \"$judul\"",
                targetActivity = BaseActivity::class.java,
                navigateTo = "pengumuman"
            )

            Toast.makeText(
                this,
                "Reminder akan muncul dalam $menit menit!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}