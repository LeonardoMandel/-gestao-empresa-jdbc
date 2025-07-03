package br.com.empresa.model;

/**
 * Representa a entidade Pessoa.
 * É a classe base para outras, como Funcionario.
 */
public class Pessoa {
    private int id;
    private String nome;
    private String email;

    // Construtor padrão (vazio)
    public Pessoa() {
    }

    // Construtor para facilitar a criação de objetos já com dados
    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters para permitir acesso controlado aos atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // O método toString() é útil para imprimir o objeto de forma legível no console
    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}