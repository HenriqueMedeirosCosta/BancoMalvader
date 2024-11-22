package view;

import util.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ConsultarContaView extends JFrame {

    private JTextField numeroContaField;
    private JTextArea resultadoArea;

    public ConsultarContaView() {
        setTitle("Consultar Conta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Rótulo e campo de texto para número da conta
        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        numeroContaField = new JTextField(20);

        // Botão de consulta
        JButton btnConsultar = new JButton("Consultar");

        // Área de resultado (não editável)
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Layout usando GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Adicionando o rótulo e campo de texto ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNumeroConta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(numeroContaField, gbc);

        // Adicionando o botão de consulta ao painel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnConsultar, gbc);

        // Adicionando a área de resultados ao painel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        // Ação do botão de consulta
        btnConsultar.addActionListener(e -> consultarConta());

        // Adicionando o painel à janela
        add(panel);
    }

    private void consultarConta() {
        // Recuperando o número da conta digitado
        String numeroConta = numeroContaField.getText().trim();

        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.");
            return;
        }

        // Realizando consulta ao banco de dados
        String dadosConta = buscarDadosConta(numeroConta);

        if (dadosConta != null) {
            // Exibindo os dados da conta na área de resultados
            resultadoArea.setText(dadosConta);
        } else {
            resultadoArea.setText("Conta não encontrada para o número informado.");
        }
    }

    // Método para buscar os dados da conta no banco de dados
    private String buscarDadosConta(String numeroConta) {
        String sql = "SELECT c.numero_conta, c.tipo_conta, c.saldo, " +
                "cc.limite, cc.data_vencimento, cp.taxa_rendimento " +
                "FROM conta c " +
                "LEFT JOIN conta_corrente cc ON c.id_conta = cc.id_conta " +
                "LEFT JOIN conta_poupanca cp ON c.id_conta = cp.id_conta " +
                "WHERE c.numero_conta = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroConta); // Definindo o número da conta na consulta
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipoConta = rs.getString("tipo_conta");
                double saldo = rs.getDouble("saldo");
                String limite = tipoConta.equals("CORRENTE") ? "Limite: R$ " + rs.getDouble("limite") : "N/A";
                String dataVencimento = tipoConta.equals("CORRENTE") ? "Data de Vencimento: " + rs.getDate("data_vencimento") : "N/A";
                String taxaRendimento = tipoConta.equals("POUPANCA") ? "Taxa de Rendimento: " + rs.getDouble("taxa_rendimento") : "N/A";

                return "Número da Conta: " + numeroConta + "\n" +
                        "Tipo de Conta: " + tipoConta + "\n" +
                        "Saldo: R$ " + saldo + "\n" +
                        limite + "\n" +
                        dataVencimento + "\n" +
                        taxaRendimento;
            } else {
                return null; // Conta não encontrada
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao consultar dados da conta.";
        }
    }

    // Método principal para executar a interface
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarContaView().setVisible(true));
    }
}
