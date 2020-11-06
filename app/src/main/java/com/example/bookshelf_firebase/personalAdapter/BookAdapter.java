package com.example.bookshelf_firebase.personalAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class BookAdapter extends FirebaseRecyclerAdapter {

    class BookHolder extends RecyclerView.ViewHolder{

        public BookHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
