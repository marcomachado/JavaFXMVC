/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmvc.model.dao.ClienteDAO;
import javafxmvc.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class FXMLCadastroClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tvClientes;
    @FXML
    private TableColumn<Cliente, String> tvColunaClientesNome;
    @FXML
    private TableColumn<Cliente, String> tvColunaClientesCpf;
    @FXML
    private Button btInserir;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btRemover;
    @FXML
    private Label lbCodigo;
    @FXML
    private Label lbNome;
    @FXML
    private Label lbCpf;
    @FXML
    private Label lbTelefone;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    private final ClienteDAO clienteDAO = new ClienteDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTableViewClientes();
        
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTvClientes(newValue));
       
        /** IMPLEMENTAÇÃO DO LISTENER SEM O USO DE LAMBDA ->
        tvClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
            @Override
            public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
                selecionarItemTvClientes(newValue);
            }
            
        });**/
    }
    
    public void preencherTableViewClientes() {
        
        tvColunaClientesNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tvColunaClientesCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = clienteDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listClientes);
        tvClientes.setItems(observableListClientes);
        
    }

    private void selecionarItemTvClientes(Cliente cliente) {
        lbCodigo.setText(String.valueOf(cliente.getCodCliente()));
        lbNome.setText(cliente.getNome());
        lbCpf.setText(cliente.getCpf());
        lbTelefone.setText(cliente.getTelefone());
    }
    
}