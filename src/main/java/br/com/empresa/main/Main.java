package br.com.empresa.main;

import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.dao.PessoaDAO;
import br.com.empresa.dao.ProjetoDAO;
import br.com.empresa.model.Funcionario;
import br.com.empresa.model.Pessoa;
import br.com.empresa.model.Projeto;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        System.out.println("APLICAÇÃO INICIADA. Use o menu para interagir.");
        System.out.println("Lembre-se de preparar seu banco de dados com o script 'empresa.sql' antes de começar.");

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- Cadastrar Pessoa ---");
                        System.out.print("Digite o nome da nova pessoa: ");
                        String nomePessoa = scanner.nextLine();
                        System.out.print("Digite o email da nova pessoa: ");
                        String emailPessoa = scanner.nextLine();
                        pessoaDAO.save(new Pessoa(nomePessoa, emailPessoa));
                        break;
                    case 2:
                        System.out.println("\n--- Cadastrar Funcionário ---");
                        System.out.println("Pessoas disponíveis para se tornarem funcionários:");
                        pessoaDAO.findAll().forEach(System.out::println);
                        System.out.print("\nDigite o ID da Pessoa para promover a Funcionário: ");
                        int idPessoaFunc = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite a matrícula (ex: F007): ");
                        String matricula = scanner.nextLine();
                        System.out.print("Digite o departamento: ");
                        String depto = scanner.nextLine();
                        Funcionario novoFunc = new Funcionario();
                        novoFunc.setId(idPessoaFunc);
                        novoFunc.setMatricula(matricula);
                        novoFunc.setDepartamento(depto);
                        funcionarioDAO.save(novoFunc);
                        break;
                    case 3:
                        System.out.println("\n--- Cadastrar Projeto ---");
                        System.out.println("Funcionários disponíveis para liderar projetos:");
                        funcionarioDAO.findAll().forEach(System.out::println);
                        System.out.print("\nDigite o ID do Funcionário responsável: ");
                        int idFuncResp = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o nome do projeto: ");
                        String nomeProj = scanner.nextLine();
                        System.out.print("Digite a descrição do projeto: ");
                        String descProj = scanner.nextLine();
                        Projeto novoProj = new Projeto();
                        novoProj.setNome(nomeProj);
                        novoProj.setDescricao(descProj);
                        novoProj.setIdFuncionario(idFuncResp);
                        projetoDAO.save(novoProj);
                        break;
                    case 4:
                        System.out.println("\n--- Listar Pessoas ---");
                        pessoaDAO.findAll().forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("\n--- Listar Funcionários ---");
                        funcionarioDAO.findAll().forEach(System.out::println);
                        break;
                    case 6:
                        System.out.println("\n--- Listar Projetos ---");
                        projetoDAO.findAll().forEach(System.out::println);
                        break;
                    case 7:
                        System.out.println("\n--- Excluir Funcionário ---");
                        System.out.println("Funcionários que podem ser excluídos:");
                        funcionarioDAO.findAll().forEach(System.out::println);
                        System.out.print("\nDigite o ID do funcionário a ser EXCLUÍDO: ");
                        int idFuncDel = scanner.nextInt();
                        scanner.nextLine();
                        funcionarioDAO.deleteById(idFuncDel);
                        break;
                    case 0:
                        System.out.println("Saindo da aplicação... Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("ERRO: Por favor, digite um número válido para a opção.");
                scanner.nextLine();
                opcao = -1;
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n========= MENU DE GESTÃO =========");
        System.out.println("1. Cadastrar nova Pessoa");
        System.out.println("2. Cadastrar novo Funcionário");
        System.out.println("3. Cadastrar novo Projeto");
        System.out.println("------------------------------------");
        System.out.println("4. Listar todas as Pessoas");
        System.out.println("5. Listar todos os Funcionários");
        System.out.println("6. Listar todos os Projetos");
        System.out.println("------------------------------------");
        System.out.println("7. Excluir Funcionário");
        System.out.println("------------------------------------");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}