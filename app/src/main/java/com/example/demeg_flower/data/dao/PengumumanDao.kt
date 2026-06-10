package com.example.demeg_flower.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.demeg_flower.data.entity.PengumumanEntity

@Dao
interface PengumumanDao {
    @Query("SELECT * FROM pengumuman ORDER BY createdAt DESC")
    suspend fun getAll(): List<PengumumanEntity>

    @Insert
    suspend fun insert(pengumuman: PengumumanEntity)

    @Delete
    suspend fun delete(pengumuman: PengumumanEntity)
}
