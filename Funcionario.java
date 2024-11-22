package Model;

public class Funcionario {
    private int codigo; // Novo campo 'codigo'
    private String nome;
    private String cpf;
    private String cargo;
    private String telefone;
    private String endereco;
    private String dataNascimento; // Novo campo 'dataNascimento'
    private String senha;  // Campo para a senha

    // Construtor atualizado para aceitar o 'codigo', 'dataNascimento' e 'senha'
    public Funcionario(String nome, String cpf, String cargo, String telefone, String endereco, String dataNascimento, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento; // Inicializa a data de nascimento
        this.senha = senha;  // Inicializa a senha
    }

    // Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataNascimento() {
        return dataNascimento; // Getter para dataNascimento
    }

    public String getSenha() {
        return senha;  // Getter para senha
    }

    // Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento; // Setter para dataNascimento
    }

    public void setSenha(String senha) {
        this.senha = senha;  // Setter para senha
    }
}
