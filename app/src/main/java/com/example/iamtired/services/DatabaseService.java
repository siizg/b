package com.example.iamtired.services;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iamtired.Note;
import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DatabaseService {
    public static FirebaseDatabase getDatabase() {
        //TODO
        return FirebaseDatabase.getInstance();
    }

    //public static Task<Void> addTestModel(String ref, TestModel model) {
      //  return getDatabase()
        //        .getReference(ref)
          //      .push()
            //    .setValue(model);
    //}

    public static void getTestModel(String ref, ValueEventListener listener) {
        getDatabase()
                .getReference(ref)
                .addListenerForSingleValueEvent(listener);
    }

    public static void getAllTestModels(String ref, ChildEventListener listener) {
        getDatabase()
                .getReference(ref)
                .orderByChild("booleanValue")
                .addChildEventListener(listener);
    }

    public static Task<Void> remove(String ref) {
        return getDatabase()
                .getReference(ref)
                .removeValue();
    }

    public static FirebaseRecyclerOptions<Note> getNoteOptions() {
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String path = "users/ " + userUID + "/ notes";

        Query query = getDatabase().getReference(path);
        ClassSnapshotParser<Note> parser = new ClassSnapshotParser<>(Note.class);

        return new FirebaseRecyclerOptions.Builder<Note>()
                .setQuery(query, parser)
                .build();
    }
    public static void createNewNote(Note newNote) {
        Note note = new Note(newNote.name, newNote.description, newNote.importance, newNote.responsible, newNote.date);
        String userUID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        String path = "users/ " + userUID + "/ notes/";

        getDatabase()
                .getReference(path)
                .push()
                .setValue(note);

        //databaseReference.setValue(notes);
    }

}