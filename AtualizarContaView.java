package view;

import Controller.ContaController;
import Model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtualizarContaView extends JFrame {

    private ContaController contaController;
    private JTextField cpfField;
    private JTextField numeroContaField;
    private JTextField tipoContaField;
    private JTextField limiteField;
    private JTextField dataVencimentoField;
    private JButton atualizarButton;
    private JButton cancelarButton;

    public AtualizarContaView() {
        contaController = new ContaController(); // Inicializa o controlador de Conta
        setTitle("Atualizar Conta");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout do formulário
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaField = new JTextField();

        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaField = new JTextField();

        JLabel limiteLabel = new JLabel("Limite:");
        limiteField = new JTextField();

        JLabel dataVencimentoLabel = new JLabel("Data de Vencimento (dd/MM/yyyy):");
        dataVencimentoField = new JTextField();

        atualizarButton = new JButton("Atualizar");
        cancelarButton = new JButton("Cancelar");

        // Adiciona componentes ao layout
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

        add(atualizarButton);
        add(cancelarButton);

        // Listener do botão Atualizar
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarConta(); // Chama o método para atualizar a conta
            }
        });

        // Listener do botão Cancelar
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela se o usuário cancelar
            }
        });
    }

    // Método para atualizar os dados da conta
    private void atualizarConta() {
        String cpf = cpfField.getText();
        String numeroConta = numeroContaField.getText();
        String tipoConta = tipoContaField.getText();
        String limiteStr = limiteField.getText();
        String dataVencimento = dataVencimentoField.getText();

        // Validação dos campos
        if (cpf.isEmpty() || numeroConta.isEmpty() || tipoConta.isEmpty() || limiteStr.isEmpty() || dataVencimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double limite = Double.parseDouble(limiteStr); // Converte o limite para double

            // Busca a conta para verificar se existe
            Conta conta = contaController.consultarContaPorCpf(cpf);
            if (conta != null) {
                // Atualiza a conta com os novos dados
                contaController.atualizarConta(numeroConta, tipoConta, limite, dataVencimento, cpf);
                JOptionPane.showMessageDialog(this, "Conta atualizada com sucesso!");
                dispose(); // Fecha a janela após atualizar
            } else {
                JOptionPane.showMessageDialog(this, "Conta não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O limite deve ser um valor numérico válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AtualizarContaView().setVisible(true);
            }
        });
    }
}
