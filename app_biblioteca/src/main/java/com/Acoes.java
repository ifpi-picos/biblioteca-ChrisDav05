package com;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.dao.EmprestimoDAO;
import com.dao.LivroDAO;
import com.dao.UsuarioDAO;


public class Acoes {
    Scanner scanner = new Scanner(System.in);
    LivroDAO livroDAO = new LivroDAO();
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void Cadastrar(){
          System.out.println("\nDigite o autor do livro:");
          String autor = scanner.nextLine();
          System.out.println("Digite o título do livro:");
          String titulo = scanner.nextLine(); 
          System.out.println("Digite a editora do livro:");
          String editora = scanner.nextLine();
          int anoAtual = LocalDate.now().getYear();
          System.out.println("Ano atual: " + anoAtual);
          System.out.println("Digite o ano do livro:");
          int ano = scanner.nextInt();
          while (ano > anoAtual) {
            System.out.println("O ano do livro não pode ser maior que o ano atual. Tente novamente:");
            ano = scanner.nextInt(); 
        }
          scanner.nextLine();
          System.out.println("Digite o ISBN do livro no formato XXXX-XXXX:");
          String ISBN = scanner.nextLine();
          Livro livro = new Livro(autor, titulo, editora, ano, false, ISBN);
          livro = livroDAO.adicionarLivro(livro);
          
               
    }
    
    public void ListarLivros(){
        List <Livro> livros = livroDAO.listarLivros();
        System.out.println("\nLivros cadastrados na biblioteca:");
            for (Livro livro : livros) {
                System.out.println("\nAutor: " + livro.getAutor());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Emprestado: " + livro.getEmprestado());
                System.out.println("ISBN: " + livro.getISBN());
                System.out.println("---------------------------");
            }
    }

    public void CadUsuario(){
        System.out.println("\nDigite o seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o seu cpf no formato xxx.xxx.xxx-xx:");
        String cpf = scanner.nextLine(); 
        System.out.println("Digite o seu email no formato username@gmail.com:");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(nome, cpf, email);
        usuario = usuarioDAO.adicionarUsuario(usuario);
        
        System.out.println("\nUsuario cadastrado na biblioteca!");
        System.out.println("\nNome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Email: " + usuario.getEmail());
    }

    public void Emprestar(){

        System.out.println("Lista dos livros da biblioteca");
        System.out.println("\nDigite o título do livro que deseja tomar como emprestado:");
        String livroTitulo = scanner.nextLine();
        int livro_id = livroDAO.buscaIdPorTitulo(livroTitulo);

        System.out.println("Digite o nome do usuario que quer tomar emprestado um livro:");
        String usuarioNome = scanner.nextLine();
        int usuario_id = usuarioDAO.buscaIdPorNome(usuarioNome);

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarioDAO.listarUsuarios()) {
        if (usuario_id == usuario.getIDUsuario()) {
            usuarioEncontrado = usuario;
            break;
        }
    }
        Livro livroEncontrado = null;
        for (Livro livro : livroDAO.listarLivros()) {
        if (livro_id == livro.getIDLivro()) {
            livroEncontrado = livro;
            break;
        }
    }
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);
        String emprestado_status = "Emprestado";
        if(livroEncontrado!= null){
            int idEncontrado = livroDAO.buscaIdPorTitulo(livroTitulo);
            emprestimoDAO.atualizarStatus(true, idEncontrado);
            InterNotificaçao notificaçao = new NotificarEmprestimo();
            notificaçao.Notificar(usuarioEncontrado);
        }
        try {
            Emprestimo emprestimo = new Emprestimo(livroEncontrado, usuarioEncontrado, dataEmprestimo, dataDevolucao, emprestado_status);
            emprestimoDAO.adicionarEmprestimo(emprestimo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Devolver (){
    
    System.out.println("Digite seu ID de usuário:");
    int idUsuario = scanner.nextInt();
    Emprestimo emprestimoEncontrado = null;
    
    for (Emprestimo emprestimo : emprestimoDAO.listarEmprestimos()) {
        Usuario usuario = emprestimo.getUsuario();
        
        if (usuario != null && idUsuario == usuario.getIDUsuario()) {
            emprestimoEncontrado = emprestimo;
            break;
        }
    }    
    if (emprestimoEncontrado == null) {
        System.out.println("Nenhum empréstimo encontrado para este usuário.");
        return;
    }
    System.out.println("Digite o ID do livro que deseja devolver: ");
    int idDevolve = scanner.nextInt();

    boolean encontrouLivro = false;

    for (Emprestimo emprestimo : emprestimoDAO.listarEmprestimos()) {
        if (idUsuario == emprestimo.getUsuario().getIDUsuario() && idDevolve == emprestimo.getLivro().getIDLivro()) {
            encontrouLivro = true;
            break;
        }
    }

    if (!encontrouLivro) {
        System.out.println("Este livro não pertence a um empréstimo seu.");
        return;
    }

    try {
        emprestimoDAO.devolverLivro(idDevolve, "Devolvido");
        System.out.println("Livro devolvido com sucesso!");
    } catch (SQLException e) {
        System.out.println("Erro ao devolver o livro.");
        e.printStackTrace();
    }
    }

    public void ListarLivrosDisponiveis(){
        System.out.println("\nLivros disponíveis:");
            for (Livro livro : livroDAO.listarLivrosDisponiveis()) {
                System.out.println("\nAutor: " + livro.getAutor());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Emprestado: " + livro.getEmprestado());
                System.out.println("ISBN: " + livro.getISBN());
            }
    }

    public void ListarLivrosEmprestados(){
        System.out.println("\nLivros emprestados:");
            for (Livro livrosEmprestados : livroDAO.listarLivrosEmprestados()) {
                System.out.println("\nAutor: " + livrosEmprestados.getAutor());
                System.out.println("Título: " + livrosEmprestados.getTitulo());
                System.out.println("Editora: " + livrosEmprestados.getEditora());
                System.out.println("Ano: " + livrosEmprestados.getAno());
                System.out.println("Emprestado: " + livrosEmprestados.getEmprestado());
                System.out.println("ISBN: " + livrosEmprestados.getISBN());
            }
    }
}
