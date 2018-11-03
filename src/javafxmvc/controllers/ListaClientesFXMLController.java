/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxmvc.model.dao.ClienteDAO;
import javafxmvc.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class ListaClientesFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableView<Cliente> clientesTable;

    @FXML
    private TableColumn<Cliente, Boolean> tableColumnChamada;

    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnCpf;

    @FXML
    private Button mostrar;
    
    @FXML
    private Hyperlink mostrarLink;
       
    private StringProperty usuarioLogado = new SimpleStringProperty();
    
    Cliente cliente;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    private final ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private void showSelected() {
        cliente = new Cliente();
        System.out.println(cliente);
        for (Cliente p : clientesTable.getItems()) {
            System.out.printf("%s (Faltou: %s)%n", p.getNome(), !p.isChamada()? "Sim" : "Não");
        }
        System.out.println();
        usuarioLogado.set("marco");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new Cliente();
        
        clientesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        tableColumnChamada.setCellValueFactory(new PropertyValueFactory<Cliente, Boolean>("chamada"));
        tableColumnChamada.setCellFactory(CheckBoxTableCell.forTableColumn(tableColumnChamada));

        preencherTableViewClientes();

//table display preference - should not affect this exercise/problem
        clientesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
        mostrar.setDisable(true);
        mostrar.disableProperty().bind(Bindings.isEmpty(usuarioLogado));
    }

    public void preencherTableViewClientes() {

        //Cria um vínculo entre as colunas na tela e os atributos da classe Cliente
        /*col_nome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        col_cpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
        col_deletar.setCellFactory(CheckBoxTableCell.forTableColumn(col_deletar));*/
        //Traz os clientes cadastrados no BD
        listClientes = clienteDAO.listar();

        //vincla o observador com a lista trazida do BD; sempre que um novo cliente for cadastrado
        //ele também aparecerá na tela
        observableListClientes = FXCollections.observableArrayList(listClientes);
        clientesTable.setItems(observableListClientes);

    }

}
