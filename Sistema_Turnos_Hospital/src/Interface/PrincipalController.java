/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Clases.Paciente;
import Clases.Puesto;
import Clases.Video;
import TDA.SimplyLinkedList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josue
 */
public class PrincipalController implements Initializable {
    public static int contador=1;
    
    public static SimplyLinkedList<Label> labelsT=new SimplyLinkedList<>();
    public static SimplyLinkedList<Label> labelsP=new SimplyLinkedList<>();
    
    @FXML
    private VBox VBoxTurno;
    @FXML
    private VBox VBoxPuesto;
    @FXML
    private Label lblT1;
    @FXML
    private Label lblT2;
    @FXML
    private Label lblT3;
    @FXML
    private Label lblT4;
    @FXML
    private Label lblT5;
    @FXML
    private Label lblP1;
    @FXML
    private Label lblP2;
    @FXML
    private Label lblP3;
    @FXML
    private Label lblP4;
    @FXML
    private Label lblP5;
    @FXML
    private MediaView pantallaVideo;
    
    public static Stage segundaVentana;
    public static Scene segunda;
    public Iterator<Video> direccionesVideo=sistema_turnos_hospital.Sistema_Turnos_Hospital.videos.iterator();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            segundaVentana = new Stage();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Options.fxml"));
            Parent root = loader1.load();
            segunda = new Scene(root);
            //rController = loader1.getController();

            
            //System.out.println(rController);
            //rController.setPrincipal(this);
            segundaVentana.setTitle("Opciones del programa");
            segundaVentana.setScene(segunda);
            segundaVentana.show();

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(labelsP.isEmpty()){
            llenarListaP();
            llenarListaT();
        }
        
