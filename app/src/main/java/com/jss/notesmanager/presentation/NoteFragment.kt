package com.jss.notesmanager.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.jss.core.data.Note
import com.jss.notesmanager.R
import com.jss.notesmanager.databinding.FragmentNoteBinding
import com.jss.notesmanager.framework.vm.NoteViewModel

/**
 * Represents note fragment view
 */
class NoteFragment : Fragment() {

    private val args: NoteFragmentArgs by navArgs()
    private var noteId = 0L
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        binding.floatingActionButton.setOnClickListener {
            if (binding.titleEditText.text.toString().isNotEmpty() && binding.contentEditText.text.toString().isNotEmpty()) {
                updateCurrentNote()
                viewModel.saveNote(currentNote)
            } else Navigation.findNavController(it).popBackStack()
        }

        if(args.noteId != 0L) {
            noteId = args.noteId
            viewModel.getNote(noteId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_note -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context!!)
                            .setTitle(getString(R.string.delete_note))
                            .setMessage(getString(R.string.want_to_delete_note))
                            .setPositiveButton(getString(R.string.yes)) { _: DialogInterface?, _: Int -> viewModel.deleteNote(currentNote) }
                            .setNegativeButton(getString(R.string.cancel)) { _: DialogInterface?, _: Int ->  }
                            .show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateCurrentNote() {
        currentNote.title = binding.titleEditText.text.toString()
        currentNote.content = binding.contentEditText.text.toString()
        currentNote.updateTime = System.currentTimeMillis()
        if (currentNote.id == 0L) currentNote.creationTime = System.currentTimeMillis()
    }

    private fun displayCurrentNote(){
        binding.titleEditText.setText(currentNote.title, TextView.BufferType.EDITABLE)
        binding.contentEditText.setText(currentNote.content, TextView.BufferType.EDITABLE)
    }

    private fun observeViewModel() {
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        viewModel.saved.observe( this, Observer {
             if (it) {
                 Toast.makeText( context, "Done !", Toast.LENGTH_SHORT).show()
                 hideKeyBoard()
                 Navigation.findNavController(binding.titleEditText).popBackStack()
             } else Toast.makeText( context, "Something went wrong", Toast.LENGTH_SHORT).show()
        })

        viewModel.currentNote.observe(this, Observer {
            it?.let {
                currentNote = it
                displayCurrentNote()
            }
        })
    }

    private fun hideKeyBoard() {
        (context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).also {
            it.hideSoftInputFromWindow(binding.contentEditText.windowToken, 0)
        }
    }
}