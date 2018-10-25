package com.example.afridha.simplememoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.afridha.simplememoapp.Adapter.NoteListAdapter;
import com.example.afridha.simplememoapp.Model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvNotes;
    NoteListAdapter mAdapter;
    List<Note> notesList = new ArrayList<>();
    FloatingActionButton tambahButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tambahButton = findViewById(R.id.fabTambahNotes);

        buildRecyclerView();
        loadNotes();

        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    private void buildRecyclerView() {
        rvNotes = findViewById(R.id.RecyclerNotes);
        rvNotes.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        rvNotes.setLayoutManager(linearLayout);

    }

    private void loadNotes() {
        DatabaseHelper db = new DatabaseHelper(this);
        notesList = db.getAllNotes();
        if (notesList.size() != 0) {
            mAdapter = new NoteListAdapter(this, notesList);
            rvNotes.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void addNote() {
        Intent i = new Intent(MainActivity.this, PlainNoteEditor.class);
        startActivity(i);
    }
}