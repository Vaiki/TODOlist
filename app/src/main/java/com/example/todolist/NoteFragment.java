
package com.example.todolist;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NoteFragment extends Fragment {
    private static final String ARG_NOTE_ID = "note_id";
    private static final String DIALOG_DATE = "DialogDate";
    private Note mNote;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static NoteFragment newInstance(UUID noteId){// хранение аргументов для вызова требуемого(UUID) фрагмента с активити
        Bundle args = new Bundle(); // содержит пары key-value
        args.putSerializable(ARG_NOTE_ID,noteId); // добавление аргументов в пакет
        NoteFragment fragment = new NoteFragment();// создает экземпляр фрагмента
        fragment.setArguments(args); // присоединени пакета аргументов к фрагменту
        return fragment;



    }
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mNote = new Note();
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID); //когда фрагменту требуется получить доступ к его аргументам, он вызывает метод getArguments() класса
        // Fragment, а затем один из get-методов Bundle для конкретного типа.
        mNote = NoteLab.get(getActivity()).getNote(noteId);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        mTitleField = (EditText) v.findViewById(R.id.note_title);
        mTitleField.setText(mNote.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.note_date);
        mDateButton.setText(mNote.getmDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager,DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.note_solved);
        mSolvedCheckBox.setChecked(mNote.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mNote.setmSolved(isChecked);
            }
        });
        return v;

    }
}