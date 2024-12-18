import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //DECLARAÇÃO DOS ARRAYS 
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Livro> livrosEmprestados = new ArrayList<>();

        
        while (true) {
        System.out.println("\nSEJA BEM-VINDO A BIBLIOTECA QUE TEM UM BOOKADO DE LIVRO");
        System.out.println("\n1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
        System.out.print("\nDigite uma opção:");
        int escolha = scanner.nextInt();    
        scanner.nextLine();

        if (escolha == 1){ 
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
          
        } else if (escolha == 2){
            System.out.println("Livros cadastrados na biblioteca:");
            for (Livro livro : livros) {
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Emprestado: " + livro.getEmprestado());
                System.out.println("ISBN: " + livro.getISBN());
                System.out.println("---------------------------");
            }
        // } else if (escolha == 3){
        //     System.out.println("Digite o ISBN do livro que deseja tomar como emprestado:\n");
        //     String ISBN = scanner.nextLine();
        //     if (ISBN == ){
        //         livros.remove(1);
        //         System.out.println("Livro emprestado!" + livrosEmprestados.add());
        //     }
        }
        }
}
}
