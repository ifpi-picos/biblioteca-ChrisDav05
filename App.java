import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Livro> livrosEmprestados = new ArrayList<>();
        Acoes obj = new Acoes();
        
        while (true) {
        System.out.println("\nSEJA BEM-VINDO A BIBLIOTECA QUE TEM UM BOOKADO DE LIVRO");
        System.out.println("\n1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
        System.out.print("\nDigite uma opção:");
        int escolha = scanner.nextInt();    
        scanner.nextLine();

        if (escolha == 1){ 
            obj.Cadastrar();
        } else if (escolha == 2){
            obj.listarLivros();
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
