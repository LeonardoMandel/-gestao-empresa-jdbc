package br.com.empresa.dao;

import br.com.empresa.model.Projeto;
import br.com.empresa.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void save(Projeto projeto) {
        if (new FuncionarioDAO().findById(projeto.getIdFuncionario()) == null) {
            System.err.println("ERRO: Não é possível cadastrar o projeto. Funcionário com ID " + projeto.getIdFuncionario() + " não foi encontrado.");
            return;
        }
        String sql = "INSERT INTO projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, projeto.getNome());
            ps.setString(2, projeto.getDescricao());
            ps.setInt(3, projeto.getIdFuncionario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    projeto.setId(rs.getInt(1));
                }
            }
            System.out.println("CONFIRMAÇÃO: Projeto '" + projeto.getNome() + "' cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao cadastrar projeto. " + e.getMessage());
        }
    }

    public List<Projeto> findAll() {
        String sql = "SELECT * FROM projeto";
        List<Projeto> projetos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setIdFuncionario(rs.getInt("id_funcionario"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao listar projetos. " + e.getMessage());
        }
        return projetos;
    }
}