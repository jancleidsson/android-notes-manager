package com.jss.notesmanager.framework

import com.jss.core.usecase.AddNote
import com.jss.core.usecase.GetAllNotes
import com.jss.core.usecase.GetNote
import com.jss.core.usecase.RemoveNote

data class UseCases(
        val addNote: AddNote,
        val getAllNotes: GetNote,
        val getNote: GetAllNotes,
        val removeNote: RemoveNote)