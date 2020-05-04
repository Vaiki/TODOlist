package com.example.todolist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {
    private static NoteLab sNoteLab;
    private List<Note> mNotes;

    public static NoteLab get(Context context) {

        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mNotes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Note note = new Note();
            note.setmTitle("Note #" + i);
            note.setmSolved(i % 2 == 0);
            mNotes.add(note);

        }
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    public Note getNote(UUID id) {
        for (Note note : mNotes) {
            if (note.getmId().equals(id)) {
                return note;
            }


        }
        return null;
    }
}