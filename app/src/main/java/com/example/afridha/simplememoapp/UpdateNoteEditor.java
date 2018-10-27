package com.example.afridha.simplememoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.afridha.simplememoapp.Model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateNoteEditor extends AppCompatActivity {
    EditText etJudul, etIsi;
    String title, content, dateCreated, dateModified, mCurrentDate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_editor);

        etJudul = findViewById(R.id.etTitle);
        etIsi = findViewById(R.id.etContent);

        //Set currentDate
        mCurrentDate = getDate();

        //Get data from selected notes
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        dateCreated = getIntent().getStringExtra("created");
        dateModified = mCurrentDate;

        //Set data to EditText
        etJudul.setText(title);
        etIsi.setText(content);
    }

    private void updateNote() {
        //Get data from EditText
        String currentTitle = etJudul.getText().toString();
        String currentContent = etIsi.getText().toString();

        //Initialize the database
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        //Saving the updated data to object notes
        Note notes = new Note(currentTitle, currentContent, dateCreated, dateModified);

        //Call method for updating note
        db.editNote(notes, id);
        db.close();

        Intent intent = new Intent(UpdateNoteEditor.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            updateNote();
        }
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
