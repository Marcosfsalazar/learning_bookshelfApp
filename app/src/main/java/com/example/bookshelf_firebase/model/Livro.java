package com.example.bookshelf_firebase.model;

public class Livro {

    private String nome;
    private String autor;
    private int quantidade;
    private String status;

    public Livro(){

    }

    public Livro(String nome, String autor, int quantidade, String status) {
        this.nome = nome;
        this.autor = autor;
        this.quantidade = quantidade;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
