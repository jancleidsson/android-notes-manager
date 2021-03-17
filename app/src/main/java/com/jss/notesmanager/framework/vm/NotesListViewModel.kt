package com.jss.notesmanager.framework.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jss.core.data.Note
import com.jss.notesmanager.framework.UseCases
import com.jss.notesmanager.framework.di.ApplicationModule
import com.jss.notesmanager.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesListViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var useCases: UseCases
    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notesList = MutableLiveData<List<Note>>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    fun getNotes() {
        coroutineScope.launch {
            val notes = useCases.getAllNotes()
            notes.forEach { it.wordCount = useCases.getWorldCount.invoke(it) }
            notesList.postValue(notes)
        }
    }
}
