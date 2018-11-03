/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxmvc.model.domain.Cliente;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class FXMLCadastroClienteFormularioController implements Initializable {
 
    @FXML
    TextField tfNome;
    @FXML
    TextField tfCpf;
    @FXML
    TextField tfTelefone;
    @FXML
    Button btConfirmar;
    @FXML
    Button btCancelar;

    private Cliente cliente;
    private boolean btConfirmarClicked = false;
    private Stage dialongStage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void handleConfirmar() {

        cliente.setNome(tfNome.getText());
        cliente.setCpf(tfCpf.getText());
        cliente.setTelefone(tfTelefone.getText());
        
        btConfirmarClicked = true;
        dialongStage.close();
    }
    @FXML
    public void handleCancelar() {
        dialongStage.close();
    }
    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        
        //preenche os dados do cliente (em caso do cliente desejar fazer uma alteração)
        //tfCodigo.setText(String.valueOf(cliente.getCodCliente()));
        tfNome.setText(cliente.getNome());
        tfCpf.setText(cliente.getCpf());
        tfTelefone.setText(cliente.getTelefone());
    }

    /**
     * @return the btConfirmarClicked
     */
    public boolean isBtConfirmarClicked() {
        return btConfirmarClicked;
    }

    /**
     * @param btConfirmarClicked the btConfirmarClicked to set
     */
    public void setBtConfirmarClicked(boolean btConfirmarClicked) {
        this.btConfirmarClicked = btConfirmarClicked;
    }

    /**
     * @return the dialongStage
     */
    public Stage getDialongStage() {
        return dialongStage;
    }

    /**
     * @param dialongStage the dialongStage to set
     */
    public void setDialongStage(Stage dialongStage) {
        this.dialongStage = dialongStage;
    }
}
