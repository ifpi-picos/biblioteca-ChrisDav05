package dominio;

public class Usuario {
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
