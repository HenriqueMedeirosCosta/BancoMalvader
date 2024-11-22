package view;

import Controller.ContaController;
import Model.Conta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarContaView extends JFrame {
    private ContaController contaController;
    private JTextField numeroContaField;
    private JTextField tipoContaField;
    private JTextField limiteField;
    private JTextField dataVencimentoField;
    private JTextField cpfField;
    private JButton salvarButton;
    private JButton cancelarButton;

    public AlterarContaView() {
        contaController = new ContaController();
        setTitle("Alterar Conta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaField = new JTextField();

        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaField = new JTextField();

        JLabel limiteLabel = new JLabel("Novo Limite:");
        limiteField = new JTextField();

        JLabel dataVencimentoLabel = new JLabel("Data de Vencimento (dd/MM/yyyy):");
        dataVencimentoField = new JTextField();

        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        add(cpfLabel);
        add(cpfField);
        add(numeroContaLabel);
        add(numeroContaField);
        add(tipoContaLabel);
        add(tipoContaField);
        add(limiteLabel);
        add(limiteField);
        add(dataVencimentoLabel);
        add(dataVencimentoField);
        add(salvarButton);
        add(cancelarButton);

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarConta();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void alterarConta() {
        String cpf = cpfField.getText();
        String numeroConta = numeroContaField.getText();
        String tipoConta = tipoContaField.getText();
        String limiteStr = limiteField.getText();
        String dataVencimento = dataVencimentoField.getText();

        if (cpf.isEmpty() || numeroConta.isEmpty() || tipoConta.isEmpty() || limiteStr.isEmpty() || dataVencimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double limite = Double.parseDouble(limiteStr);

            Conta conta = contaController.consultarContaPorCpf(cpf);
            if (conta != null && conta.getNumero().equals(numeroConta)) {
                contaController.atualizarConta(numeroConta, tipoConta, limite, dataVencimento, cpf);
                JOptionPane.showMessageDialog(this, "Conta alterada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O limite deve ser um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AlterarContaView().setVisible(true));
    }
}
