import java.util.ArrayList;
import java.util.Scanner;

import dominio.Livro;
import dominio.Usuario;

public class Acoes {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Livro> livros = new ArrayList<>();

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
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Emprestado: " + livro.getEmprestado());
                System.out.println("ISBN: " + livro.getISBN());
                System.out.println("---------------------------");
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
        
        System.out.println("\nUsuario cadastrado na biblioteca!");
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Email: " + usuario.getEmail());

        System.out.print("Digite o titulo do livro que deseja tomar como emprestado:");
        String verificaLivro = scanner.nextLine();

    }
}
