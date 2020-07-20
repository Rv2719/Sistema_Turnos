/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.Paciente;
import Clases.Sintoma;
import TDA.SimplyLinkedList;
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
public class CrearPacienteController implements Initializable {
    private static int turnos=0;
    @FXML
    private TextField textFieldNombrePaciente;
    @FXML
    private TextField textFieldApellidoPaciente;
    @FXML
    private ComboBox<String> comboSexo;
    @FXML
    private TextField edad;
    @FXML
    private Button btnAtrasPaciente;
    @FXML
    private Button btnRegistrarPaciente;
    @FXML
    private ComboBox<Sintoma> comboSintoma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboSexo();
        llenarComboSintoma();
    }    
    private void llenarComboSexo(){
        comboSexo.getItems().clear();
        comboSexo.getItems().addAll("Hombre", "Mujer");
        comboSexo.setValue("---");
    }
    
    private void llenarComboSintoma(){
        comboSintoma.getItems().clear();
        SimplyLinkedList<Sintoma> sintomas=sistema_turnos_hospital.Sistema_Turnos_Hospital.sintomas;
        for(Sintoma sintoma:sintomas){
            comboSintoma.getItems().add(sintoma);
        } 
        comboSintoma.setValue(sistema_turnos_hospital.Sistema_Turnos_Hospital.sintomas.getFirst());
    }
    @FXML
    private void regresarPaciente(ActionEvent event) throws IOException {
        PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("Options.fxml")));
        
    }

    @FXML
    private void registrarPaciente(ActionEvent event) throws IOException {
        if(estaCompleto()){
            String nombre=textFieldNombrePaciente.getText();
            String apellido=textFieldApellidoPaciente.getText();
            String nombreC=nombre.concat(" "+apellido);
            int e=Integer.parseInt(edad.getText());
            String gen=comboSexo.getValue();
            boolean bol;
            if(gen.equals("Hombre")) bol=true;
            else bol=false;
            Sintoma sint=comboSintoma.getValue();
            int turno=turnos+1;     
            if(turnos>50){
                turnos=1;
                turno=1;
            }
            turnos++;
            Paciente p= new Paciente(nombreC, e,bol,sint, turno);
            System.out.println(turno+"<- turno");
            sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.add(p); 
            PrincipalController.contador++;
            //PrincipalController.update();
            //sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Principal.fxml")));
            PrincipalController.segunda.setRoot(FXMLLoader.load(getClass().getResource("Options.fxml")));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha ingresado los datos correctamente o falta ingresar datos. \nPor favor ingrese todos los datos o revise si ingreso datos correctos.",ButtonType.CLOSE);
            alert.show();
        }

    
    
}
    
    private boolean estaCompleto(){
        boolean nombre = ! textFieldNombrePaciente.getText().equals("");
        boolean apellido = ! textFieldApellidoPaciente.getText().equals("");
        boolean sexo= !comboSexo.getValue().equals("---");
        boolean e;
        try{
            int ed=Integer.parseInt(edad.getText());
            e=true;
        }catch(NumberFormatException ex){
            e=false;
        }
        return nombre && apellido && sexo && e;      
    }
    
}