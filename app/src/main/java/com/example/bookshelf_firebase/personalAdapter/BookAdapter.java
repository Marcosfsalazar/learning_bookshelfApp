package com.example.bookshelf_firebase.personalAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf_firebase.R;
import com.example.bookshelf_firebase.model.Livro;
import com.example.bookshelf_firebase.view.ActivityCadastroLivro;
import com.example.bookshelf_firebase.view.ActivityUpdate;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class BookAdapter extends FirebaseRecyclerAdapter<Livro,BookAdapter.BookHolder> {
    Context context;

    public BookAdapter(@NonNull FirebaseRecyclerOptions<Livro> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull BookHolder bookHolder, int i, @NonNull Livro livro) {
        bookHolder.txtViewTitle.setText(livro.getNome());
        bookHolder.txtViewStatus.setText("pag."+String.valueOf(livro.getQuantidade()));
        bookHolder.txtViewAutor.setText(livro.getStatus());

        bookHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUpdate.class);
                intent.putExtra("livroNome",getRef(i).getKey());
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro,
                parent,false);
        return new BookHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getRef().removeValue();
    }

    class BookHolder extends RecyclerView.ViewHolder{
        TextView txtViewTitle;
        TextView txtViewAutor;
        TextView txtViewStatus;

        public BookHolder(@NonNull View itemView) {

            super(itemView);
            txtViewTitle = itemView.findViewById(R.id.txt_view_title);
            txtViewAutor = itemView.findViewById(R.id.txt_view_autor);
            txtViewStatus = itemView.findViewById(R.id.txt_view_status);
        }
    }
}
