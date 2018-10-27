package com.example.afridha.simplememoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.afridha.simplememoapp.Model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PlainNoteEditor extends AppCompatActivity {
    EditText etJudul, etIsi;
    String title, content, dateCreated, dateModified, mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_editor);

        etJudul = findViewById(R.id.etTitle);
        etIsi = findViewById(R.id.etContent);

        //Set currentDate
        mCurrentDate = getDate();
    }

    private void saveNote() {
        //Get data from EditText
        String title = etJudul.getText().toString();
        String content = etIsi.getText().toString();

        //Set dateCreated and dateModified value
        dateCreated = mCurrentDate;
        dateModified = dateCreated;

        if (title.equals("") || content.equals("")) {
            Toast.makeText(this, "Field masih kosong, tidak dapat menyimpan", Toast.LENGTH_SHORT).show();
        } else {
            //Initialize the database
            DatabaseHelper db = new DatabaseHelper(this);

            //Saving the data to object notes
            Note note = new Note(title, content, dateCreated, dateModified);

            //Call method for adding new notes
            db.addNote(note);
            db.close();

            Intent intent = new Intent(PlainNoteEditor.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save)
            saveNote();
        return super.onOptionsItemSelected(item);
    }

    public String getDate() {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String current = dateFormat.format(c.getTime());
            Date date = dateFormat.parse(current);
            SimpleDateFormat fmtOut = new SimpleDateFormat("d MMM yyyy");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }
        return "";
    }
}
