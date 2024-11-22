package view;

import Controller.ConsultarFuncionario;

import javax.swing.*;
import java.awt.*;

public class ConsultarFuncionarioView extends JFrame {
    private JTextField txtCpf;
    private JTextArea resultadoArea;
    private ConsultarFuncionario consultarFuncionarioController;

    public ConsultarFuncionarioView() {
        consultarFuncionarioController = new ConsultarFuncionario();

        setTitle("Consultar Funcionário");
        setSize(600, 500); // Ajustando o tamanho da janela para acomodar mais informações
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblCpf = new JLabel("Digite o CPF:");

        // Campo de texto para CPF
        txtCpf = new JTextField(30);

        // Botão de consulta
        JButton btnConsultar = new JButton("Consultar");

        // Área de resultado com rolagem
        resultadoArea = new JTextArea(15, 50);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Botão Voltar ao Menu
        JButton btnVoltar = new JButton("Voltar ao Menu");

        gbc.insets = new Insets(10, 10, 10, 10);

        // Rótulo do CPF
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(lblCpf, gbc);

        // Campo de texto para CPF
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtCpf, gbc);

        // Botão de consulta
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnConsultar, gbc);

        // Área de resultado
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Botão Voltar ao Menu
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnVoltar, gbc);  // Adicionando o botão "Voltar ao Menu"

        // Configurar ação do botão Consultar
        btnConsultar.addActionListener(e -> consultarFuncionario());

        // Configurar ação do botão Voltar
        btnVoltar.addActionListener(e -> voltarAoMenu());
    }

    private void consultarFuncionario() {
        String cpf = txtCpf.getText().trim();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.");
            return;
        }

        // Chamando o controlador para obter as informações
        String resultado = consultarFuncionarioController.exibirInfoFuncionario(cpf);
        resultadoArea.setText(resultado);
    }

    // Método para voltar ao Menu do Funcionário
    private void voltarAoMenu() {
        // Fechar a janela de consulta de funcionário
        dispose();

        // Abrir a janela do Menu do Funcionário
        new view.FuncionarioMenuView().setVisible(true); // Chamando a tela do menu
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarFuncionarioView().setVisible(true));
    }
}
