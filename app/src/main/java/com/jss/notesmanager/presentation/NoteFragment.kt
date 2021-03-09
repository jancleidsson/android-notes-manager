package com.jss.notesmanager.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.jss.core.data.Note
import com.jss.notesmanager.databinding.FragmentNoteBinding
import com.jss.notesmanager.framework.NoteViewModel

/**
 * Represents note fragment view
 */
class NoteFragment : Fragment() {

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateCurrentNote() {
        currentNote.title = binding.titleEditText.text.toString()
        currentNote.content = binding.contentEditText.text.toString()
        currentNote.updateTime = System.currentTimeMillis()
        if (currentNote.id == 0L) currentNote.creationTime = System.currentTimeMillis()
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
    }

    private fun hideKeyBoard() {
        (context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).also {
            it.hideSoftInputFromWindow(binding.contentEditText.windowToken, 0)
        }
    }
}