package com.example.jonas_hultn.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hepsi.repository.local.DBs.MessageDb
import com.example.jonas_hultn.repository.local.callbacks.MessageDao

@Database(entities = [MessageDb::class],version = 3 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun createMessageDao(): MessageDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "nouri")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}