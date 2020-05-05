package com.example.todolist;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListFragment extends Fragment {
    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;
    private int getPosition;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_recycler_view);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume(){ // переопределяем чтобы при возврате к списку из NoteFragment обновить содержимое списка в случае изменений
        super.onResume();
        updateUI();
    }
    private void updateUI(){
        NoteLab noteLab = NoteLab.get(getActivity());// создаем синглетон
        List<Note> notes = noteLab.getNotes();// передаем все содержимое синглетоона в адаптер
        if(mAdapter==null){
        mAdapter = new NoteAdapter(notes);
        mNoteRecyclerView.setAdapter(mAdapter);}// метод связывает подготовленый список со списком
        else{
            mAdapter.notifyItemChanged(getPosition);// сообщает что текущая позиция изменена

        }
    }
    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private CheckBox mCheckBox;
    private ImageView mStarImageView;
    private Note mNote;
    public NoteHolder(LayoutInflater inflater, ViewGroup parent){
        super(inflater.inflate(R.layout.list_item_note,parent,false));
        itemView.setOnClickListener(this);
        mTitleTextView = (TextView) itemView.findViewById(R.id.note_title);
        mDateTextView = (TextView) itemView.findViewById(R.id.note_date);
        mCheckBox=(CheckBox) itemView.findViewById(R.id.check_list);
        mStarImageView = (ImageView) itemView.findViewById(R.id.is_star);
     //   if (mCheckBox.isChecked())mTitle TextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }
    public void bind(Note note){
        mNote = note;
        mTitleTextView.setText(mNote.getmTitle());

        mDateTextView.setText(mNote.getmDate().toString());
        mStarImageView.setVisibility(note.ismSolved()?View.VISIBLE:View.GONE);

    }

        @Override
        public void onClick(View v) {
        getPosition = getAdapterPosition();
           Intent intent = NotePagerActivity.newIntent(getActivity(),mNote.getmId());// передаем id выбранной заметке
           startActivity(intent);// Запуск активити с подробной инфой

        }
    }

private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<Note> mNotes;
    public NoteAdapter(List<Note> notes){
        mNotes=notes;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        return new NoteHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = mNotes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

        }
}

