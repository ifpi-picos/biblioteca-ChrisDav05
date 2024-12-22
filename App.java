import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Acoes obj = new Acoes();
        
        while (true) {
        System.out.println("\nSEJA BEM-VINDO A BIBLIOTECA QUE TEM UM BOOKADO DE LIVRO");
        System.out.println("\n0 - Cadastrar livro\n1 - Listar todos os livros\n2 - Cadastrar usuário\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
        System.out.print("\nDigite uma opção:");
        int escolha = scanner.nextInt();    
        scanner.nextLine();

        if (escolha == 0){ 
            obj.Cadastrar();
        } else if (escolha == 1){
            obj.ListarLivros();
        } else if (escolha == 2){
            obj.CadUsuario();
        } else if (escolha == 3){
            obj.Emprestar();
        }
        }
        }
}

