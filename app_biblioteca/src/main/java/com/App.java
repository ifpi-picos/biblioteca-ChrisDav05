package com;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Acoes obj = new Acoes();
        
        while (true) {
        System.out.println("\nBIBLIOTECA UM BOOKADO DE LIVRO");
        System.out.println("\n0 - Cadastrar livro\n1 - Listar todos os livros\n2 - Cadastrar usuário\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Sair");
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
        } else if (escolha == 4){
            obj.Devolver();
        } else if (escolha == 5){
            System.out.print("\nDigite uma opção:\n1 - Listar livros disponiveis\n2 - Listar livros emprestados\nDigite a opção: ");
            int opcao = scanner.nextInt();    
            scanner.nextLine();
            if (opcao == 1){
                obj.ListarLivrosDisponiveis();
            } else if (opcao == 2){
                obj.ListarLivrosEmprestados();
            }
        } else if(escolha == 6){
            System.out.println("OBRIGADO POR USAR O SISTEMA.");
            break;    
        }
        }
        }
        
}

