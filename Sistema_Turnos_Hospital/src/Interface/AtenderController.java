/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.Medico;
import Clases.Paciente;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Josue
 */
public class AtenderController implements Initializable {

    @FXML
    private ComboBox<Puesto> comboPuestos;
    @FXML
    private TextArea textDiagnostico;
    @FXML
    private TextArea textReceta;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnAtras;
    @FXML
    private TextField textFieldArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCombo();
    }    

    
    public void llenarCombo(){
        try{
            comboPuestos.getItems().clear();
            for(int i =0; i<5;i++){
                Label l1= PrincipalController.labelsP.get(i);
                int id=Integer.parseInt(l1.getText());
                Puesto pue=null;
                for(Puesto p:  sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos){
                    if(p.getNum()==id){
                        pue=p;
                    }
                }
                comboPuestos.getItems().add(pue);
            }
            comboPuestos.setValue(comboPuestos.getItems().get(0));}
        catch(Exception ex){
            comboPuestos.setDisable(true);
            comboPuestos.getItems().clear();
        }
    }
    
    @FXML
    private void siguientePaciente(ActionEvent event) throws IOException {
        if(validar()){
            Puesto puesto=comboPuestos.getValue();
            int turno=0;
            turno=puesto.getPaciente().getTurno();
            puesto.setPaciente(null);
            
            for(Label l: PrincipalController.labelsP){
                if(l.getText().equals(puesto.getNum()+"")){
                    l.setText("...");
                }
            }
            for(Label l: PrincipalController.labelsT){
                if(l.getText().equals(turno)){
                    l.setText("...");
                }
            }
            PrincipalController.contador++;
            sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha ingresado los datos correctamente o falta ingresar datos. \nPor favor ingrese todos los datos o revise si ingreso datos correctos.",ButtonType.CLOSE);
            alert.show();
        }
    }

    @FXML
    private void atrasPaciente(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("Options.fxml")));
    }
    
    private boolean validar(){
        try{
        boolean texto1= !textDiagnostico.getText().equals("");
        boolean texto2= !textReceta.getText().equals("");
        boolean combo= !comboPuestos.getValue().equals("No hay puestos");
        return texto1 && texto2 && combo;
        }catch(NullPointerException ex){
            return false;
        }
    }
    
    @FXML 
    private void llenarPaciente(ActionEvent event){
        try{
            Puesto pu=comboPuestos.getValue();
            Paciente pac=pu.getPaciente();
            textFieldArea.setText(pac.getNombreApellido());}
        catch(Exception e){
            textFieldArea.setText("Problema al enlazar el puesto con el paciente");
        }
    }
}
