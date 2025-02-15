package com;

import java.time.LocalDate;

public class Emprestimo {
    private int livro_id;
    private int usuario_id;
    private LocalDate dataEmprestimo;
    private Boolean emprestadoStatus;    

    public Emprestimo(int livro_id, int usuario_id, LocalDate dataEmprestimo, Boolean emprestadoStatus) {
        this.livro_id = livro_id;
        this.usuario_id = usuario_id;
        this.dataEmprestimo =  dataEmprestimo;
        this.emprestadoStatus = emprestadoStatus;

    }
    public Boolean getEmprestadoStatus(){
        return emprestadoStatus;
    }
    public void setEmprestadoStatus(Boolean emprestadoStatus){
        this.emprestadoStatus = emprestadoStatus;
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


