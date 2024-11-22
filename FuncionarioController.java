package Controller;

import Dao.FuncionarioDAO;
import Model.Funcionario;
import Model.Relatorio;

import java.util.List;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

    // Construtor que inicializa o DAO de Funcionário
    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    // Método para inserir um funcionário no banco de dados
    public void inserirFuncionario(Funcionario funcionario) {
        funcionarioDAO.inserirFuncionario(funcionario);
    }

    // Método para gerar relatório dos funcionários
    public List<Relatorio> gerarRelatorio() {
        return funcionarioDAO.gerarRelatorio();
    }
}
