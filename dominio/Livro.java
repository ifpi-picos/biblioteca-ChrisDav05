package dominio;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String autor;
    private String titulo;
    private String editora;
    private int ano;
    private Boolean emprestado;
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
      }
    public String getAutor(){
        return autor;
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
        this.emprestado = false;
    }
}

