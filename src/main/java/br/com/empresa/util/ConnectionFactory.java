package br.com.empresa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por criar e gerenciar a conexão com o banco de dados.
 * Usar uma "fábrica de conexões" centraliza a configuração em um único lugar.
 */
public class ConnectionFactory {

    // Configurações de conexão com o banco de dados MySQL que criamos
    private static final String URL = "jdbc:mysql://localhost:3306/empresa?useSSL=false&serverTimezone=UTC";
    private static final String USER = "devuser";
    private static final String PASSWORD = "devpass123"; // A senha que você definiu no script SQL

    /**
     * Obtém uma nova conexão com o banco de dados.
     * Toda vez que precisarmos falar com o banco, chamaremos este método.
     * @return um objeto Connection.
     * @throws SQLException se ocorrer um erro ao conectar.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Em versões mais recentes do JDBC, o registro do Driver é automático.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Imprime uma mensagem de erro clara no console antes de lançar a exceção
            System.err.println("ERRO: Falha na conexão com o banco de dados: " + e.getMessage());
            throw e; // Lança a exceção para que a classe que chamou o método saiba do erro.
        }
    }
}