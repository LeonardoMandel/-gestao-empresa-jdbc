package br.com.empresa.dao;

import br.com.empresa.model.Funcionario;
import br.com.empresa.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void save(Funcionario funcionario) {
        if (new PessoaDAO().findById(funcionario.getId()) == null) {
            System.err.println("ERRO: Não é possível cadastrar o funcionário. Pessoa com ID " + funcionario.getId() + " não foi encontrada.");
            return;
        }
        String sql = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, funcionario.getId());
            ps.setString(2, funcionario.getMatricula());
            ps.setString(3, funcionario.getDepartamento());
            ps.executeUpdate();
            System.out.println("CONFIRMAÇÃO: Funcionário com matrícula '" + funcionario.getMatricula() + "' cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao cadastrar funcionário. " + e.getMessage());
        }
    }

    public void deleteById(int id) {
        String checkSql = "SELECT COUNT(*) FROM projeto WHERE id_funcionario = ?";
        try (Connection conn = ConnectionFactory.getConnection()) {
            try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {
                psCheck.setInt(1, id);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        System.err.println("ERRO: Não é possível excluir o funcionário com ID " + id + ". Ele está vinculado a " + rs.getInt(1) + " projeto(s).");
                        return;
                    }
                }
            }
            String deleteSql = "DELETE FROM funcionario WHERE id = ?";
            try (PreparedStatement psDelete = conn.prepareStatement(deleteSql)) {
                psDelete.setInt(1, id);
                int rowsAffected = psDelete.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("CONFIRMAÇÃO: Funcionário com ID " + id + " excluído com sucesso!");
                } else {
                    System.err.println("AVISO: Nenhum funcionário com ID " + id + " foi encontrado para exclusão.");
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao excluir funcionário. " + e.getMessage());
        }
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT p.id, p.nome, p.email, f.matricula, f.departamento " +
                "FROM pessoa p JOIN funcionario f ON p.id = f.id";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setMatricula(rs.getString("matricula"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao listar funcionários. " + e.getMessage());
        }
        return funcionarios;
    }

    public Funcionario findById(int id) {
        String sql = "SELECT p.id, p.nome, p.email, f.matricula, f.departamento " +
                "FROM pessoa p JOIN funcionario f ON p.id = f.id WHERE f.id = ?";
        Funcionario funcionario = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setMatricula(rs.getString("matricula"));
                    funcionario.setDepartamento(rs.getString("departamento"));
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao buscar funcionário. " + e.getMessage());
        }
        return funcionario;
    }
}