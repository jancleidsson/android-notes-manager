package com.jss.notesmanager.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import com.jss.notesmanager.databinding.FragmentNotesListBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jss.notesmanager.framework.vm.NotesListViewModel

/**
 * Represents notes list fragment view
 */
class NotesListFragment : Fragment(), ListAction{

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NotesListViewModel
    private val notesListAdapter =  NotesListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }
        observeViewModel()
        binding.createNotesBtn.setOnClickListener { goToNoteDetails() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun observeViewModel() {
        viewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
        viewModel.notesList.observe(
            this,
            Observer { notesList ->
                binding.loadingView.visibility = View.GONE
                binding.notesListView.visibility = View.VISIBLE
                notesListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
            }
        )
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action: NavDirections = NotesListFragmentDirections.actionNotesListToNote(id)
        Navigation.findNavController(binding.notesListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}