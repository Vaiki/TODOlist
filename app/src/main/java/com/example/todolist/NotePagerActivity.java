package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class NotePagerActivity extends AppCompatActivity {
    private static  final String EXTRA_NOTE_ID = "com.example.todolist.note_id";
    private ViewPager mViewPager;
    private List<Note> mNotes;
    public static Intent newIntent(Context packageContext, UUID noteId){
        Intent intent = new Intent(packageContext, NotePagerActivity.class);
        intent.putExtra(EXTRA_NOTE_ID,noteId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pager);
        mViewPager = (ViewPager) findViewById(R.id.note_view_pager);
        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        mNotes = NoteLab.get(this).getNotes();
        // необходим чтобы FragmentStatePagerAdapter смог управлять фрагментами и добавлять их в активность
        FragmentManager fragmentManager  = getSupportFragmentManager();
        // FragmentStatePagerAdapter - добавляет возвращаемыые фрагменты в активность  и помогает ViewPager
        // идентифицировать представления фрагментов для их правильного размещения
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            // получаем Note для заданной позиции и использует его id для создания и возвращения правильного настроенного экземпляра Note
            public Fragment getItem(int position) {
                Note note = mNotes.get(position);
                return NoteFragment.newInstance(note.getmId());
            }

            @Override
            public int getCount() {
                return mNotes.size();
            }
        });
        // viewPager запускает инфу о выбраной заметке
     for (int i=0;i<mNotes.size();i++){
            if (mNotes.get(i).getmId().equals(noteId)){
                mViewPager.setCurrentItem(i);
                break;
            }}

    }

}
