package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Usuario;
import com.example.ConexaoBanco;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = ConexaoBanco.obterConexao();
    }

    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (Cpf, Nome, Email) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet usuarioBD = stmt.executeQuery()) {

            while (usuarioBD.next()) {
                Usuario usuario = new Usuario(
                    usuarioBD.getString("cpf"),
                    usuarioBD.getString("nome"),
                    usuarioBD.getString("email")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public int buscaIdPorNome(String nome) {
        String sql = "SELECT id FROM usuario WHERE nome = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage());
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
