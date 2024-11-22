package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import util.DataBaseConnection;

public class ConexaoBancoView extends JFrame {

    private JTextField txtStatus;
    private JButton btnTestarConexao;
    private JButton btnFechar;

    public ConexaoBancoView() {
        // Configurações da janela
        setTitle("Testar Conexão com o Banco de Dados");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Definindo layout e componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel lblStatus = new JLabel("Status da Conexão:");
        txtStatus = new JTextField(20);
        txtStatus.setEditable(false);  // O campo de status não deve ser editável

        btnTestarConexao = new JButton("Testar Conexão");
        btnFechar = new JButton("Fechar");

        // Adicionando os componentes ao painel
        panel.add(lblStatus);
        panel.add(txtStatus);
        panel.add(btnTestarConexao);
        panel.add(btnFechar);

        add(panel);

        // Ação do botão "Testar Conexão"
        btnTestarConexao.addActionListener(e -> testarConexao());

        // Ação do botão "Fechar"
        btnFechar.addActionListener(e -> dispose());
    }

    // Método para testar a conexão com o banco de dados
    private void testarConexao() {
        Connection conn = null;
        try {
            // Tentando obter a conexão com o banco de dados
            conn = DataBaseConnection.getConnection();
            if (conn != null) {
                txtStatus.setText("Conexão bem-sucedida!");
            } else {
                txtStatus.setText("Erro na conexão.");
            }
        } finally {
            // Fecha a conexão se estiver aberta
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método main para exibir a janela
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConexaoBancoView().setVisible(true));
    }
}
