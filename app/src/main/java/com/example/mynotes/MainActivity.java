package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mynotes.Activity.InsertNotesActivity;
import com.example.mynotes.Adapter.NotesAdapter;
import com.example.mynotes.ViewModel.NotesViesModel;
import com.example.mynotes.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViesModel notesViesModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    TextView noFilter, highToLow, lowToHigh;
    List<Notes> filterNotesAllList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecycler = findViewById(R.id.notesRecycler);

        noFilter = findViewById(R.id.nofilter);
        highToLow = findViewById(R.id.hightolow);
        lowToHigh = findViewById(R.id.lowtohigh);

        noFilter.setBackgroundResource(R.drawable.filter_ui);

        noFilter.setOnClickListener(v -> {
            loadData(0);

            noFilter.setBackgroundResource(R.drawable.filter_ui);
            highToLow.setBackgroundResource(R.drawable.filter_un);
            lowToHigh.setBackgroundResource(R.drawable.filter_un);

        });

        highToLow.setOnClickListener(v -> {
            loadData(1);

            noFilter.setBackgroundResource(R.drawable.filter_un);
            highToLow.setBackgroundResource(R.drawable.filter_ui);
            lowToHigh.setBackgroundResource(R.drawable.filter_un);

        });

        lowToHigh.setOnClickListener(v -> {
            loadData(2);

            noFilter.setBackgroundResource(R.drawable.filter_un);
            highToLow.setBackgroundResource(R.drawable.filter_un);
            lowToHigh.setBackgroundResource(R.drawable.filter_ui);

        });

        notesViesModel = ViewModelProviders.of(this).get(NotesViesModel.class);

        newNotesBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InsertNotesActivity.class)));

        notesViesModel.getAllNotes.observe(this, notes -> {
            setAdapter(notes);
            filterNotesAllList = notes;
        });

    }

    private void loadData(int i) {
        if (i==0){
            notesViesModel.getAllNotes.observe(this, notes -> {
                setAdapter(notes);
                filterNotesAllList = notes;
            });
        }
        else if (i==1){

            notesViesModel.highToLow.observe(this, notes -> {
                setAdapter(notes);
                filterNotesAllList = notes;
            });
        }
        else if (i==2){

            notesViesModel.lowToHigh.observe(this, notes -> {
                setAdapter(notes);
                filterNotesAllList = notes;
            });
        }
    }

    public void setAdapter(List<Notes> notes) {
        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        notesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_bar,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;

            }
        });
        return true;
    }

    private void NotesFilter(String newText) {
        Log.e("NewText", "NotesFilter: "+newText );

        ArrayList<Notes> FilterNames = new ArrayList<>();
        for (Notes notes: this.filterNotesAllList){
            if (notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)){
                FilterNames.add(notes);
            }
        }
        this.adapter.SearchNotes(FilterNames);
    }
}