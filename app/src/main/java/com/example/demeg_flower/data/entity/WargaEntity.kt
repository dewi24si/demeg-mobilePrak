package com.example.demeg_flower.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warga")
data class WargaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaWarga: String,
    val jabatan: String,
    val avatarUrl: String
)
