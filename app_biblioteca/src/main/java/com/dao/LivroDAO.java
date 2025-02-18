package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Livro;
import com.example.ConexaoBanco;

public class LivroDAO {
    private Connection conexao;

    public LivroDAO() {
        this.conexao = ConexaoBanco.obterConexao();
    }

    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livro (autor, titulo, editora, ano, ISBN, emprestado) VALUES (?, ?, ?, ?, ?, false)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setString(5, livro.getISBN());
            stmt.executeUpdate();
            System.out.println("Livro cadastrado na biblioteca!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet livroBD = stmt.executeQuery()) {

            while (livroBD.next()) {
                Livro livro = new Livro(
                    livroBD.getString("autor"),
                    livroBD.getString("titulo"),
                    livroBD.getString("editora"),
                    livroBD.getInt("ano"),
                    livroBD.getBoolean("emprestado"),
                    livroBD.getString("ISBN")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE emprestado = false";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet livroDisponiveisBD = stmt.executeQuery()) {

            while (livroDisponiveisBD.next()) {
                Livro livroDisponivel = new Livro(
                    livroDisponiveisBD.getString("autor"),
                    livroDisponiveisBD.getString("titulo"),
                    livroDisponiveisBD.getString("editora"),
                    livroDisponiveisBD.getInt("ano"),
                    livroDisponiveisBD.getBoolean("emprestado"),
                    livroDisponiveisBD.getString("ISBN")
                );
                livrosDisponiveis.add(livroDisponivel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livrosDisponiveis;
    }

    public List<Livro> listarLivrosEmprestados() {
        List<Livro> livrosEmprestados = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE emprestado = true";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet livroEmprestadoBD = stmt.executeQuery()) {

            while (livroEmprestadoBD.next()) {
                Livro livroEmprestado = new Livro(
                    livroEmprestadoBD.getString("autor"),
                    livroEmprestadoBD.getString("titulo"),
                    livroEmprestadoBD.getString("editora"),
                    livroEmprestadoBD.getInt("ano"),
                    livroEmprestadoBD.getBoolean("emprestado"),
                    livroEmprestadoBD.getString("ISBN")
                );
                livrosEmprestados.add(livroEmprestado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livrosEmprestados;
    }

    public int buscaIdPorTitulo(String titulo) {
        String sql = "SELECT id FROM livro WHERE titulo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage());
        }
        return -1;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
