package com;

import java.time.LocalDate;

public class Emprestimo {
    private int emprestimo_id;
    private int livro_id;
    private int usuario_id;
    private LocalDate dataEmprestimo;
    private String status;    

    public Emprestimo(int livro_id, int usuario_id, LocalDate dataEmprestimo, String status) {
        this.livro_id = livro_id;
        this.usuario_id = usuario_id;
        this.dataEmprestimo =  dataEmprestimo;
        this.status = status;

    }
    public int getIDEmprestimo(){
        return emprestimo_id;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public int getIDLivro() {
        return livro_id;
    }

    public int getIDUsuario() {
        return usuario_id;
    }

    public LocalDate getDataEmprestimo(){
        return dataEmprestimo;
    }
}


