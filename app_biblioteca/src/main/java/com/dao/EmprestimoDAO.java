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
import com.Usuario;
import com.example.ConexaoBanco;

public class EmprestimoDAO {
    private Connection conexao;
    private LivroDAO livroDAO;
    private UsuarioDAO usuarioDAO;

    public EmprestimoDAO() {
        this.conexao = ConexaoBanco.obterConexao();
        this.livroDAO = new LivroDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo, data_devolucao, emprestado_status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, emprestimo.getLivro().getIDLivro());
            stmt.setInt(2, emprestimo.getUsuario().getIDUsuario());
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
            stmt.setString(5, emprestimo.getStatus());

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

    public void atualizarStatus(boolean emprestado, int livro_id) {
        String sql = "UPDATE livro SET emprestado = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setBoolean(1, emprestado);
            stmt.setInt(2, livro_id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Status do livro alterado!");
            } else {
                System.out.println("Erro ao atualizar status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Livro buscarLivroPorId(int id) {
        String sql = "SELECT * FROM livro WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(rs.getString("autor"), rs.getString("titulo"), rs.getString("editora"), rs.getInt("ano"), rs.getBoolean("emprestado"), rs.getString("ISBN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"));
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Emprestimo> listarEmprestimos() {
    List<Emprestimo> emprestimos = new ArrayList<>();
    String sql = "SELECT livro_id, usuario_id, data_emprestimo, data_devolucao, emprestado_status FROM emprestimos";
    ;

    try (PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet emprestimoBD = stmt.executeQuery()) {

        while (emprestimoBD.next()) {
            int livroId = emprestimoBD.getInt("livro_id");
            int usuarioId = emprestimoBD.getInt("usuario_id");

            Livro livro = buscarLivroPorId(livroId);
            Usuario usuario = buscarUsuarioPorId(usuarioId);

            Emprestimo emprestimo = new Emprestimo(
                livro,
                usuario,
                emprestimoBD.getDate("data_emprestimo").toLocalDate(),
                emprestimoBD.getDate("data_emprestimo").toLocalDate().plusDays(7),
                emprestimoBD.getString("emprestado_status")
            );

            emprestimos.add(emprestimo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return emprestimos;
}

    public void devolverLivro(int livro, String status)throws SQLException {
        String sql = "UPDATE emprestimos SET emprestado_status = ? WHERE livro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, livro);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Status alterado!");
            } else {
                System.out.println("Erro ao alterar status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
