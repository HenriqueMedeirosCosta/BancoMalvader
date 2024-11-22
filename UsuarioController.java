package Controller;

import util.DataBaseConnection;
import java.sql.*;

public class UsuarioController {

    // Método para autenticar um usuário (Cliente ou Funcionário) com base no CPF, senha e tipo
    public boolean autenticar(String cpf, String senha, String tipo) {
        boolean autenticado = false;

        try (Connection conn = DataBaseConnection.getConnection()) {
            // Consulta corrigida: usando 'tipo_usuario' em vez de 'usuario_tipo'
            String query = "SELECT * FROM usuario WHERE cpf = ? AND senha = ? AND tipo_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            stmt.setString(3, tipo.toUpperCase()); // Verifica o tipo (CLIENTE ou FUNCIONÁRIO)
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                autenticado = true;
                System.out.println(tipo + " autenticado com sucesso!");
            } else {
                System.out.println("Credenciais de " + tipo + " inválidas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autenticado;
    }

    // Método para autenticar um usuário independentemente do tipo
    public boolean autenticarUsuario(String cpf, String senha) {
        return autenticar(cpf, senha, "CLIENTE") || autenticar(cpf, senha, "FUNCIONÁRIO");
    }

    // Método para consultar um usuário pelo CPF e tipo
    public String consultarUsuarioPorCPF(String cpf, String tipo) {
        StringBuilder dadosUsuario = new StringBuilder();

        try (Connection conn = DataBaseConnection.getConnection()) {
            // Consulta corrigida: usando 'tipo_usuario' em vez de 'usuario_tipo'
            String query = "SELECT * FROM usuario WHERE cpf = ? AND tipo_usuario = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);
            stmt.setString(2, tipo.toUpperCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dadosUsuario.append("CPF: ").append(rs.getString("cpf")).append("\n");
                dadosUsuario.append("Nome: ").append(rs.getString("nome")).append("\n");
                dadosUsuario.append("Data de Nascimento: ").append(rs.getDate("data_nascimento")).append("\n");
                dadosUsuario.append("Telefone: ").append(rs.getString("telefone")).append("\n");
                dadosUsuario.append("Tipo de Usuário: ").append(rs.getString("tipo_usuario")).append("\n");
            } else {
                System.out.println("Nenhum usuário encontrado para o CPF: " + cpf);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return dadosUsuario.toString();
    }

    // Método para cadastrar um novo usuário
    public boolean cadastrarUsuario(String cpf, String nome, String dataNascimento, String telefone, String senha, String tipo) {
        String query = "INSERT INTO usuario (cpf, nome, data_nascimento, telefone, senha, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, dataNascimento);
            stmt.setString(4, telefone);
            stmt.setString(5, senha);
            stmt.setString(6, tipo.toUpperCase());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(tipo + " cadastrado com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao cadastrar " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Método para atualizar informações de um usuário
    public boolean atualizarUsuario(String cpf, String nome, String dataNascimento, String telefone, String senha, String tipo) {
        String query = "UPDATE usuario SET nome = ?, data_nascimento = ?, telefone = ?, senha = ? WHERE cpf = ? AND tipo_usuario = ?";

        try (Connection conn = DataBaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, telefone);
            stmt.setString(4, senha);
            stmt.setString(5, cpf);
            stmt.setString(6, tipo.toUpperCase());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados do " + tipo + " atualizados com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao atualizar dados do " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Método para deletar um usuário
    public boolean deletarUsuario(String cpf, String tipo) {
        String query = "DELETE FROM usuario WHERE cpf = ? AND tipo_usuario = ?";

        try (Connection conn = DataBaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);
            stmt.setString(2, tipo.toUpperCase());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(tipo + " deletado com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao deletar " + tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
