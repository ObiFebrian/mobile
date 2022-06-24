package com.obi.project_1901010156.kotlin.database

import android.content.Context
import androidx.core.app.NavUtils
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        @JvmStatic
        fun getDatabase (context: Context) : NoteDatabase{
            if (INSTANCE == null){
                synchronized(NoteDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteDatabase
        }
    }
}