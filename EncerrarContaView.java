package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncerrarContaView extends JFrame {

    private JTextField txtNumeroConta;
    private JButton btnEncerrarConta, btnVoltar;

    public EncerrarContaView() {
        setTitle("Banco Malvader - Encerrar Conta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        txtNumeroConta = new JTextField();
        btnEncerrarConta = new JButton("Encerrar Conta");
        btnVoltar = new JButton("Voltar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Número da Conta:"));
        panel.add(txtNumeroConta);
        panel.add(btnEncerrarConta);
        panel.add(btnVoltar);

        btnEncerrarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarConta();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltar para o menu do funcionário
                new view.FuncionarioMenuView().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }

    private void encerrarConta() {
        String numeroConta = txtNumeroConta.getText();

        // Aqui você pode adicionar a lógica para verificar se a conta existe no banco de dados
        // Se a conta for encontrada, você pode excluí-la.

        JOptionPane.showMessageDialog(this, "Conta " + numeroConta + " encerrada com sucesso!");

        // Voltar para o menu do funcionário após a operação
        new view.FuncionarioMenuView().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new EncerrarContaView().setVisible(true);
    }
}
