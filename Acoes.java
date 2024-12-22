import java.util.ArrayList;
import java.util.Scanner;

import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

public class Acoes {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Livro> livros = new ArrayList<>();
    ArrayList<Livro> livrosEmprestados = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void Cadastrar(){
          System.out.println("Digite o autor do livro:");
          String autor = scanner.nextLine();
          System.out.println("Digite o título do livro:");
          String titulo = scanner.nextLine(); 
          System.out.println("Digite a editora do livro:");
          String editora = scanner.nextLine();
          System.out.println("Digite o ano do livro:");
          int ano = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Digite o ISBN do livro:");
          String ISBN = scanner.nextLine();
          
          Livro livro = new Livro(autor, titulo, editora, ano, false, ISBN);

          livros.add(livro);

          System.out.println("\nLivro cadastrado na biblioteca!");
          System.out.println("O autor do livro é: " + livro.getAutor());
          System.out.println("O título do livro é: " + livro.getTitulo());
          System.out.println("A editora do livro é: " + livro.getEditora());
          System.out.println("O ano de publicação do livro é: " + livro.getAno());
          System.out.println("O status de empréstimo do livro é: " + livro.getEmprestado());
          System.out.println("O ISBN do livro é: " + livro.getISBN());
          
    }
    public void listarLivros(){
        System.out.println("\nLivros cadastrados na biblioteca:");
            for (Livro livro : livros) {
                System.out.printf("Autor: " + livro.getAutor());
                System.out.printf("Título: " + livro.getTitulo());
                System.out.printf("Editora: " + livro.getEditora());
                System.out.printf("Ano: " + livro.getAno());
                System.out.printf("Emprestado: " + livro.getEmprestado());
                System.out.printf("ISBN: " + livro.getISBN());
                System.out.printf("---------------------------");
            }
    }

    public void emprestar(){
        System.out.println("Digite o seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o seu cpf:");
        String cpf = scanner.nextLine(); 
        System.out.println("Digite o seu email:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nome, cpf, email);

        usuarios.add(usuario);
        
        System.out.println("\nUsuario cadastrado na biblioteca!");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Email: " + usuario.getEmail());

        System.out.println("Digite o ISBN do livro que deseja tomar como emprestado:\n");
        String isbn = scanner.nextLine();

        Livro livroEncontrado = null;
        for (Livro livro : livros) {
        if (isbn.equals(livro.getISBN())) {
            livroEncontrado = livro;
            break;
        }
    }
        if(livroEncontrado!= null){
            livrosEmprestados.add(livroEncontrado);
            livros.remove(livroEncontrado);
            livroEncontrado.setEmprestado(true);
            System.out.println("Livro emprestado com sucesso!");
        } else {
            System.out.println("ISBN fornecido é invalido.");
        }
        emprestimos.add(new Emprestimo(livroEncontrado, usuario));
        for (Emprestimo emprestimo : emprestimos){
            System.out.println("Livro "+emprestimo.getLivro().getTitulo() + " está emprestado para " + emprestimo.getUsuario().getNome());
        }
}
}
