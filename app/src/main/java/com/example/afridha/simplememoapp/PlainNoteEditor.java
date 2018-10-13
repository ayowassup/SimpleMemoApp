package com.example.afridha.simplememoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlainNoteEditor extends AppCompatActivity {
        EditText etJudul, etIsi;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String mCurrentDate;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.plain_note_editor);
            etJudul = findViewById(R.id.etTitle);
            etIsi = findViewById(R.id.etContent);

            //Set currentDate
            mCurrentDate = dateFormat.format(c.getTime());
        }

        private void saveNote(){
            String dateCreated, dateModified;
            String title = etJudul.getText().toString();
            String content = etIsi.getText().toString();
            dateCreated = mCurrentDate;
            dateModified = dateCreated;

            if(title.equals("") || content.equals("")){
                showToast("Field masih kosong, tidak dapat menyimpan");
            } else {
                DatabaseHelper db = new DatabaseHelper(this);
                Note note = new Note(title, content, dateCreated, dateModified);
                db.addNote(note);
                db.close();
                Intent i = new Intent(PlainNoteEditor.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }

        }

        private void showToast(String msg){
            Toast.makeText(this, msg ,Toast.LENGTH_SHORT);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_add_notes,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if(id == R.id.action_save)
                saveNote();
            return super.onOptionsItemSelected(item);
        }
}
