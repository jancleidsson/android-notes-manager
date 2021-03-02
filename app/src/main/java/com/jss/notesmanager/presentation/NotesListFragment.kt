package com.jss.notesmanager.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jss.notesmanager.R

/**
 * A simple [Fragment] subclass.
 * Use the [NotesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }
}