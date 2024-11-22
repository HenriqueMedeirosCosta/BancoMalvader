package view.Main;

import Controller.ClienteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteView extends JFrame {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField enderecoField;
    private JTextField telefoneField;
    private JButton cadastrarButton;

    private ClienteController clienteController = new ClienteController();

    public ClienteView() {
        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(30, 30, 100, 30);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(140, 30, 200, 30);
        add(nomeField);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setBounds(30, 70, 100, 30);
        add(cpfLabel);

        cpfField = new JTextField();
        cpfField.setBounds(140, 70, 200, 30);
        add(cpfField);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(30, 110, 100, 30);
        add(enderecoLabel);

        enderecoField = new JTextField();
        enderecoField.setBounds(140, 110, 200, 30);
        add(enderecoField);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setBounds(30, 150, 100, 30);
        add(telefoneLabel);

        telefoneField = new JTextField();
        telefoneField.setBounds(140, 150, 200, 30);
        add(telefoneField);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(140, 200, 120, 30);
        add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clienteController.cadastrarCliente(
                        nomeField.getText(),
                        cpfField.getText(),
                        enderecoField.getText(),
                        telefoneField.getText()
                );
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                limparCampos();
            }
        });

        setVisible(true);
    }

    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        enderecoField.setText("");
        telefoneField.setText("");
    }

    public static void main(String[] args) {
        new ClienteView();
    }
}
