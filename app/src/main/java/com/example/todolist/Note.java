package com.example.todolist;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Note {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Note() {
        mId = UUID.randomUUID();
        mDate = new Date();
       // DateFormat.getDateInstance(DateFormat.LONG).format(mDate);

    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
      // return android.text.format.DateFormat.format("HH:mm EEEE, dd.MM.yyyy",mDate);
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }
}
