package com.androiddev97.noteapp2021.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.androiddev97.noteapp2021.R
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.ui.main.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_new_note.*
import kotlinx.android.synthetic.main.fragment_new_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentNewNote : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //supportActionBar?.setDisplayShowTitleEnabled(false)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        view.imgBack.setOnClickListener {
            insertNote()
            Navigation.findNavController(view).navigate(R.id.action_fragmentNewNote_to_fragmentNote)
        }
    }

    private fun insertNote() {
        val getTitle = editTextTitle.text.toString()
        val getDescription = editTextDescription.text.toString()
        val getDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        if (!TextUtils.isEmpty(getTitle) && !TextUtils.isEmpty(getDescription)) {
            val noteInsert = Note(0, getTitle, getDescription, getDate)
            noteViewModel.insertData(noteInsert)
        }
    }

}