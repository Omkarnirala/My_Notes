package com.example.mynotes.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Activity.UpdatesNotesActivity;
import com.example.mynotes.MainActivity;
import com.example.mynotes.R;
import com.example.mynotes.model.Notes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesItem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesItem = new ArrayList<>(notes);
    }

    public void SearchNotes(List<Notes> filteredNames){

        this.notes = filteredNames;
        notifyDataSetChanged();

    }

    @Override
    public @NotNull notesViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {


        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.@NotNull notesViewHolder holder, int position) {

        Notes note = notes.get(position);
        try {
            switch (note.notesPriority) {
                case "1":
                    holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                    break;
                case "2":
                    holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                    break;
                case "3":
                    holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                    break;
            }

        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }


        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdatesNotesActivity.class);

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subTitle",note.notesSubtitle);
            intent.putExtra("notes",note.notes);
            intent.putExtra("priority",note.notesPriority);


            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }



    static class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubTitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }

}
