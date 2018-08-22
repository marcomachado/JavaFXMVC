
package javafxmvc.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class FXMLVBoxMainController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuItemCadastrosCliente;
    @FXML
    private MenuItem menuItemCadastrosProduto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleMenuItemCadastrosClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafxmvc/view/FXMLCadastroCliente.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
