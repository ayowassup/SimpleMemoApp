package com.example.afridha.simplememoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvNotes;
    RecyclerView.Adapter adapter;
    List<Note> notesList;
    FloatingActionButton tambahButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotes = findViewById(R.id.RecyclerNotes);
        tambahButton = findViewById(R.id.fabTambahNotes);


        initViews();
        loadNotes();
            tambahButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addNote();
                }
            });
        }

        private void initViews() {
            rvNotes.setHasFixedSize(true);
            LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
            rvNotes.setLayoutManager(linearLayout);

        }

        private void loadNotes(){
            DatabaseHelper db = new DatabaseHelper(this);
            notesList = db.getAllNotes();
            if(notesList.size() != 0){
                notesList.clear();
                notesList.addAll(db.getAllNotes());
                adapter = new NoteListAdapter(this,notesList);
                rvNotes.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }

        public void addNote(){
            Intent i = new Intent(MainActivity.this,PlainNoteEditor.class);
            startActivity(i);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4) {
            DatabaseHelper db = new DatabaseHelper(this);
            notesList.clear();
            notesList.addAll(db.getAllNotes());
            adapter.notifyDataSetChanged();
        }
    }
}