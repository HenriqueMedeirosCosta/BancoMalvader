package Controller;

import Dao.ContaDao;
import Model.Conta;

public class ContaController {

    private ContaDao contaDao;

    // Construtor
    public ContaController() {
        this.contaDao = new ContaDao(); // Inicializa a DAO
    }

    // Consultar saldo apenas com CPF
    public double consultarSaldo(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.err.println("CPF inválido.");
            return -1;
        }
        Conta conta = contaDao.buscarContaPorCpf(cpf); // Busca a conta pelo CPF
        if (conta != null) {
            return contaDao.consultarSaldo(conta.getCpf(), conta.getNumero()); // Consulta saldo com o número da conta
        }
        return -1; // Retorna -1 se não encontrar a conta
    }

    // Consultar saldo por CPF e número da conta
    public double consultarSaldo(String cpf, String numeroConta) {
        if (cpf == null || numeroConta == null || cpf.isEmpty() || numeroConta.isEmpty()) {
            System.err.println("CPF ou número da conta inválido.");
            return -1;
        }
        return contaDao.consultarSaldo(cpf, numeroConta); // Chama o método da DAO
    }

    // Buscar conta por CPF
    public Conta buscarConta(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.err.println("CPF inválido.");
            return null;
        }
        return contaDao.buscarContaPorCpf(cpf); // Chama a DAO para buscar a conta pelo CPF
    }

    // Realizar depósito
    public void realizarDeposito(String cpf, String numeroConta, double valor) {
        if (valor <= 0) {
            System.err.println("O valor do depósito deve ser maior que zero.");
            return;
        }
        Conta conta = buscarConta(cpf); // Busca a conta pelo CPF
        if (conta != null) {
            contaDao.realizarDeposito(numeroConta, valor); // Chama o método da DAO
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso!");
        } else {
            System.err.println("Conta não encontrada. Verifique os dados.");
        }
    }

    // Realizar saque
    public void realizarSaque(String cpf, String numeroConta, double valor) {
        if (valor <= 0) {
            System.err.println("O valor do saque deve ser maior que zero.");
            return;
        }
        Conta conta = buscarConta(cpf); // Busca a conta pelo CPF
        if (conta != null) {
            double saldoAtual = contaDao.consultarSaldo(cpf, numeroConta); // Consulta o saldo atual
            if (saldoAtual >= valor) {
                contaDao.realizarSaque(numeroConta, valor); // Realiza o saque
                System.out.println("Saque de R$ " + valor + " realizado com sucesso!");
            } else {
                System.err.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.err.println("Conta não encontrada. Verifique os dados.");
        }
    }

    // Atualizar dados de uma conta
    public void atualizarConta(String numeroConta, String tipoConta, double limite, String dataVencimento, String cpf) {
        if (numeroConta == null || numeroConta.isEmpty() || cpf == null || cpf.isEmpty()) {
            System.err.println("CPF ou número da conta inválido.");
            return;
        }
        Conta conta = buscarConta(cpf); // Busca a conta pelo CPF
        if (conta != null) {
            conta.setTipoConta(tipoConta); // Atualiza o tipo de conta
            conta.setLimite(limite); // Atualiza o limite
            conta.setDataVencimento(dataVencimento); // Atualiza a data de vencimento
            contaDao.atualizarConta(conta); // Chama o método da DAO
            System.out.println("Conta atualizada com sucesso!");
        } else {
            System.err.println("Conta não encontrada. Verifique os dados.");
        }
    }

    // Consultar conta por CPF
    public Conta consultarContaPorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            System.err.println("CPF inválido.");
            return null;
        }
        return contaDao.buscarContaPorCpf(cpf); // Chama a DAO para buscar a conta pelo CPF
    }
}
