package view;

import javax.swing.*;
import java.awt.*;

import Dao.FuncionarioDAO;
import Model.Funcionario;

public class CadastrarFuncionarioView extends JFrame {

    public CadastrarFuncionarioView() {
        setTitle("Cadastrar Funcionário");
        setSize(400, 400);  // Ajustado o tamanho para acomodar o campo de senha
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);

        JLabel lblCpf = new JLabel("CPF:");
        JTextField txtCpf = new JTextField(15);

        JLabel lblCargo = new JLabel("Cargo:");
        JTextField txtCargo = new JTextField(15);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
        JTextField txtDataNascimento = new JTextField(10);  // Para data no formato dd/mm/aaaa

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField(15);

        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField(30);

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField(15);  // Campo para senha

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        // Layout para os campos de entrada
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));  // Ajustado para 8 campos
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblCargo);
        panel.add(txtCargo);
        panel.add(lblDataNascimento);
        panel.add(txtDataNascimento);
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(lblSenha);
        panel.add(txtSenha);  // Adicionando o campo de senha
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        // Ação para salvar o funcionário
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String cargo = txtCargo.getText();
            String dataNascimento = txtDataNascimento.getText();
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();
            String senha = new String(txtSenha.getPassword());  // Obtendo a senha

            if (nome.isEmpty() || cpf.isEmpty() || cargo.isEmpty() || dataNascimento.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!");
            } else {
                // Criando o objeto Funcionario com a senha
                Funcionario funcionario = new Funcionario(nome, cpf, cargo, telefone, dataNascimento, endereco, senha);

                // Inserindo o funcionário no banco de dados através do DAO
                FuncionarioDAO funcionarioDao = new FuncionarioDAO();
                funcionarioDao.inserirFuncionario(funcionario);

                // Mensagem de sucesso e fechando a janela
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
                dispose();
            }
        });

        // Ação para cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Adicionando o painel à janela
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastrarFuncionarioView().setVisible(true));
    }
}
