package com.example.bookshelf_firebase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookshelf_firebase.R;
import com.example.bookshelf_firebase.model.Livro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityUpdate extends AppCompatActivity {

    private DatabaseReference mDatabase2;
    private EditText txtNome2;
    private EditText txtAutor2;
    private EditText txtQuantidade2;
    private Spinner spnTipo2;
    private Button btnSalvar2;

    private Livro livro;

    private FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ref = FirebaseDatabase.getInstance().getReference().child("pessoa");

        txtNome2 = (EditText)findViewById(R.id.txtNome2);
        txtAutor2 = (EditText)findViewById(R.id.txtAutor2);
        txtQuantidade2 = (EditText)findViewById(R.id.txtQuantidade2);
        spnTipo2 = (Spinner)findViewById(R.id.spnTipo2);

        btnSalvar2 = (Button)findViewById(R.id.btnSalvar2);

        String nomeLivro = getIntent().getStringExtra("livroNome");

        ref.child(nomeLivro).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String autor = snapshot.child("autor").getValue().toString();
                    String nome = snapshot.child("nome").getValue().toString();
                    String quantidade = snapshot.child("quantidade").getValue().toString();
                    String status = snapshot.child("status").getValue().toString();
                    String parado = "parado";

                    txtNome2.setText(nome);
                    txtAutor2.setText(autor);
                    txtQuantidade2.setText(quantidade);
                    if(status.compareToIgnoreCase(parado) == 0){
                        spnTipo2.setSelection(1);
                    }else if(status.compareToIgnoreCase(parado)==1){
                        spnTipo2.setSelection(0);
                    }else{
                        spnTipo2.setSelection(-1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}