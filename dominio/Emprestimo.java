package dominio;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;    

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo =  LocalDate.now();
        this.dataDevolucao = this.dataEmprestimo.plusDays(7);
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo(){
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao(){
        return dataDevolucao;
    }
}


