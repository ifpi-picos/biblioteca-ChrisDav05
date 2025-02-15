package com;

public class Livro {
    private int livro_id;
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private String ISBN;
    private Boolean emprestado;

    public Livro( String autor, String titulo, String editora, int ano, Boolean emprestado, String ISBN) {
        this.autor = autor;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.emprestado = emprestado;
        this.ISBN = ISBN;
    }
    public int getIDLivro(){
        return livro_id;
    }
    public void setIDLivro(int livro_id){
        this.livro_id = livro_id;
    }
    public String getAutor(){
        return autor;
    }
    public String getISBN(){
        return ISBN;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getEditora(){
        return editora;
    }
    public void setEditora(String editora){
        this.editora = editora;
    }
    public int getAno(){
        return ano;
    }
    public void setAno(int ano){
        this.ano = ano;
    }
    public Boolean getEmprestado(){
        return emprestado;
    }
    public void setEmprestado(Boolean emprestado){
        this.emprestado = emprestado;
    }
}

