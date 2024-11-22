package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // URL de conexão, usuário e senha do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvader";
    private static final String USER = "root";
    private static final String PASSWORD = "Enzo1537";

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        try {
            // Estabelecendo a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);  // Lançando exceção para indicar falha na conexão
        }
    }

    // Método para desconectar do banco de dados (fecha a conexão)
    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();  // Fecha a conexão com o banco
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar do banco de dados: " + e.getMessage());
            }
        }
    }
}
