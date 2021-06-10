package com.example.mynotes.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynotes.DAO.NotesDAO;
import com.example.mynotes.model.Notes;

@Database(entities = {Notes.class},version = 1)
public abstract class DatabaseNotes extends RoomDatabase {

    public abstract NotesDAO notesDAO();
    public static DatabaseNotes INSTANCE;

    public static DatabaseNotes getDatabaseInstance(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseNotes.class,
                    "DatabaseNotes").allowMainThreadQueries().build();
        }
            return INSTANCE;
    }

}
