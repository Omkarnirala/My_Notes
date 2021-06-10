package com.example.mynotes.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mynotes.DAO.NotesDAO;
import com.example.mynotes.DataBase.DatabaseNotes;
import com.example.mynotes.model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDAO notesDAO;
    public LiveData<List<Notes>> getAllNotes;


    public NotesRepository(Application application){

        DatabaseNotes databaseNotes = DatabaseNotes.getDatabaseInstance(application);
        notesDAO = databaseNotes.notesDAO();
        getAllNotes = notesDAO.getAllNotes();

    }

    public void insertNotes(Notes notes){
        notesDAO.insertNotes(notes);
    }

    public void DeleteNotes(int id){
        notesDAO.DeleteNotes(id);
    }

    public void UpdateNotes(Notes notes){
        notesDAO.UpdateNotes(notes);

    }
}
