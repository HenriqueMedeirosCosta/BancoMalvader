package view;

import javax.swing.*;
import Controller.ContaController;

public class ConsultarSaldoView extends JFrame {

    private JTextField cpfField;
    private JLabel saldoLabel;
    private ContaController contaController;

    public ConsultarSaldoView() {
        // Configurações da janela
        setTitle("Consultar Saldo");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicializa o controlador de conta
        contaController = new ContaController();

        // Layout
        setLayout(null);

        // Campo para inserir o CPF
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(20, 20, 80, 25);
        add(cpfLabel);

        cpfField = new JTextField();
        cpfField.setBounds(100, 20, 150, 25);
        add(cpfField);

        // Botão para consultar o saldo
        JButton consultarButton = new JButton("Consultar");
        consultarButton.setBounds(100, 60, 120, 25);
        add(consultarButton);

        // Rótulo para mostrar o saldo
        saldoLabel = new JLabel("Saldo disponível: R$ 0,00");
        saldoLabel.setBounds(20, 100, 250, 25);
        add(saldoLabel);

        // Ação do botão
        consultarButton.addActionListener(e -> consultarSaldo());
    }

    private void consultarSaldo() {
        // Obtém o CPF inserido pelo usuário
        String cpf = cpfField.getText().trim();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.");
            return;
        }

        // Chama o controlador para consultar o saldo
        double saldo = contaController.consultarSaldo(cpf);

        if (saldo == -1) {
            saldoLabel.setText("Cliente não encontrado.");
        } else {
            saldoLabel.setText("Saldo disponível: R$ " + String.format("%.2f", saldo));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConsultarSaldoView().setVisible(true);
        });
    }
}
