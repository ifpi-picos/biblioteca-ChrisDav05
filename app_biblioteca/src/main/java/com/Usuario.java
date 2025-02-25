package com;

public class Usuario {
    private Integer usuario_id;
    private String nome;
    private String cpf;
    private String email;
    private int contadorEmprestimos;


    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.contadorEmprestimos = 0;
    }
    public Usuario(Integer usuario_id, String nome, String cpf, String email) {
        this.usuario_id = usuario_id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Integer getIDUsuario(){
        return usuario_id;
    }
    public void setIDUsuario(Integer usuario_id){
        this.usuario_id = usuario_id;
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public String getEmail(){
        return email;
    }
    public int getContadorEmprestimos() {
        return contadorEmprestimos; 
    }

    public void incrementarEmprestimos() {
        contadorEmprestimos++;  
    }
}
