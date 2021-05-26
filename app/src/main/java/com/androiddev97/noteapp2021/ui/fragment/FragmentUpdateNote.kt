package com.androiddev97.noteapp2021.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.androiddev97.noteapp2021.R
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.ui.main.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update_note.*
import java.text.SimpleDateFormat
import java.util.*

class FragmentUpdateNote : Fragment() {
    private val args by navArgs<FragmentUpdateNoteArgs>()
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var currentNotes: Note
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setDataView()
        updateNotes()
    }

    private fun setDataView() {
        currentNotes = args.currentNote
        editTextTitleUpdate.setText(currentNotes.title)
        editTextDescriptionUpdate.setText(currentNotes.description)
    }

    private fun updateNotes() {
        imgUpdate.setOnClickListener {
            val titleUpdate = editTextTitleUpdate.text.toString().trim()
            val descriptionUpdate = editTextDescriptionUpdate.text.toString().trim()
            val getDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
            if (titleUpdate.isNotEmpty()) {
                val note = Note(currentNotes.id, titleUpdate, descriptionUpdate, getDate)
                noteViewModel.updateData(note)
                view?.findNavController()?.navigate(R.id.action_fragmentUpdateNote_to_fragmentNote)
            }
        }
    }
}