package br.com.empresa.model;

/**
 * Representa a entidade Funcionario.
 * Esta classe estende Pessoa, herdando seus atributos e comportamentos.
 */
public class Funcionario extends Pessoa {

    private String matricula;
    private String departamento;

    // Construtor padrão
    public Funcionario() {
        // A chamada super() para o construtor da classe pai (Pessoa)
        // é feita implicitamente pelo Java aqui.
        super();
    }

    // Getters e Setters para os atributos específicos de Funcionario
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Sobrescrevemos o método toString() para incluir as novas informações.
    // Usamos os getters da classe Pessoa (getId(), getNome(), etc.) para acessar os dados.
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() + // Vem da classe Pessoa
                ", nome='" + getNome() + '\'' + // Vem da classe Pessoa
                ", email='" + getEmail() + '\'' + // Vem da classe Pessoa
                ", matricula='" + matricula + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}