package com.example.iamtired;

import static com.example.iamtired.services.DatabaseService.getDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iamtired.adapters.NoteAdapter;
import com.example.iamtired.services.DatabaseService;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        TextView userNAmeText = findViewById(R.id.textViewUserName);
        Button buttonSignOut = findViewById(R.id.buttonSighOut);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        userNAmeText.setText(mAuth.getCurrentUser().getEmail().toString());


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);


        adapter = new NoteAdapter(DatabaseService.getNoteOptions());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });


        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getBaseContext(), CreateUserActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CreateNoteActivity.class);
                startActivity(intent);
            }
        });
        Note note = new Note();
        Bundle bundle = getIntent().getExtras();

        try {
            ArrayList<String> array = bundle.getStringArrayList("NoteFields");
            note.name = array.get(0);
            note.description = array.get(1);
            note.importance = array.get(2);
            note.responsible = array.get(3);
            note.date = "";
        }
        catch(Exception e) {
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}