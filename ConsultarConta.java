package Controller;

import view.ConsultarContaView;

import javax.swing.*;
import java.awt.*;

public class ConsultarConta extends JFrame {

    private JTextField numeroContaField;
    private JTextArea resultadoArea;

    public ConsultarConta() {
        setTitle("Consultar Conta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JLabel lblNumeroConta = new JLabel("Número da Conta:");
        numeroContaField = new JTextField(20);

        JButton btnConsultar = new JButton("Consultar");
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblNumeroConta, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(numeroContaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnConsultar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        // Botão de consulta
        btnConsultar.addActionListener(e -> consultarConta());

        add(panel);
    }

    private void consultarConta() {
        String numeroConta = numeroContaField.getText().trim();

        if (numeroConta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o número da conta.");
            return;
        }

        // Tentando converter para inteiro caso o número da conta seja numérico
        try {
            int numeroContaInt = Integer.parseInt(numeroConta);

            // Realiza a busca da conta
            String dadosConta = buscarDadosConta(numeroContaInt);

            if (dadosConta != null) {
                resultadoArea.setText(dadosConta);
            } else {
                resultadoArea.setText("Conta não encontrada para o número informado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número da conta inválido. Insira um número válido.");
        }
    }

    // Simulação do método de busca no banco ou controlador
    private String buscarDadosConta(int numeroConta) {
        // Simular consulta - Substitua pela lógica real
        if (numeroConta == 12345) {
            return """
                   Número da Conta: 12345
                   Tipo de Conta: Corrente
                   Saldo: R$ 5,000.00
                   Limite: R$ 2,000.00
                   Data de Vencimento: 10/12/2024
                   """;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarContaView().setVisible(true));
    }
}
