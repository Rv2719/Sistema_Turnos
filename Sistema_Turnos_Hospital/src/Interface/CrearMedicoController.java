/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.Medico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Josue
 */
public class CrearMedicoController implements Initializable {

    @FXML
    private TextField especialidadMed;
    @FXML
    private TextField nombreMed;
    @FXML
    private Button btnAtrasMedic;
    @FXML
    private Button btnRegistrarMedico;
    @FXML
    private ComboBox<String> comboSexoMed;
    @FXML
    private TextField cedula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboSexo();
    }    
    
    private void llenarComboSexo(){
        comboSexoMed.getItems().clear();
        comboSexoMed.getItems().addAll("Hombre", "Mujer");
        comboSexoMed.setValue("---");
    }
    
    @FXML
    private void atrasMedic(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
    }

    @FXML
    private void resgistrarMedico(ActionEvent event) throws IOException {
        if (validarVacio()){
            String name= nombreMed.getText();
            String especialidad=especialidadMed.getText();
            int cedu=Integer.parseInt(cedula.getText());
            String gen=comboSexoMed.getValue();
            boolean bol;
            if(gen.equals("Hombre")) bol=true;
            else bol=false;
            sistema_turnos_hospital.Sistema_Turnos_Hospital.medicos.addLast(new Medico(name,cedu, especialidad, bol));
            sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha ingresado los datos correctamente o falta ingresar datos. \nPor favor ingrese todos los datos o revise si ingreso datos correctos.",ButtonType.CLOSE);
            alert.show();
        }
    }

    private boolean validarVacio(){
        boolean especi= !especialidadMed.getText().equals("");
        boolean nom= ! nombreMed.getText().equals("");
        boolean sexo= !comboSexoMed.getValue().equals("---");        
        boolean e;
        try{
            int ed=Integer.parseInt(cedula.getText());
            e=true;
        }catch(NumberFormatException ex){
            e=false;
        }       
        return especi && nom && sexo && e;               
    }
    
    
}
