package com.example.easytutonotes.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.easytutonotes.Model.Note;
import com.example.easytutonotes.ModifyNoteActivity;

import java.util.ArrayList;
import java.util.List;

public class DbNotes extends DataBase {
    Context context;
    public DbNotes(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public long addNOte(Note note){
        long id=-1;
        try{
            DataBase db = new DataBase(context);
            SQLiteDatabase sql = db.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("title", note.getTitle());
            values.put("description", note.getDescription());
            values.put("createdTime", note.getCreatedTime());
            values.put("status", note.getStatus());

            id=sql.insert(tableName,null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }
    public Note getNote(int id){
        DataBase db = new DataBase(context);
        SQLiteDatabase sql = db.getWritableDatabase();
        Cursor cursor= sql.rawQuery("SELECT * FROM " + tableName + " WHERE id=" + id , null);
        Note note = new Note();
        if(cursor.moveToFirst()){
            do{
                note =new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            }while(cursor.moveToNext());
        }
        return note;
    }
    public List<Note> getNotes(){
        DataBase db = new DataBase(context);
        SQLiteDatabase sql = db.getWritableDatabase();
        Cursor cursor= sql.rawQuery("SELECT * FROM " + tableName, null);
        List<Note> notes= new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                notes.add(new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while(cursor.moveToNext());
        }
        return notes;
    }

    public void deleteNote(int id){
        DataBase db = new DataBase(context);
        SQLiteDatabase sql = db.getWritableDatabase();
        sql.execSQL("DELETE FROM "+ tableName + " WHERE id=" + id );
        sql.close();

    }

   public int   updateNote(Note note){
       int result=0;
       try{
           DataBase db = new DataBase(context);
           SQLiteDatabase sql = db.getWritableDatabase();

           ContentValues values = new ContentValues();
           values.put("title", note.getTitle());
           values.put("description", note.getDescription());
           values.put("createdTime", note.getCreatedTime());
           values.put("status", note.getStatus());

          String id = ""+ note.getId();

           result=sql.update(tableName,values, "id = ?",new String[] { id });


       }catch (Exception ex){
           ex.toString();
       }
       return  result;
    }
}
