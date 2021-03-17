package com.jss.notesmanager.framework

import com.jss.core.usecase.*

data class UseCases(
        val addNote: AddNote,
        val getAllNotes: GetAllNotes,
        val getNote: GetNote,
        val removeNote: RemoveNote,
        val getWorldCount: GetWordCount)