package com.example.iamtired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iamtired.services.DatabaseService;

import java.util.ArrayList;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        TextView createYourNoteText = findViewById(R.id.textViewCreateYourNote);
        Button createButton = findViewById(R.id.buttonCreate);
        EditText ETName = findViewById(R.id.editTextName);
        EditText ETDescription = findViewById(R.id.editTextDescription);
        EditText ETImportance = findViewById(R.id.editTextImportance);
        EditText ETResponsible = findViewById(R.id.editTextResponsible);
        Button goBackButton = findViewById(R.id.buttonGoBack);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NoteActivity.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ETName.getText().toString();
                String description = ETDescription.getText().toString();
                String importance = ETImportance.getText().toString();
                String responsible = ETResponsible.getText().toString();

                //
                if(name.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Name is empty", Toast.LENGTH_SHORT).show();
                }
                else if(importance.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Importance is empty", Toast.LENGTH_SHORT).show();
                }
                else if(responsible.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Who is responsible is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Date currentDate = new Date();
                    String date = currentDate.getHours() + " : " + currentDate.getMinutes();
                    Note note = new Note(name, description, importance, responsible, date, false);
                    Intent intent = new Intent(getBaseContext(), NoteActivity.class);
                    ArrayList<String> array = new ArrayList<>();
                    array.add(name);
                    array.add(description);
                    array.add(importance);
                    array.add(responsible);

                    DatabaseService.createNewNote(note);

                    intent.putExtra("noteFields", array);
                    startActivity(intent);
                }
            }
        });
    }
}