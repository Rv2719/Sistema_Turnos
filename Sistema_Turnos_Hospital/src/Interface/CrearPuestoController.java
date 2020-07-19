/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.Medico;
import Clases.Puesto;
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
public class CrearPuestoController implements Initializable {

    @FXML
    private TextField idPuesto;
    @FXML
    private ComboBox<Medico> comboDoct;
    @FXML
    private Button btnAtrasPues;
    @FXML
    private Button btnCrearPuesto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCombo();
    }    
    
    private void llenarCombo(){
        comboDoct.getItems().clear();
        for(Medico doc: sistema_turnos_hospital.Sistema_Turnos_Hospital.medicos){
            comboDoct.getItems().add(doc);
        }
        comboDoct.setValue(sistema_turnos_hospital.Sistema_Turnos_Hospital.medicos.getFirst());
    } 
    
    private boolean validarVacio(){
        boolean e;
        try{
            int ed=Integer.parseInt(idPuesto.getText());
            for(Puesto p:sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos){
                if(p.getNum()==ed){
                    return false;
                }
            }
            e=true;
        }catch(NumberFormatException ex){
            e=false;
        }
        return e;
    }

    @FXML
    private void atrasPues(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
    }

    @FXML
    private void crearPues(ActionEvent event) throws IOException {
        if(validarVacio()){
            int id=Integer.parseInt(idPuesto.getText());
            Medico doc=comboDoct.getValue();
            int cedula=doc.getCedula();
            sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.addLast(new Puesto(id, cedula));
            sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha ingresado los datos correctamente o falta ingresar datos. \nPor favor ingrese todos los datos o revise si ingreso datos correctos.",ButtonType.CLOSE);
            alert.show();
        }
    }

    
}
