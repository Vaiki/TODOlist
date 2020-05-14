package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolist.database.NoteCursorWrapper;
import com.example.todolist.database.NoteDbSchema;
import com.example.todolist.database.NoteDbSchema.NoteTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {
    private static NoteLab sNoteLab;
   // private List<Note> mNotes;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static NoteLab get(Context context) {

        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();

      //  mNotes = new ArrayList<>();

    }
public void addNote(Note n){
        //mNotes.add(n);
    ContentValues values = getContentValues(n);
    mDatabase.insert(NoteTable.NAME,null,values);
}
    public List<Note> getNotes() {
       List<Note> notes = new ArrayList<>();

       NoteCursorWrapper cursor = queryNotes(null,null);
       try{
           cursor.moveToFirst();
           while (!cursor.isAfterLast()){
               notes.add(cursor.getNote());
               cursor.moveToNext();
           }}
           finally{cursor.close();}


        return notes;
    }

    public void deleteNote(Note note){
        String uuidString = note.getmId().toString();
        mDatabase.delete(NoteTable.NAME,
                NoteTable.Cols.UUID + " = ?",
                new String[] {uuidString});
        // ContentValues values = getContentValues(note);
        //mDatabase.delete(NoteTable.NAME,note.getmId().toString(),null);

    }

    public Note getNote(UUID id) {
        NoteCursorWrapper cursor = queryNotes(
                NoteTable.Cols.UUID+ " = ?",
                new String[]{id.toString()}
        );
        try{
            if (cursor.getCount()==0){return null;}
         cursor.moveToFirst();
        return cursor.getNote();}
        finally {
            cursor.close();
        }
            }
    public void updateNote(Note note){
        String uuidString = note.getmId().toString();
        ContentValues values = getContentValues(note);

        mDatabase.update(NoteTable.NAME, values, NoteTable.Cols.UUID+ " = ?", new String[]{uuidString});

    }
    private NoteCursorWrapper queryNotes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                NoteTable.NAME,
                null,// c null выбираются все столбцы
                whereClause,
                whereArgs,
                null,null,null

        ); return new NoteCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Note note){
        ContentValues values = new ContentValues();
        values.put(NoteTable.Cols.UUID, note.getmId().toString());
        values.put(NoteTable.Cols.TITLE, note.getmTitle());
        values.put(NoteTable.Cols.DATE, note.getmDate().getTime());
        values.put(NoteTable.Cols.SOLVED, note.ismSolved()?1:0);
       return values;


    }


}