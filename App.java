import dominio.Emprestimo;
import dominio.Livro;
import dominio.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
        System.out.println("\n1 - Cadastrar livro\n2 - Listar todos os livros\n3 - Pegar livro emprestado\n4 - Devolver livro\n5 - Listar livros emprestados e disponíveis\n6 - Listar histórico de empréstimos do usuário\n7 - Sair");
        System.out.print("\nDigite uma opção:");
        int escolha = scanner.nextInt();    
        
        Livro livro = new Livro();
        livro.setAutor("Machado de Assis");
        livro.setTitulo("Memórias Póstumas de Brás Cubas");
        livro.setEditora("Saraiva");
        livro.setAno(1990);

        Livro livro1 = new Livro();
        livro1.setAutor("Louis Carrol");
        livro1.setTitulo("Alice no país das maravilhas");
        livro1.setEditora("Inovar");
        livro1.setAno(2005);

        Livro livro2 = new Livro();
        livro2.setAutor("Raquel de Queiroz");
        livro2.setTitulo("O Quinze");
        livro2.setEditora("Nova");
        livro2.setAno(1998);
        
        ArrayList<Livro> livros = new ArrayList<>();
        livros.add(livro);
        livros.add(livro1);
        livros.add(livro2);
        

        if (escolha == 1){
            System.out.println("\nLivro cadastrado!");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Ano: " + livro.getAno());

            System.out.println("\nLivro 1 cadastrado!");
            System.out.println("Título: " + livro1.getTitulo());
            System.out.println("Autor: " + livro1.getAutor());
            System.out.println("Editora: " + livro1.getEditora());
            System.out.println("Ano: " + livro1.getAno());

            System.out.println("\nLivro 2 cadastrado!");
            System.out.println("Título: " + livro2.getTitulo());
            System.out.println("Autor: " + livro2.getAutor());
            System.out.println("Editora: " + livro2.getEditora());
            System.out.println("Ano: " + livro2.getAno());
        } else if (escolha == 2){
            System.out.println(livros);
        } else if (escolha == 3){

        }
        }
}
}
