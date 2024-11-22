package Dao;

import Model.Usuario;
import util.DataBaseConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

    // Método para buscar um usuário por CPF
    public Usuario buscarPorCpf(String cpf) {
        // Definição da consulta SQL para buscar o usuário pelo CPF
        String query = "SELECT * FROM usuario WHERE cpf = ?";
        Usuario usuario = null;

        // Tentativa de conexão com o banco de dados
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Configura o CPF como parâmetro na consulta SQL
            stmt.setString(1, cpf);

            // Executa a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Se o usuário for encontrado, cria e retorna o objeto Usuario
                if (rs.next()) {
                    // Usando o construtor com parâmetros para criar o objeto Usuario
                    usuario = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getDate("data_nascimento"),
                            rs.getString("telefone"),
                            rs.getString("tipo_usuario"),
                            rs.getString("senha")
                    );

                    // Verificar se o tipo de usuário é ADMINISTRADOR
                    if ("ADMINISTRADOR".equals(usuario.getTipoUsuario())) {
                        // Aqui você pode adicionar algum comportamento extra ou mensagem
                        System.out.println("Usuário é um Administrador.");
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar o usuário pelo CPF", e);
        }

        return usuario; // Retorna o usuário encontrado ou null
    }
}
