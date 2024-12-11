import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Livro livro = new Livro();
        Scanner scanner = new Scanner(System.in);
        livro.setAutor("Machado");
        livro.setTitulo("Memórias Póstumas de Brás Cubas");
        livro.setEditora("Saraiva");
        livro.setAno(1990);
        ArrayList<Livro> livros = new ArrayList<>();
        livros.add(livro);

        while (true) {
        System.out.println("1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Listar livros emprestados e disponíveis\n4 - Listar histórico de empréstimos do usuário\n5 - Pegar livro emprestado\n6 - Devolver livro\n7 - Sair");
        System.out.print("\nDigite uma opção:");
        int escolha = scanner.nextInt();
    
        if (escolha == 1){
            System.out.println("\nLivro cadastrado!");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano: " + livro.getAno());
        } else if (escolha == 2){
            System.out.println(livros);
        }
        }
}
}
