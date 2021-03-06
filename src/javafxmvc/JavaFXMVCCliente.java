/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmvc;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marco
 */
public class JavaFXMVCCliente extends Application {
    private Stage dialongStage;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ListaClientesFXML.fxml"));
        
        Scene scenePrincipal = new Scene(root);
        
        stage.setScene(scenePrincipal);
        stage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
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
