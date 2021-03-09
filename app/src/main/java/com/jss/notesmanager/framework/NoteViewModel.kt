package com.jss.notesmanager.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jss.core.data.Note
import com.jss.core.repository.NoteRepository
import com.jss.core.usecase.AddNote
import com.jss.core.usecase.GetAllNotes
import com.jss.core.usecase.GetNote
import com.jss.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = NoteRepository(RoomNoteDataSource(application))
    val useCases = UseCases(
            AddNote(repository),
            GetNote(repository),
            GetAllNotes(repository),
            RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note) {
        coroutineScope.launch { useCases.addNote(note) }
        saved.postValue(true)
    }
}