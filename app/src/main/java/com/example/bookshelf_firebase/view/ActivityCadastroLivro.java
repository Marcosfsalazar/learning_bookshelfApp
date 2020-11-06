package com.example.bookshelf_firebase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bookshelf_firebase.R;
import com.example.bookshelf_firebase.model.Livro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCadastroLivro extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText txtNome;
    private EditText txtAutor;
    private EditText txtQuantidade;
    private Spinner spnTipo;
    private Button btnSalvar;

    private Livro livro;

    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtAutor = (EditText)findViewById(R.id.txtAutor);
        txtQuantidade = (EditText)findViewById(R.id.txtQuantidade);
        spnTipo = (Spinner)findViewById(R.id.spnTipo);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);

        initFireBase();

        this.clickButtonListener();
    }

    private void clickButtonListener(){
        this.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro livro = getLivroForm();
                if(getLivroForm() != null){
                    databaseReference.child("pessoa").child(livro.getNome()).setValue(livro); //sim chamei o child de pessoa
                }                                                                             //pq tava usando m.de. bras cubas de livro
            }                                                                                 // e isso me deixou confuso
        });
    }

    private void initFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private Livro getLivroForm(){
        this.livro = new Livro();

        if(this.txtNome.getText().toString().isEmpty() == false){
            this.livro.setNome(this.txtNome.getText().toString());
        }else{
            return null;
        }

        if(this.txtAutor.getText().toString().isEmpty() == false){
            this.livro.setAutor(this.txtAutor.getText().toString());
        }else{
            return null;
        }

        if(this.txtQuantidade.getText().toString().isEmpty() == false){
            int quantProd = Integer.parseInt(txtQuantidade.getText().toString());
            this.livro.setQuantidade(quantProd);
        }else{
            return null;
        }

        this.livro.setStatus(spnTipo.getSelectedItem().toString());

        return livro;

    }

}