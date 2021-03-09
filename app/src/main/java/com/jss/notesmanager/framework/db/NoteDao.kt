package com.jss.notesmanager.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert (onConflict = REPLACE)
    suspend fun addNodeEntity(noteEntity: NoteEntity)

    @Query ("SELECT * FROM 'note' WHERE ID = :id")
    suspend fun getNoteEntity(id: Long): NoteEntity?

    @Query("SELECT * FROM 'note'")
    suspend fun getAllNotesEntity(): List<NoteEntity>

    @Delete
    suspend fun deleteNoteEntity(noteEntity: NoteEntity)
}