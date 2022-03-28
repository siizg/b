package com.example.iamtired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button button = findViewById(R.id.buttonLogIn2);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword2);
        EditText editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        Button buttonBack = findViewById(R.id.buttonBack);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateUserActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //val
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();

                //
                if(password.isEmpty()) {
                    Toast.makeText(getBaseContext(), "password is empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()) {
                    Toast.makeText(getBaseContext(), "email is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
                            addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getBaseContext(), "can't sign in", Toast.LENGTH_SHORT);
                                }
                            }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(getBaseContext(), NoteActivity.class));
                        }
                    });
                }
            }
        });

    }
}