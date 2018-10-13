package com.example.afridha.simplememoapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

    public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NotesViewHolder> {
        private List<Note> mNoteList;
        private Context context;


        public static class NotesViewHolder extends RecyclerView.ViewHolder {
            public TextView tvTitle, tvDate;
            public CardView cvNotes;
            public Button btnOptions;

            public NotesViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvNoteTitle);
                tvDate = itemView.findViewById(R.id.tvNoteDate);
                cvNotes = itemView.findViewById(R.id.cardNote);
                btnOptions = itemView.findViewById(R.id.btnOptions);

            }
        }

        public NoteListAdapter(Context context, List<Note> noteList) {
            this.context = context;
            mNoteList = noteList;
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
            holder.cvNotes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, currentNote.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, UpdateNoteEditor.class);
                    intent.putExtra("position", position);
                    intent.putExtra("id", currentNote.getId());
                    intent.putExtra("title", currentNote.getTitle());
                    intent.putExtra("content", currentNote.getContent());
                    intent.putExtra("created", currentNote.getDateCreated());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mNoteList.size();
        }

        public void notifyAll(List<Note> list) {
            if (mNoteList != null) {
                mNoteList.clear();
                mNoteList.addAll(list);
            } else {
                mNoteList = list;
            }
            notifyDataSetChanged();
        }
    }