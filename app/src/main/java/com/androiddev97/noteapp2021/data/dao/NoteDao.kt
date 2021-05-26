package com.androiddev97.noteapp2021.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.androiddev97.noteapp2021.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from Note")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE title LIKE :titleText OR description LIKE :titleText")
    fun searchNoteList(titleText: String): Flow<List<Note>>

    @Query("SELECT COUNT(*) FROM Note")
    fun getCount(): LiveData<Int?>?


    @Update(onConflict = REPLACE)
    suspend fun updateNote(note: Note)

    @Insert(onConflict = REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}