package com.example.afridha.simplememoapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afridha.simplememoapp.DatabaseHelper;
import com.example.afridha.simplememoapp.Model.Note;
import com.example.afridha.simplememoapp.R;
import com.example.afridha.simplememoapp.UpdateNoteEditor;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NotesViewHolder> {
    private List<Note> mNoteList;
    private Context context;
    private DatabaseHelper mDatabase;


    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDate;
        public CardView cvNotes;
        public ImageView delete, edit;

        public NotesViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvDate = itemView.findViewById(R.id.tvNoteDate);
            delete = itemView.findViewById(R.id.deleteButton);
            cvNotes = itemView.findViewById(R.id.cardNote);
            edit = itemView.findViewById(R.id.editButton);
        }
    }

    public NoteListAdapter(Context context, List<Note> noteList) {
        this.context = context;
        mNoteList = noteList;
        mDatabase = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_adapter, parent, false);
        NotesViewHolder nvh = new NotesViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        final Note currentNote = mNoteList.get(position);
        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDate.setText(currentNote.getDateModified());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNoteEditor.class);

                //Pass the data to UpdateNoteEditor activity
                intent.putExtra("id", currentNote.getId());
                intent.putExtra("title", currentNote.getTitle());
                intent.putExtra("content", currentNote.getContent());
                intent.putExtra("created", currentNote.getDateCreated());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete selected note from database
                mDatabase.deleteNote(currentNote.getId());
                //Delete selected note from list
                mNoteList.remove(position);
                refreshView(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void refreshView(int position) {
        notifyItemChanged(position);
    }
}