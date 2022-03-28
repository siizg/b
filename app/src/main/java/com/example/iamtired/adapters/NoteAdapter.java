package com.example.iamtired.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iamtired.Note;
import com.example.iamtired.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.Date;

public class NoteAdapter extends FirebaseRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    public NoteAdapter(@NonNull FirebaseRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView importanceTextView;
        TextView responsibleTextView;
        TextView dateTextView;
        TextView nameTextView;
        CheckBox checkBox;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            importanceTextView = itemView.findViewById(R.id.textViewImportance);
            responsibleTextView = itemView.findViewById(R.id.textViewResponcible);
            dateTextView = itemView.findViewById(R.id.textViewDate);
            nameTextView = itemView.findViewById(R.id.textViewName);
            //checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void bind(Note note) {
            descriptionTextView.setText(note.description);
            importanceTextView.setText(note.importance);
            responsibleTextView.setText(note.responsible);
            nameTextView.setText(note.name);
            //Date currentDate = new Date();
           // String date = currentDate.getDay() + "  " + currentDate.getHours() + " : " + currentDate.getMinutes();
            dateTextView.setText(note.date);
//            checkBox.setChecked(note.isChecked);
//
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    note.isChecked = true;
//                    nameTextView.setPaintFlags(nameTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//                }
//            });

        }
    }
}
