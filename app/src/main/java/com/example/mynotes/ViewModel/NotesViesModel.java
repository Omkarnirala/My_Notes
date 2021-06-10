package com.example.mynotes.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.model.Notes;
import com.example.mynotes.repository.NotesRepository;

import java.util.List;

public class NotesViesModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViesModel(Application application) {
        super(application);

        notesRepository = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes;

    }

    public void insertNotes(Notes notes){
        notesRepository.insertNotes(notes);
    }

    public void DeleteNotes(int id){
        notesRepository.DeleteNotes(id);
    }

    public void UpdateNotes(Notes notes){
        notesRepository.UpdateNotes(notes);

    }
}
