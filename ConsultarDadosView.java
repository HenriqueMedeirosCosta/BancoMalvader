package view;

import Controller.UsuarioController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarDadosView extends JFrame {
    private JTextField cpfField;
    private JTextArea resultadoArea;
    private UsuarioController usuarioController;

    public ConsultarDadosView() {
        usuarioController = new UsuarioController();

        // Configurações da janela
        setTitle("Consultar Dados do Cliente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Label e campo para entrada de CPF
        JLabel lblCpf = new JLabel("Digite o CPF:");
        cpfField = new JTextField(15);

        // Botão para buscar dados
        JButton btnConsultar = new JButton("Consultar");

        // Área de texto para exibir os resultados
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Adicionando os componentes à janela
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblCpf, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(cpfField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(btnConsultar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Ação do botão "Consultar"
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um CPF.");
                    return;
                }

                // Defina o tipo de usuário conforme o contexto
                String tipo = "CLIENTE";
                consultarDados(cpf, tipo);
            }
        });
    }

    // Método para consultar os dados de um usuário
    private void consultarDados(String cpf, String tipo) {
        try {
            // Consultar informações do usuário com base no CPF e tipo
            String dadosUsuario = usuarioController.consultarUsuarioPorCPF(cpf, tipo);

            if (dadosUsuario != null) {
                resultadoArea.setText(dadosUsuario);
            } else {
                resultadoArea.setText("Nenhum usuário encontrado com o CPF fornecido.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarDadosView().setVisible(true));
    }
}
