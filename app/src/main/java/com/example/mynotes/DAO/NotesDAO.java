package com.example.mynotes.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.model.Notes;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM Database_notes")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Database_notes ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("SELECT * FROM Database_notes ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowToHigh();

    @Insert
    void insertNotes(Notes...notes);

    @Query("DELETE FROM database_notes WHERE id=:id")
    void DeleteNotes(int id);

    @Update
    void UpdateNotes(Notes notes);
}
