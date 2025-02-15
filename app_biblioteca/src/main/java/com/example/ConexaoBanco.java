package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco{
    public static Connection obterConexao(){
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "postgres");
            if (conexao != null){
                System.out.println("Banco de Dados conectado com sucesso!");
                return conexao;
            } else{
                System.out.println("Conexão falhou!");
        
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
                return null;
    }
        
}



