import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
          System.out.println("Digite o ISBN do livro:");
          String ISBN = scanner.nextLine();
          
          Livro livro = new Livro(autor, titulo, editora, ano, false, ISBN);

          livros.add(livro);

          System.out.println("\nLivro cadastrado na biblioteca!");
          System.out.println("\nO autor do livro é: " + livro.getAutor());
          System.out.println("O título do livro é: " + livro.getTitulo());
          System.out.println("A editora do livro é: " + livro.getEditora());
          System.out.println("O ano de publicação do livro é: " + livro.getAno());
          System.out.println("O status de empréstimo do livro é: " + livro.getEmprestado());
          System.out.println("O ISBN do livro é: " + livro.getISBN());
          
    }
    public void ListarLivros(){
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
        System.out.println("Digite o seu cpf:");
        String cpf = scanner.nextLine(); 
        System.out.println("Digite o seu email:");
        String email = scanner.nextLine();
        Usuario usuario = new Usuario(nome, cpf, email);

        usuarios.add(usuario);
        
        System.out.println("\nUsuario cadastrado na biblioteca!");
        System.out.println("\nNome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Email: " + usuario.getEmail());
    }
    public void Emprestar(){
        System.out.println("Lista dos livros da biblioteca");
        ListarLivros();
        System.out.println("\nDigite o ISBN do livro que deseja tomar como emprestado:");
        String isbn = scanner.nextLine();

        System.out.println("Digite o nome do usuario que quer tomar emprestado um livro:");
        String nomeProcura = scanner.nextLine();

        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
        if (nomeProcura.equals(usuario.getNome())) {
            usuarioEncontrado = usuario;
            break;
        }
    }
        if(usuarioEncontrado!= null){
            System.out.println("Usuario encontrado!");
        } else {
            System.out.println("Usuario não encontrado.");
        }

        Livro livroEncontrado = null;
        for (Livro livro : livros) {
        if (isbn.equals(livro.getISBN())) {
            livroEncontrado = livro;
            break;
        }
    }
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucao = dataEmprestimo.plusDays(7);

        if(livroEncontrado!= null){
            livrosEmprestados.add(livroEncontrado);
            livros.remove(livroEncontrado);
            livroEncontrado.setEmprestado(true);
            usuarioEncontrado.incrementarEmprestimos();
            int contagemEmprestimos = usuarioEncontrado.getContadorEmprestimos();
            System.out.println("Livro emprestado com sucesso!");
            System.out.println("O usuário " + usuarioEncontrado.getNome() + " já pegou o livro com ISBN " + isbn +
                " " + contagemEmprestimos + " vez(es).");
        } else {
            System.out.println("ISBN fornecido é invalido.");
        }
        emprestimos.add(new Emprestimo(livroEncontrado, usuarioEncontrado, dataEmprestimo, dataDevolucao));
        for (Emprestimo emprestimo : emprestimos){
            System.out.println("Livro "+emprestimo.getLivro().getTitulo() + " está emprestado para " + emprestimo.getUsuario().getNome() + ".\nData de empréstimo: "+ emprestimo.getDataEmprestimo()+".\nData de devolução: "+ emprestimo.getDataDevolucao()+".");
        }
    }
    public void Devolver (){
        System.out.println("Digite o ISBN do livro que deseja devolver: ");
        String isbn = scanner.nextLine();
        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo : emprestimos) {
        if (emprestimo.getLivro().getISBN().equals(isbn)) {
            emprestimoEncontrado = emprestimo;
            break;
        }
    }
        if(emprestimoEncontrado != null){
            LocalDate dataAtual = LocalDate.now();
            Livro livro = emprestimoEncontrado.getLivro();

            if (dataAtual.isAfter(emprestimoEncontrado.getDataDevolucao())) {
            long diasAtraso = ChronoUnit.DAYS.between(emprestimoEncontrado.getDataDevolucao(), dataAtual);
            System.out.println("Livro devolvido com atraso de " + diasAtraso + " dias.");
        } else {
            System.out.println("Livro devolvido no prazo. Obrigado!");
        }
        livro.setEmprestado(false);
        livros.add(livro);
        livrosEmprestados.remove(livro);
        emprestimos.remove(emprestimoEncontrado);
        } else {
            System.out.println("Nenhum empréstimo encontrado para o ISBN fornecido.");
        }
    }
    public void ListarLivrosDisp(){
        System.out.println("\nLivros disponíveis:");
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
    public void ListarLivrosEmp(){
        System.out.println("\nLivros emprestados:");
            for (Livro livrosEmprestados : livrosEmprestados) {
                System.out.println("\nAutor: " + livrosEmprestados.getAutor());
                System.out.println("Título: " + livrosEmprestados.getTitulo());
                System.out.println("Editora: " + livrosEmprestados.getEditora());
                System.out.println("Ano: " + livrosEmprestados.getAno());
                System.out.println("ISBN: " + livrosEmprestados.getISBN());
                System.out.println("---------------------------");
            }
    }
    public void ListarHistoricoEmp(){
        System.out.println("Digite o nome do usuario que deseja ver o histórico de empréstimos:");
        String nomeUsuario = scanner.nextLine();
        Usuario usuarioEncontrado = null;
        for (Usuario usuario : usuarios) {
        if (nomeUsuario.equals(usuario.getNome())) {
            usuarioEncontrado = usuario;
            break;
        }
    }
        if(usuarioEncontrado!= null){
            System.out.println("Usuario encontrado!");
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getUsuario().equals(usuarioEncontrado)) {
                    System.out.println("Livro: " + emprestimo.getLivro().getTitulo() +
                            " - Data de empréstimo: " + emprestimo.getDataEmprestimo() +
                            " - Data de devolução: " + emprestimo.getDataDevolucao());
                }
            }
        } else {
            System.out.println("Usuario não encontrado.");
        }
}
}
