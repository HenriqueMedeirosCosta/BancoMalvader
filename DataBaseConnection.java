package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    // URL de conexão, usuário e senha (exemplo, altere conforme seu banco de dados)
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvader";
    private static final String USER = "root";
    private static final String PASSWORD = "Enzo1537";

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para desconectar do banco de dados (fecha a conexão)
    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
