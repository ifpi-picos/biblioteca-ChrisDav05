package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.ConexaoBanco;

public class EmprestimoDAO {
    LivroDAO livroDAO = new LivroDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    


public void adicionarEmprestimo(int livro_id, int usuario_id, LocalDate data_emprestimo, LocalDate data_devolucao) throws SQLException{

    String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo,data_devolucao) VALUES (?, ?, ?, ?)";
    try (Connection conexao = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, livro_id);
            stmt.setInt(2, usuario_id);
            stmt.setDate(3, Date.valueOf(data_emprestimo));
            stmt.setDate(4, Date.valueOf(data_devolucao)); 


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
public static void atualizarStatus(int livro_id, Boolean emprestado){
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

public void devolverLivro(int livro_id, Boolean emprestado_status) throws SQLException{

    String sql = "UPDATE emprestimos SET emprestado_status = ? WHERE id = ?";
    try (Connection conexao = ConexaoBanco.obterConexao();
        PreparedStatement stmt = conexao.prepareStatement(sql)){
        stmt.setBoolean(1, emprestado_status);
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
