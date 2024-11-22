package Controller;

import Model.Cliente;
import Dao.ClienteDao;

public class ClienteController {

    private ClienteDao clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDao(); // Inicializa o DAO
    }

    // Método para cadastrar um cliente no banco de dados
    public void cadastrarCliente(String cpf, String nome, String telefone, String endereco) {
        // Cria um cliente com os dados fornecidos
        Cliente cliente = new Cliente(cpf, nome, telefone, endereco);

        // Chama o DAO para inserir o cliente no banco de dados
        clienteDAO.inserirCliente(cliente);
    }

    // Método para consultar o saldo de um cliente
    public double consultarSaldo(String cpf) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf); // Busca o cliente por CPF
        if (cliente != null) {
            return cliente.getSaldo(); // Retorna o saldo do cliente
        }
        System.out.println("Cliente não encontrado.");
        return -1; // Retorna -1 para indicar erro
    }

    // Método para realizar um depósito
    public boolean realizarDeposito(String cpf, double valor) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf); // Busca o cliente por CPF
        if (cliente != null) {
            cliente.setSaldo(cliente.getSaldo() + valor); // Adiciona o valor ao saldo
            clienteDAO.atualizarCliente(cliente); // Atualiza o cliente no banco de dados
            System.out.println("Depósito realizado com sucesso!");
            return true;
        }
        System.out.println("Cliente não encontrado.");
        return false;
    }

    // Método para realizar um saque
    public boolean realizarSaque(String cpf, double valor) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf); // Busca o cliente por CPF
        if (cliente != null) {
            // Verifica se o cliente tem saldo suficiente para o saque
            if (cliente.getSaldo() >= valor) {
                cliente.setSaldo(cliente.getSaldo() - valor); // Deduz o valor do saque
                clienteDAO.atualizarCliente(cliente); // Atualiza o cliente no banco de dados
                System.out.println("Saque realizado com sucesso!");
                return true;
            } else {
                System.out.println("Saldo insuficiente.");
                return false;
            }
        }
        System.out.println("Cliente não encontrado.");
        return false;
    }

    // Método para consultar as informações do cliente
    public String consultarClientePorCpf(String cpf) {
        Cliente cliente = clienteDAO.buscarClientePorCpf(cpf); // Buscar cliente pelo CPF
        if (cliente != null) {
            // Retorna as informações do cliente, agora com o endereço
            return "Nome: " + cliente.getNome() + "\n" +
                    "CPF: " + cliente.getCpf() + "\n" +
                    "Telefone: " + cliente.getTelefone() + "\n" +
                    "Endereço: " + cliente.getEndereco() + "\n" +  // Adicionando o campo endereço
                    "Saldo: " + cliente.getSaldo();
        } else {
            // Caso o cliente não seja encontrado
            return "Cliente não encontrado para o CPF informado.";
        }
    }
}
