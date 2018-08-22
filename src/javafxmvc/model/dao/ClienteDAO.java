/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.model.dao;

import java.util.ArrayList;
import java.util.List;
import javafxmvc.model.domain.Cliente;

/**
 *
 * @author marco
 */
public class ClienteDAO {
    
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "joão", "111.111.111.11", "123456"));
        clientes.add(new Cliente(2, "maria", "222.222.222.22", "456789"));
        clientes.add(new Cliente(3, "beatriz", "333.333.333.33", "987645431"));
        
        return clientes;
    }
    
}
