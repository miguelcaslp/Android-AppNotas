package com.example.easytutonotes;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easytutonotes.Data.DbNotes;
import com.example.easytutonotes.Model.Note;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class ModifyNoteActivity extends AppCompatActivity {
    private String selectedDate;
    private String status;
    private Note note;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id= extras.getInt("id");
            DbNotes db = new DbNotes(ModifyNoteActivity.this);
            note =db.getNote(id);
        }

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        CalendarView calendar = findViewById(R.id.calendarView);
        Spinner statusInput = findViewById(R.id.statusView);
        MaterialButton saveBtn = findViewById(R.id.savebtn);
        MaterialButton removeBtn = findViewById(R.id.removebtn);


        titleInput.setText(note.getTitle());
        descriptionInput.setText(note.getDescription());
        selectedDate=note.getCreatedTime();

        String[] options = new String[] {
                "Por Hacer",
                "En Proceso",
                "Hecho"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusInput.setAdapter(adapter);
        if(note.getStatus().equals("Por Hacer")){
            statusInput.setSelection(0);
        } else if (note.getStatus().equals("En Proceso")) {
            statusInput.setSelection(1);
        }else{
            statusInput.setSelection(2);
        }

        statusInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                status = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                status = parentView.getItemAtPosition(0).toString();
            }

        });

        //setear fecha

        String date = note.getCreatedTime();
        String parts[] = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Calendar cal= Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        long milliTime = cal.getTimeInMillis();
        calendar.setDate(milliTime);



        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "/" + (month+1)  + "/" + year;
                selectedDate=Date;
            }
        });






        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();

                Note note = new Note(title,description,selectedDate,status);

                DbNotes db = new DbNotes(ModifyNoteActivity.this);
                db.updateNote(note);

                finish();


            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbNotes db = new DbNotes(ModifyNoteActivity.this);
                db.deleteNote(note.getId());
                Toast.makeText(ModifyNoteActivity.this,"Nota borrada",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

}