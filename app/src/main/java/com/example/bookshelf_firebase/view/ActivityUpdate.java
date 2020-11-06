package com.example.bookshelf_firebase.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookshelf_firebase.R;
import com.example.bookshelf_firebase.model.Livro;
import com.google.firebase.FirebaseApp;
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
    private String saveName;

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

                    saveName = nome;
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

        initFireBase();

        this.clickButtonListener();

    }

    private void clickButtonListener(){
        this.btnSalvar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro livro = getLivroForm();
                ref = FirebaseDatabase.getInstance().getReference("pessoa");
                ref.child(saveName).child("nome").setValue(livro.getNome());
                ref.child(saveName).child("autor").setValue(livro.getAutor());
                ref.child(saveName).child("quantidade").setValue(livro.getQuantidade());
                ref.child(saveName).child("status").setValue(livro.getStatus());

            }
        });
    }

    private void initFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();
    }

    private Livro getLivroForm(){
        this.livro = new Livro();

        if(this.txtNome2.getText().toString().isEmpty() == false){
            this.livro.setNome(this.txtNome2.getText().toString());
        }else{
            return null;
        }

        if(this.txtAutor2.getText().toString().isEmpty() == false){
            this.livro.setAutor(this.txtAutor2.getText().toString());
        }else{
            return null;
        }

        if(this.txtQuantidade2.getText().toString().isEmpty() == false){
            int quantProd = Integer.parseInt(txtQuantidade2.getText().toString());
            this.livro.setQuantidade(quantProd);
        }else{
            return null;
        }

        this.livro.setStatus(spnTipo2.getSelectedItem().toString());

        return livro;

    }
}