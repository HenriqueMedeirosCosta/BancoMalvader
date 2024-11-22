package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.MaskFormatter;

public class AbrirContaView extends JFrame {

    // Definição dos campos de texto para os dados do cliente
    private JTextField tfAgencia, tfNumeroConta, tfNomeCliente, tfCpfCliente, tfTelefone, tfEnderecoCep,
            tfNumeroCasa, tfBairro, tfCidade, tfEstado, tfSenhaCliente, tfLimiteConta, tfDataVencimento;
    private JFormattedTextField tfDataNascimento;
    private JComboBox<String> cbTipoConta;

    // Painéis para campos de Conta Corrente
    private JPanel panelContaCorrente;

    public AbrirContaView() {
        setTitle("Abrir Conta - Banco Malvader");
        setSize(500, 700); // Ajuste o tamanho da tela conforme necessário
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Criando os painéis
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Usando GridBagLayout para flexibilidade
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Inicializando os campos de texto
        tfAgencia = new JTextField(20);
        tfNumeroConta = new JTextField(20);
        tfNomeCliente = new JTextField(20);
        tfCpfCliente = new JTextField(20);
        tfTelefone = new JTextField(20);
        tfEnderecoCep = new JTextField(20);
        tfNumeroCasa = new JTextField(20);
        tfBairro = new JTextField(20);
        tfCidade = new JTextField(20);
        tfEstado = new JTextField(20);
        tfSenhaCliente = new JTextField(20);

        // Criando e configurando o campo de Data de Nascimento com máscara
        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####"); // Formato dd/MM/yyyy
            mascaraData.setPlaceholderCharacter('_'); // Usando _ como caractere de espaço vazio
        } catch (Exception e) {
            e.printStackTrace();
        }
        tfDataNascimento = new JFormattedTextField(mascaraData);

        // ComboBox para escolher o tipo de conta
        cbTipoConta = new JComboBox<>(new String[] { "Conta Poupança", "Conta Corrente" });

        // Campos específicos para Conta Corrente
        tfLimiteConta = new JTextField(20);
        tfDataVencimento = new JTextField(20);

        panelContaCorrente = new JPanel();
        panelContaCorrente.setLayout(new GridLayout(2, 2));
        panelContaCorrente.add(new JLabel("Limite da Conta:"));
        panelContaCorrente.add(tfLimiteConta);
        panelContaCorrente.add(new JLabel("Data de Vencimento:"));
        panelContaCorrente.add(tfDataVencimento);
        panelContaCorrente.setVisible(false); // Inicialmente oculto

        // Adicionando os componentes ao painel com GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Agência:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(tfAgencia, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Número da Conta:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(tfNumeroConta, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Nome do Cliente:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(tfNomeCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("CPF do Cliente:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(tfCpfCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Data de Nascimento:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(tfDataNascimento, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(tfTelefone, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("CEP:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(tfEnderecoCep, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Número da Casa:"), gbc);
        gbc.gridx = 1; gbc.gridy = 7;
        panel.add(tfNumeroCasa, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Bairro:"), gbc);
        gbc.gridx = 1; gbc.gridy = 8;
        panel.add(tfBairro, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1; gbc.gridy = 9;
        panel.add(tfCidade, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        panel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1; gbc.gridy = 10;
        panel.add(tfEstado, gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        panel.add(new JLabel("Senha do Cliente:"), gbc);
        gbc.gridx = 1; gbc.gridy = 11;
        panel.add(tfSenhaCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 12;
        panel.add(new JLabel("Tipo de Conta:"), gbc);
        gbc.gridx = 1; gbc.gridy = 12;
        panel.add(cbTipoConta, gbc);

        // Adicionando painel da Conta Corrente
        gbc.gridx = 0; gbc.gridy = 13;
        gbc.gridwidth = 2;
        panel.add(panelContaCorrente, gbc);

        // Criando o botão para salvar os dados
        JButton btnSalvar = new JButton("Salvar");

        // Ação para o botão "Salvar"
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agencia = tfAgencia.getText();
                String numeroConta = tfNumeroConta.getText();
                String nomeCliente = tfNomeCliente.getText();
                String cpfCliente = tfCpfCliente.getText();
                String dataNascimento = tfDataNascimento.getText();
                String telefone = tfTelefone.getText();
                String enderecoCep = tfEnderecoCep.getText();
                String numeroCasa = tfNumeroCasa.getText();
                String bairro = tfBairro.getText();
                String cidade = tfCidade.getText();
                String estado = tfEstado.getText();
                String senhaCliente = tfSenhaCliente.getText();
                String tipoConta = (String) cbTipoConta.getSelectedItem();

                if (tipoConta.equals("Conta Corrente")) {
                    String limiteConta = tfLimiteConta.getText();
                    String dataVencimento = tfDataVencimento.getText();
                    // Salvar ou processar os dados de Conta Corrente
                    System.out.println("Conta Corrente criada para: " + nomeCliente);
                    System.out.println("Limite da Conta: " + limiteConta);
                    System.out.println("Data de Vencimento: " + dataVencimento);
                } else {
                    // Salvar ou processar os dados de Conta Poupança
                    System.out.println("Conta Poupança criada para: " + nomeCliente);
                }
            }
        });

        // Adicionando o botão ao painel
        gbc.gridx = 0; gbc.gridy = 14;
        gbc.gridwidth = 2; // O botão ocupa as duas colunas
        panel.add(btnSalvar, gbc);

        // Adicionando o painel à janela
        add(panel);

        // Ação para atualizar os campos ao escolher o tipo de conta
        cbTipoConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbTipoConta.getSelectedItem().equals("Conta Corrente")) {
                    panelContaCorrente.setVisible(true); // Mostrar campos específicos da conta corrente
                } else {
                    panelContaCorrente.setVisible(false); // Ocultar campos de conta corrente
                }
            }
        });
    }

    public static void main(String[] args) {
        new AbrirContaView().setVisible(true);
    }
}
