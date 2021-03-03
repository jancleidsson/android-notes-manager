package com.jss.notesmanager.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import com.jss.notesmanager.databinding.FragmentNotesListBinding
import androidx.navigation.Navigation
import com.jss.notesmanager.R

/**
 * Represents notes list fragment view
 */
class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createNotesBtn.setOnClickListener { goToNoteDetails() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action: NavDirections = NotesListFragmentDirections.actionNotesListToNote()
        Navigation.findNavController(binding.notesListView).navigate(action)
    }
}