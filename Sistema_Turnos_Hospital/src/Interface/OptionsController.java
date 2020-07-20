/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Josue
 */
public class OptionsController implements Initializable {

    @FXML
    private Button btnCrearPuesto;
    @FXML
    private Button btnRegistrarPaciente;
    @FXML
    private Button btnCrearMedico;
    @FXML
    private Button btnAtender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void crearPuesto(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("CrearPuesto.fxml")));
    }

    @FXML
    private void registrarPaciente(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("CrearPaciente.fxml")));
    }

    @FXML
    private void crearMedico(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("CrearMedico.fxml")));
    }

    @FXML
    private void atenderPaciente(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("Atender.fxml")));
    }
    
}
