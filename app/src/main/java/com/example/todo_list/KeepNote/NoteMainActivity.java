package com.example.todo_list.KeepNote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo_list.KeepNote.ProxyNote.NoteServiceProxy;
import com.example.todo_list.LoginSignup.LoginActivity;
import com.example.todo_list.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NoteMainActivity extends AppCompatActivity {
    private EditText title, desc;
    private String titlesend, descsend;
    private NoteServiceProxy noteServiceProxy;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        initializeViews();
        noteServiceProxy = new NoteServiceProxy();
    }

    public void AddNotes(View view) {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(getApplicationContext(), "Not logged in!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            return;
        }
        String userId = currentUser.getUid();
        String titlesend = title.getText().toString();
        String descsend = desc.getText().toString();

        Listdata note = new Listdata("", titlesend, descsend);
        noteServiceProxy.addNote(note);
    }

    private void initializeViews() {
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
    }
}