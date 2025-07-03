package br.com.empresa.dao;

import br.com.empresa.model.Pessoa;
import br.com.empresa.util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void save(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pessoa.setId(rs.getInt(1));
                }
            }
            System.out.println("CONFIRMAÇÃO: Pessoa '" + pessoa.getNome() + "' cadastrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao cadastrar pessoa. " + e.getMessage());
        }
    }

    public List<Pessoa> findAll() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao listar pessoas. " + e.getMessage());
        }
        return pessoas;
    }

    public Pessoa findById(int id) {
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa pessoa = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    pessoa = new Pessoa();
                    pessoa.setId(rs.getInt("id"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("ERRO: Falha ao buscar pessoa. " + e.getMessage());
        }
        return pessoa;
    }
}