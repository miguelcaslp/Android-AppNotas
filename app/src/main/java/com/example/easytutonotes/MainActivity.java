package com.example.easytutonotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.example.easytutonotes.Data.DbNotes;
import com.example.easytutonotes.Model.Note;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   public  List<Note> notesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MaterialButton addBoton = findViewById(R.id.addBoton);
        MaterialButton abautBoton = findViewById(R.id.abautBoton);

        addBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddNoteActivity.class));
               /* Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                changeNotes.launch(intent);*/
            }
        });

        abautBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AbautMe.class));
            }
        });

        loadNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    public void loadNotes(){
        DbNotes db = new DbNotes(MainActivity.this);
        this.notesList= db.getNotes();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),notesList);
        recyclerView.setAdapter(myAdapter);

    }



    /*ActivityResultLauncher<Intent> changeNotes= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result!=null && result.getResultCode() == RESULT_OK){
                if(result.getData()!=null){
                    boolean a =result.getData().getBooleanExtra(AddNoteActivity.result,true);
                    if(a){
                        loadNotes();
                    }
                }
            }
        }
    });*/




}