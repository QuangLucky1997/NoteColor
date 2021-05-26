package com.androiddev97.noteapp2021.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androiddev97.noteapp2021.R
import com.androiddev97.noteapp2021.data.model.Note
import com.androiddev97.noteapp2021.ui.adapter.NoteAdapter
import com.androiddev97.noteapp2021.ui.main.view.SearchActivity
import com.androiddev97.noteapp2021.ui.main.viewmodel.NoteViewModel
import com.example.awesomedialog.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_note.view.*


class FragmentNote : Fragment(), NoteAdapter.OnItemClickListener {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)
        view.fab.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fragmentNote_to_fragmentNewNote)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setUpRecycleView()
        // getRecordNotes()
        getAllNotes()
        imgSearch.setOnClickListener {
            val intentSearch = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intentSearch)
        }
    }

    private fun getAllNotes() {
        noteViewModel.readAllNotes.observe(requireActivity(), { notes ->
            noteAdapter.setData(notes)
        })
    }

//    private fun getRecordNotes() {
//        noteViewModel.getCount()!!.observe(requireActivity(),{
//            text_view_number_note.text = toString()
//        })
//    }


    private fun setUpRecycleView() {
        noteAdapter = NoteAdapter(this)
        recycle_view_note.adapter = noteAdapter
        recycle_view_note.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
    }

    override fun CLickItem(notes: Note) {
        AwesomeDialog.build(requireActivity()).title("Delete Note!!").body(
            "\n" +
                    "Are you sure to delete this note?\n"
        ).icon(R.drawable.bin)
            .onPositive("Yes") {
                noteViewModel.deleteData(notes)
            }.onNegative("Cancel")
            {

            }
    }

    override fun ClickCard(notes: Note) {
        val noteUpdate = Note(notes.id, notes.title, notes.description, notes.date)
        val direction = FragmentNoteDirections
            .actionFragmentNoteToFragmentUpdateNote(noteUpdate)
        view?.findNavController()?.navigate(direction)


    }

}