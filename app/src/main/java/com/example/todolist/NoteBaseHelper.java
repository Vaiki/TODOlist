package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.database.NoteDbSchema;
import com.example.todolist.database.NoteDbSchema.NoteTable;

import androidx.annotation.Nullable;

public class NoteBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ NoteDbSchema.NoteTable.NAME + "("+"_id integer primary key autoincrement, " +
                NoteTable.Cols.UUID+", "+
                NoteTable.Cols.TITLE+", "+
                NoteTable.Cols.DATE+ ", "+
                NoteTable.Cols.SOLVED +")"
                                );



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
