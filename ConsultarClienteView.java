package view;

import Controller.ConsultarCliente;

import javax.swing.*;
import java.awt.*;

public class ConsultarClienteView extends JFrame {
    private JTextField txtCpf;
    private JTextArea resultadoArea;
    private ConsultarCliente consultarClienteController;

    public ConsultarClienteView() {
        // Inicializando o controlador
        consultarClienteController = new ConsultarCliente();

        // Definindo título da janela e outras configurações da janela
        setTitle("Consultar Cliente");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fechar a janela quando a aplicação for encerrada
        setLocationRelativeTo(null);  // Centralizar a janela
        setLayout(new GridBagLayout());  // Usando GridBagLayout para arranjar os componentes na tela

        // Instanciando componentes da interface
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblCpf = new JLabel("Digite o CPF do Cliente:");
        txtCpf = new JTextField(15);  // Campo para digitar o CPF
        JButton btnConsultar = new JButton("Consultar");  // Botão para consultar
        resultadoArea = new JTextArea(10, 30);  // Área para exibir os resultados
        resultadoArea.setEditable(false);  // A área de resultados não deve ser editável
        JScrollPane scrollPane = new JScrollPane(resultadoArea);  // Scroll para a área de resultados
        JButton btnVoltar = new JButton("Voltar ao Menu");  // Botão para voltar ao menu do funcionário

        // Definindo as margens entre os componentes
        gbc.insets = new Insets(10, 10, 10, 10);

        // Organizando os componentes na tela com o layout GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblCpf, gbc);

        gbc.gridx = 1;
        add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(btnConsultar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnVoltar, gbc);  // Botão para voltar ao menu

        // Ação do botão "Consultar"
        btnConsultar.addActionListener(e -> consultarCliente());

        // Ação do botão "Voltar"
        btnVoltar.addActionListener(e -> voltarAoMenu());
    }

    // Método para consultar o cliente no banco
    private void consultarCliente() {
        String cpf = txtCpf.getText().trim();  // Remove espaços em branco antes e depois do CPF
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.");
            return;
        }

        // Chamando o controlador para buscar as informações do cliente
        String resultado = consultarClienteController.exibirInfoCliente(cpf);

        if (resultado.isEmpty()) {
            resultado = "Cliente não encontrado para o CPF informado.";  // Mensagem de erro caso não encontre o cliente
        }

        // Exibindo o resultado na área de texto
        resultadoArea.setText(resultado);
    }

    // Método para voltar ao menu do funcionário
    private void voltarAoMenu() {
        dispose();  // Fechar a janela de consulta de cliente
        new view.FuncionarioMenuView().setVisible(true);  // Abertura do menu do funcionário
    }

    // Método principal para mostrar a janela
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarClienteView().setVisible(true));  // Exibir a janela
    }
}