        reproducirVideos(pantallaVideo, direccionesVideo);
        
        
        if(contador%2==0){
            update();
        }
    }
    
    private void reproducirVideos(final MediaView pantalla, final Iterator<Video> videos){
        if (direccionesVideo.hasNext()) {
            Video v=direccionesVideo.next();
            File f=new File(v.getDireccion());
            Media m=new Media(f.toURI().toString());
            MediaPlayer mp = new MediaPlayer(m);
            mp.setAutoPlay(true);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    reproducirVideos(pantalla, (Iterator<Video>) videos);
                }
            });
            pantalla.setMediaPlayer(mp);
        }
    }

    public Label getLblP4() {
        return lblP4;
    }

    public void setLblP4(Label lblP4) {
        this.lblP4 = lblP4;
    }
    
    public void update(){       
        Label vacio;
        for(Label l: labelsP){
            String p=l.getText();
            if(p.equals("...")){
                vacio=l;
                recorrerPuestos(vacio);
                break;
            }
        }
        //System.out.println(labelsP);
        contador++;
        
    }
    
    private void recorrerPuestos(Label l){
        int index=labelsP.indexOf(l);
        System.out.println("Caso tomado:" +index);
        switch (index){
            case 0:
                /*Paciente paciente=sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.poll();
                for(int i=0;i< sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.size();i++){
                        Puesto p=sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i);
                        if(p.getPaciente()==null){
                            sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i).setPaciente(paciente);
                            System.out.println(p.getNum());
                            lblP1.setText(p.getNum()+"");
                            lblT1.setText(paciente.getTurno()+"");
                            labelsP.get(0).setText(p.getNum()+"");
                            labelsT.get(0).setText(paciente.getTurno()+"");
                            break;
                        }
                }*/
                addNuevoPaciente(0);
                break;
            case 1:
                /*Paciente pac=sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.poll();
                Puesto p1=null;
                for(int i=0;i< sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.size();i++){
                        Puesto p=sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i);
                        if(p.getPaciente()==null){
                            p1=p;
                            sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i).setPaciente(pac);
                            System.out.println(p.getNum());
                            
                            labelsP.get(1).setText(p.getNum()+"");
                            labelsT.get(1).setText(pac.getTurno()+"");
                            break;
                        }
                }*/
                //estas modificaciones las hace en la lista para no repetir puestos y no perderlos
                labelsP.get(1).setText(labelsP.get(0).getText());
                labelsT.get(1).setText(labelsT.get(0).getText()); 
                System.out.println(labelsP.get(0).getText()+" escrito en la segunda fila");
                System.out.println(labelsT.get(0).getText()+" escrito en la segunda fila");
                lblP2.setText(labelsP.get(0).getText());//estas modificaciones provocan cambios 
                lblT2.setText(labelsT.get(0).getText());//en los labels que se muestran en la interfaz.
                /*lblP1.setText(p1.getNum()+"");
                lblT1.setText(pac.getTurno()+"");*/
                addNuevoPaciente(1);
                break;
            case 2:
                
                labelsP.get(2).setText(labelsP.get(1).getText());
                labelsT.get(2).setText(labelsT.get(1).getText());
                labelsP.get(1).setText(labelsP.get(0).getText());
                labelsT.get(1).setText(labelsT.get(0).getText());      
                System.out.println(labelsP.get(1).getText());
                System.out.println(labelsP.get(0).getText()+" deberia ser 2"+labelsP.get(0));
                lblP3.setText(labelsP.get(1).getText());
                lblT3.setText(labelsT.get(1).getText());
                lblP2.setText(labelsP.get(0).getText());
                lblT2.setText(labelsT.get(0).getText());
                /*lblP1.setText(p2.getNum()+"");
                lblT1.setText(pac2.getTurno()+"");*/
                addNuevoPaciente(2);
                break;
            case 3:
                /*Paciente pac3=sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.poll();
                Puesto p3=null;
                for(int i=0;i< sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.size();i++){
                        Puesto p=sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i);
                        if(p.getPaciente()==null){
                            p3=p;
                            sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i).setPaciente(pac3);
                            System.out.println(p.getNum());
                            
                            labelsP.get(3).setText(p.getNum()+"");
                            labelsT.get(3).setText(pac3.getTurno()+"");
                            break;
                        }
                }*/
                labelsP.get(3).setText(labelsP.get(2).getText());
                labelsT.get(3).setText(labelsT.get(2).getText());
                labelsP.get(2).setText(labelsP.get(1).getText());
                labelsT.get(2).setText(labelsT.get(1).getText());  
                labelsP.get(1).setText(labelsP.get(0).getText());
                labelsT.get(1).setText(labelsT.get(0).getText());
                lblP4.setText(labelsP.get(2).getText());
                lblT4.setText(labelsT.get(2).getText());
                lblP3.setText(labelsP.get(1).getText());
                lblT3.setText(labelsT.get(1).getText());
                lblP2.setText(labelsP.get(0).getText());
                lblT2.setText(labelsT.get(0).getText());
                /*lblP1.setText(p3.getNum()+"");
                lblT1.setText(pac3.getTurno()+"");*/
                addNuevoPaciente(3);
                break;
            case 4:
                /*Paciente pac4=sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.poll();
                Puesto p4=null;
                for(int i=0;i< sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.size();i++){
                        Puesto p=sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i);
                        if(p.getPaciente()==null){
                            p4=p;
                            sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i).setPaciente(pac4);
                            System.out.println(p.getNum());
                            
                            labelsP.get(3).setText(p.getNum()+"");
                            labelsT.get(3).setText(pac4.getTurno()+"");
                            break;
                        }
                }*/
                labelsP.get(4).setText(labelsP.get(3).getText());
                labelsT.get(4).setText(labelsT.get(3).getText());
                labelsP.get(3).setText(labelsP.get(2).getText());
                labelsT.get(3).setText(labelsT.get(2).getText());
                labelsP.get(2).setText(labelsP.get(1).getText());
                labelsT.get(2).setText(labelsT.get(1).getText());  
                labelsP.get(1).setText(labelsP.get(0).getText());
                labelsT.get(1).setText(labelsT.get(0).getText());
                
                lblP5.setText(labelsP.get(3).getText());
                lblT5.setText(labelsT.get(3).getText());
                lblP4.setText(labelsP.get(2).getText());
                lblT4.setText(labelsT.get(2).getText());
                lblP3.setText(labelsP.get(1).getText());
                lblT3.setText(labelsT.get(1).getText());
                lblP2.setText(labelsP.get(0).getText());
                lblT2.setText(labelsT.get(0).getText());
                /*lblP1.setText(p4.getNum()+"");
                lblT1.setText(pac4.getTurno()+"");*/
                addNuevoPaciente(4);
                break;
        }
    }
    
    private void addNuevoPaciente(int indice){
        Paciente paciente=sistema_turnos_hospital.Sistema_Turnos_Hospital.pacientes.poll();
        for(int i=0;i< sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.size();i++){
                Puesto p=sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i);
                if(p.getPaciente()==null){
                    sistema_turnos_hospital.Sistema_Turnos_Hospital.puestos.get(i).setPaciente(paciente);
                    System.out.println(p.getNum()+" <-puesto");
                    lblP1.setText(p.getNum()+"");
                    lblT1.setText(paciente.getTurno()+"");
                    labelsP.get(indice).setText(p.getNum()+"");
                    labelsT.get(indice).setText(paciente.getTurno()+"");
                    break;
                }
        }
    }
    
    public void llenarListaT(){
        labelsT.addLast(lblT1);
        labelsT.addLast(lblT2);
        labelsT.addLast(lblT3);
        labelsT.addLast(lblT4);
        labelsT.addLast(lblT5);
    }
    
    public void llenarListaP(){
        labelsP.addLast(lblP1);
        labelsP.addLast(lblP2);
        labelsP.addLast(lblP3);
        labelsP.addLast(lblP4);
        labelsP.addLast(lblP5);
        
    }
    public Label getLblT1() {
        return lblT1;
    }

    public void setLblT1(Label lblT1) {
        this.lblT1 = lblT1;
    }

    public Label getLblT2() {
        return lblT2;
    }

    public void setLblT2(Label lblT2) {
        this.lblT2 = lblT2;
    }

    public Label getLblT3() {
        return lblT3;
    }

    public void setLblT3(Label lblT3) {
        this.lblT3 = lblT3;
    }

    public Label getLblT4() {
        return lblT4;
    }

    public void setLblT4(Label lblT4) {
        this.lblT4 = lblT4;
    }

    public Label getLblT5() {
        return lblT5;
    }

    public void setLblT5(Label lblT5) {
        this.lblT5 = lblT5;
    }

    public Label getLblP1() {
        return lblP1;
    }

    public void setLblP1(Label lblP1) {
        this.lblP1 = lblP1;
    }

    public Label getLblP2() {
        return lblP2;
    }

    public void setLblP2(Label lblP2) {
        this.lblP2 = lblP2;
    }

    public Label getLblP3() {
        return lblP3;
    }

    public void setLblP3(Label lblP3) {
        this.lblP3 = lblP3;
    }

    public Label getLblP5() {
        return lblP5;
    }

    public void setLblP5(Label lblP5) {
        this.lblP5 = lblP5;
    }

    
    /*
    private void crearPuesto(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("CrearPuesto.fxml")));
    }

    private void registrarPaciente(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("CrearPaciente.fxml")));
    }

    private void crearMedico(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("CrearMedico.fxml")));
    }

    private void atenderPaciente(ActionEvent event) throws IOException {
        sistema_turnos_hospital.Sistema_Turnos_Hospital.s.setRoot(FXMLLoader.load(getClass().getResource("Atender.fxml")));
    }


    */
    
    
}
