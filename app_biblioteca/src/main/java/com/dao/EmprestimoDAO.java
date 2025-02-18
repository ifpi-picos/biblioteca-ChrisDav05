package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Emprestimo;
import com.Livro;
import com.example.ConexaoBanco;

public class EmprestimoDAO {
    LivroDAO livroDAO = new LivroDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    


public void adicionarEmprestimo(int livro_id, int usuario_id, LocalDate data_emprestimo, LocalDate data_devolucao, String emprestado_status) throws SQLException{

    String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao, emprestado_status) VALUES (?, ?, ?, ?, ?)";
    try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, livro_id);
            stmt.setInt(2, usuario_id);
            stmt.setDate(3, Date.valueOf(data_emprestimo));
            stmt.setDate(4, Date.valueOf(data_devolucao));
            stmt.setString(5, emprestado_status);


            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo registrado com sucesso!");
            } else {
                System.out.println("Falha ao registrar o empréstimo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
public static void atualizarStatus(Boolean emprestado,int livro_id){
        String sql = "UPDATE livro SET emprestado = ? WHERE id = ?";

        try (Connection conexao = ConexaoBanco.obterConexao(); 
            PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setBoolean(1, emprestado);
            stmt.setInt(2, livro_id);
            int linhasAfetadas = stmt.executeUpdate();
        if(linhasAfetadas > 0){
            System.out.println("Status do livro alterado!");
        }else{
            System.out.println("Erro ao atualizar status.");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
public static List<Emprestimo> listarEmprestimos(){
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet emprestimoBD = stmt.executeQuery()) {

            while (emprestimoBD.next()) {
                Emprestimo emprestimo = new Emprestimo(
                    emprestimoBD.getInt("livro_id"),
                    emprestimoBD.getInt("usuario_id"),
                    emprestimoBD.getDate("data_emprestimo").toLocalDate(),
                    emprestimoBD.getString("emprestado_status")
                );
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
public static void devolverLivro(int livro_id, String status) throws SQLException{

    String sql = "UPDATE emprestimos SET emprestado_status = ? WHERE livro_id = ?";
    try (Connection conexao = ConexaoBanco.obterConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql)){
        stmt.setString(1, status);
        stmt.setInt(2, livro_id);
        int linhasAfetadas = stmt.executeUpdate();
    if(linhasAfetadas > 0){
        System.out.println("Status alterado!");
    }else{
        System.out.println("Erro ao alterar status.");
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
