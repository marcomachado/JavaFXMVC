/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafxmvc.model.domain.Cliente;

/**
 *
 * @author marco
 */
public class ClienteDAO {
    
    Connection connection;
    
    public ClienteDAO () {
        connection = new ConnectionFactory().conectar();
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, cpf, telefone) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: "+ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome=?, cpf=?, telefone=? WHERE codCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getCodCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: "+ex.getMessage());
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM clientes WHERE codCliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getCodCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("ERRO: "+ex.getMessage());
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(resultado.getInt("codCliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setTelefone(resultado.getString("telefone"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("ERRO: "+ex.getMessage());
        }
        return retorno;
    }
}
