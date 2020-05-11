package com.example.todolist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.todolist.Note;
import com.example.todolist.database.NoteDbSchema.NoteTable;

import java.util.Date;
import java.util.UUID;

public class NoteCursorWrapper extends CursorWrapper {
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Note getNote(){
        String uuidString = getString(getColumnIndex(NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NoteTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(NoteTable.Cols.SOLVED));
        Note note = new Note(UUID.fromString(uuidString));
        note.setmTitle(title);
        note.setmDate(new Date(date));
        note.setmSolved(isSolved!=0);

        return note;
    }
}
