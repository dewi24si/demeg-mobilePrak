package com.example.demeg_flower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demeg_flower.data.dao.PengumumanDao
import com.example.demeg_flower.data.dao.WargaDao
import com.example.demeg_flower.data.entity.PengumumanEntity
import com.example.demeg_flower.data.entity.WargaEntity

@Database(
    entities = [WargaEntity::class, PengumumanEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wargaDao(): WargaDao
    abstract fun pengumumanDao(): PengumumanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "binadesa_database"
                ).fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
