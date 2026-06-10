package com.example.demeg_flower.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.demeg_flower.data.entity.WargaEntity

@Dao
interface WargaDao {
    @Query("SELECT * FROM warga")
    suspend fun getAll(): List<WargaEntity>

    @Insert
    suspend fun insert(warga: WargaEntity)

    @Delete
    suspend fun delete(warga: WargaEntity)
}
