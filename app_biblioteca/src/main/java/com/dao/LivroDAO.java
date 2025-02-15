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
    public void adicionarLivro(Livro livro){
        String sql = "INSERT INTO livro (autor, titulo, editora, ano, ISBN, emprestado ) VALUES (?, ?, ?, ?, ?, false)";

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getTitulo());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setString(5, livro.getISBN());
            stmt.setBoolean(6, livro.getEmprestado());
            stmt.executeUpdate();
            System.out.println("Livro cadastrado na biblioteca!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
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

    public static int buscaIdPorTitulo(String Titulo){
        String sql = "SELECT id FROM livro WHERE titulo = ?";

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
                stmt.setString(1, Titulo);
                ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            return rs.getInt("id");
        }        
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro: " + e.getMessage());
        }
        return -1;
             
}
    
}

