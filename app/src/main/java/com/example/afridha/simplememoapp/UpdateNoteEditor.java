package com.example.afridha.simplememoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateNoteEditor extends AppCompatActivity {
    EditText etJudul, etIsi;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String title, content, dateCreated, dateModified, mCurrentDate;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_note_editor);

        etJudul = findViewById(R.id.etEditTitle);
        etIsi = findViewById(R.id.etEditContent);

        //Set currentDate
        mCurrentDate = dateFormat.format(c.getTime());

        //Get data from selected notes
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        dateCreated = getIntent().getStringExtra("created");
        dateModified = mCurrentDate;
        etJudul.setText(title);
        etIsi.setText(content);
    }
    private void updateNote() {
        String currentTitle = etJudul.getText().toString();
        String currentContent = etIsi.getText().toString();
            //new object to add to database
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            Note notes = new Note(currentTitle, currentContent,dateCreated, dateModified);
            //Call method for updating note
            db.editNote(notes, id);
            db.close();
            Toast.makeText(getApplicationContext(), "Changed", Toast.LENGTH_SHORT).show();
            finish();
    }

    private void deleteNote() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.deleteNote(id);
        db.close();
//        .remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, moviesList.size());
//        Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();

    }

    private void showToast(String msg){
        Toast.makeText(this, msg ,Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update_notes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_save) {
            updateNote();
        } else if (id == R.id.action_delete) {
            deleteNote();
        }
        return super.onOptionsItemSelected(item);
    }

}
