/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    
    /**
     * Esse método sempre é chamado na inicialização do controlador. Da forma como está, ele
     * preenche o TableView de Clientes e faz funcionar um Listener (observador) nessa TableView.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherTableViewClientes();

        
        //Esse Listener tem o objetivo de identificar sempre que um cliente for selecionado na Tabela.
        //Quando o cliente for selecionado, seus dados completos serão apresentados ao lado 
        // (código, nome, cpf e telefone) através do método selecionarItemTvClientes
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTvClientes(newValue));

        /**
         * IMPLEMENTAÇÃO DO LISTENER SEM O USO DE LAMBDA ->
         * tvClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {
         *
         * @Override public void changed(ObservableValue<? extends Cliente>
         * observable, Cliente oldValue, Cliente newValue) {
         * selecionarItemTvClientes(newValue); }
         *
         * });*
         */
    }

    /**
     * Preenche a TableView na tela
     * 
     * 
     */
    public void preencherTableViewClientes() {
        
        //Cria um vínculo entre as colunas na tela e os atributos da classe Cliente
        tvColunaClientesNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tvColunaClientesCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        //Traz os clientes cadastrados no BD
        listClientes = clienteDAO.listar();
        
        //vincla o observador com a lista trazida do BD; sempre que um novo cliente for cadastrado
        //ele também aparecerá na tela
        observableListClientes = FXCollections.observableArrayList(listClientes);
        tvClientes.setItems(observableListClientes);

    }

    /**
     * Método chamado quando um item é selecionado na TableView; preenche os labels na parte direita da tela
     * @param cliente 
     */
    private void selecionarItemTvClientes(Cliente cliente) {
        if (cliente != null) {
            lbCodigo.setText(String.valueOf(cliente.getCodCliente()));
            lbNome.setText(cliente.getNome());
            lbCpf.setText(cliente.getCpf());
            lbTelefone.setText(cliente.getTelefone());
        } else {
            lbCodigo.setText("");
            lbNome.setText("");
            lbCpf.setText("");
            lbTelefone.setText("");
        }

    }

    @FXML
    public void handleInserirCliente() throws IOException {
        Cliente cliente = new Cliente();
        boolean btConfirmarClicked = abrirFormularioCadastroCliente(cliente);
        if (btConfirmarClicked) {
            clienteDAO.inserir(cliente);
            preencherTableViewClientes();
        }
    }

    @FXML
    public void handleAlterarCliente() throws IOException {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        boolean btConfirmarClicked = abrirFormularioCadastroCliente(cliente);
        if (cliente != null) {
            if (btConfirmarClicked) {
                clienteDAO.alterar(cliente);
                preencherTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela");
            alert.show();
        }

    }

    @FXML
    public void handleRemoverCliente() {
        Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            preencherTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na tabela");
            alert.show();
        }
    }

    private boolean abrirFormularioCadastroCliente(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroClienteFormularioController.class.getResource("/javafxmvc/view/FXMLCadastroClienteFormulario.fxml"));
        AnchorPane page = loader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        stage.setScene(scene);
        
        FXMLCadastroClienteFormularioController controller = loader.getController();
        controller.setDialongStage(stage);
        controller.setCliente(cliente);
        
        stage.showAndWait();
        
        return controller.isBtConfirmarClicked();
    }
}
