package com.example.demeg_flower.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengumuman")
data class PengumumanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val isi: String,
    val createdAt: Long
)
