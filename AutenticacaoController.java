package Controller;

import java.sql.*;
import util.DataBaseConnection;

public class AutenticacaoController {

    public boolean autenticarUsuario(String cpf, String senha) {
        String query = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpf);  // CPF inserido pelo usuário
            stmt.setString(2, senha); // Senha inserida pelo usuário

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Verifica o tipo de usuário
                String tipoUsuario = rs.getString("tipo_usuario");

                // Verificação para FUNCIONARIO ou CLIENTE
                if ("FUNCIONARIO".equals(tipoUsuario)) {
                    System.out.println("Bem-vindo, Funcionário!");
                    return true;  // Usuário funcionário autenticado com sucesso
                } else if ("CLIENTE".equals(tipoUsuario)) {
                    System.out.println("Bem-vindo, Cliente!");
                    return true;  // Usuário cliente autenticado com sucesso
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Se não encontrar o usuário ou senha incorreta
    }
}
