package com.androiddev97.noteapp2021.ui.main.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androiddev97.noteapp2021.R

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(findViewById(R.id.toolbar))
    }




}






