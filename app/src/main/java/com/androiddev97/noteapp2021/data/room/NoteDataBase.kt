package com.androiddev97.noteapp2021.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddev97.noteapp2021.data.dao.NoteDao
import com.androiddev97.noteapp2021.data.model.Note

@Database(entities = [Note::class],version = 1)
abstract class NoteDataBase : RoomDatabase(){
    abstract  fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDataBase?= null
        private const val DB_NAME = "note_db"

        fun getDatabase(context: Context): NoteDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}