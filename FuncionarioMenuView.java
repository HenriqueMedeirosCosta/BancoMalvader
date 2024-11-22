package view;

import javax.swing.*;
import java.awt.*;

public class FuncionarioMenuView extends JFrame {

    public FuncionarioMenuView() {
        setTitle("Banco Malvader - Menu Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JButton btnAbrirConta = new JButton("Abrir Conta");
        JButton btnEncerrarConta = new JButton("Encerrar Conta");
        JButton btnConsultarDados = new JButton("Consultar Dados");
        JButton btnAlterarDados = new JButton("Alterar Dados");
        JButton btnCadastrarFuncionario = new JButton("Cadastrar Funcionário");
        JButton btnGerarRelatorio = new JButton("Gerar Relatório");
        JButton btnSair = new JButton("Sair");

        // Criando o painel com layout de Grid
        JPanel panel = new JPanel(new GridLayout(7, 1));
        panel.add(btnAbrirConta);
        panel.add(btnEncerrarConta);
        panel.add(btnConsultarDados);
        panel.add(btnAlterarDados);
        panel.add(btnCadastrarFuncionario);
        panel.add(btnGerarRelatorio);
        panel.add(btnSair);

        // Adicionando ActionListener para o botão Consultar Dados (solicitando senha)
        btnConsultarDados.addActionListener(e -> solicitarSenha("consultar", this::abrirSubmenuConsulta));

        // Botões para outras funcionalidades
        btnAbrirConta.addActionListener(e -> new view.AbrirContaView().setVisible(true));
        btnEncerrarConta.addActionListener(e -> new view.EncerrarContaView().setVisible(true));
        btnAlterarDados.addActionListener(e -> solicitarSenha("alterar", this::abrirSubmenuAlterarDados)); // Chama o submenu de alteração
        btnCadastrarFuncionario.addActionListener(e -> new CadastrarFuncionarioView().setVisible(true));
        btnGerarRelatorio.addActionListener(e -> new view.GerarRelatorioView().setVisible(true));

        // Botão de sair para voltar à tela de login
        btnSair.addActionListener(e -> {
            new view.LoginView().setVisible(true);
            dispose();
        });

        // Adicionando o painel à janela
        add(panel);
    }

    // Método para abrir o submenu de consulta (sem solicitar senha de funcionário)
    private void abrirSubmenuConsulta() {
        JFrame submenu = new JFrame("Submenu - Consultar Dados");
        submenu.setSize(400, 300);
        submenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        submenu.setLocationRelativeTo(this);

        // Criando botões do submenu
        JButton btnConsultarConta = new JButton("Consultar Conta");
        JButton btnConsultarFuncionario = new JButton("Consultar Funcionário");
        JButton btnConsultarCliente = new JButton("Consultar Cliente");
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");

        // Configurando painel do submenu
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(btnConsultarConta);
        panel.add(btnConsultarFuncionario);
        panel.add(btnConsultarCliente);
        panel.add(btnVoltar);

        // Ações do submenu
        btnConsultarConta.addActionListener(e -> new view.ConsultarContaView().setVisible(true));
        btnConsultarFuncionario.addActionListener(e -> new view.ConsultarFuncionarioView().setVisible(true));
        btnConsultarCliente.addActionListener(e -> new view.ConsultarClienteView().setVisible(true));

        // Retorna ao menu principal
        btnVoltar.addActionListener(e -> submenu.dispose());

        submenu.add(panel);
        submenu.setVisible(true);
    }

    // Método para abrir o submenu de alteração de dados
    private void abrirSubmenuAlterarDados() {
        JFrame submenu = new JFrame("Submenu - Alterar Dados");
        submenu.setSize(400, 300);
        submenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        submenu.setLocationRelativeTo(this);

        // Criando botões do submenu
        JButton btnAlterarConta = new JButton("Alterar Conta");
        JButton btnAlterarFuncionario = new JButton("Alterar Funcionário");
        JButton btnAlterarCliente = new JButton("Alterar Cliente");
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");

        // Configurando painel do submenu
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(btnAlterarConta);
        panel.add(btnAlterarFuncionario);
        panel.add(btnAlterarCliente);
        panel.add(btnVoltar);

        // Ações do submenu
        btnAlterarConta.addActionListener(e -> new view.AlterarContaView().setVisible(true));
        btnAlterarFuncionario.addActionListener(e -> new view.AlterarFuncionarioView().setVisible(true));
        btnAlterarCliente.addActionListener(e -> new view.AlterarClienteView().setVisible(true));

        // Retorna ao menu principal
        btnVoltar.addActionListener(e -> submenu.dispose());

        submenu.add(panel);
        submenu.setVisible(true);
    }

    // Método para solicitar senha para acessar os submenus
    private void solicitarSenha(String tipo, Runnable acao) {
        String senha = JOptionPane.showInputDialog(this, "Digite a senha:");

        // Verificando se a senha é correta
        if (senha != null && senha.equals("senha123")) {
            acao.run();  // Executa a ação (abre o submenu)
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para executar o menu do funcionário
    public static void main(String[] args) {
        new FuncionarioMenuView().setVisible(true);
    }
}
