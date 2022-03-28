package com.example.iamtired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getBaseContext(), NoteActivity.class);
            startActivity(intent);
        }

        Button bCreateAccount = findViewById(R.id.buttonCreateAccount);
        Button bLogIn = findViewById(R.id.buttonLogIn);
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);

        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SignInActivity.class));
            }
        });


        bCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();

                if(password.isEmpty()) {
                    Toast.makeText(getBaseContext(), "password is empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()) {
                    Toast.makeText(getBaseContext(), "email is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            User user = new User(authResult.getUser().getEmail().toString(),
                                    authResult.getUser().getUid().toString());

//                            FirebaseDatabase database = FirebaseDatabase.getInstance();
//                            DatabaseReference myRef = database.getReference("users");
//
//                            myRef.push().setValue(authResult.getUser().getUid());

                            startActivity(new Intent(getBaseContext(), NoteActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getBaseContext(), "Can't create user", Toast.LENGTH_SHORT).show();;
                        }
                    });
                }
            }
        });


    }
}