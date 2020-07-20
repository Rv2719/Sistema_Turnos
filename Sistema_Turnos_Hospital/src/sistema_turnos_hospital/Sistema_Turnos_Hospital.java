/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_turnos_hospital;

import Clases.Medico;
import Clases.Paciente;
import Clases.Puesto;
import Clases.Sintoma;
import Clases.Video;
import TDA.CircularLinkedList;
import TDA.SimplyLinkedList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Josue
 */
public class Sistema_Turnos_Hospital extends Application {
    public static Scene s;
    public static Stage stage;
    public static SimplyLinkedList<Sintoma> sintomas;
    public static SimplyLinkedList<Puesto> puestos;    
    public static SimplyLinkedList<Medico> medicos;
    public static PriorityQueue<Paciente> pacientes=new PriorityQueue<>((Paciente p1, Paciente p2)->p1.getSintoma().getPrioridad()-p2.getSintoma().getPrioridad());
    public static SimplyLinkedList<Integer> turnos=new SimplyLinkedList<>();
    public static CircularLinkedList<Video> videos=new CircularLinkedList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        medicos=leerMedicos("Archivos/doctores.txt");
        puestos=leerPuestos("Archivos/puestos.txt");       
        sintomas=generarSintomas("Archivos/sintomas.txt");
        videos=leerVideos("Archivos/videos.txt");
        System.out.println(videos);
        launch(args);  
    }
    

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("/Interface/Principal.fxml"));
        s= new Scene (root);
        stage.setTitle("Sistema de Turnos");
        stage.setScene(s);
        stage.show();          
    }
    
    
    private static SimplyLinkedList<Sintoma> generarSintomas(String direccion) throws IOException{
        SimplyLinkedList<Sintoma> li= new SimplyLinkedList<>();
        FileReader f = new FileReader(direccion);// la ubicacion del archivo debe ser en la carpeta raiz del proyecto, no dentro del src, prefentemente en la carpeta raiz hacer una carpeta llamada config y ahi meter todos los archivos que se necesiten leer.
        BufferedReader b = new BufferedReader(f);
        String cadena;
           while((cadena=b.readLine())!=null){
               String[] linea=cadena.split("\\|");//es necesario agregarle el doble \\ para que haga el split adecuado
               Integer prioridad=Integer.parseInt(linea[1]);
               String nombre=linea[0];
               li.addLast(new Sintoma(nombre, prioridad));
           }
        b.close();
        return li;
    }
    
    public static SimplyLinkedList<Puesto> leerPuestos(String direccion) throws IOException{
        SimplyLinkedList<Puesto> li= new SimplyLinkedList<>();
        FileReader f = new FileReader(direccion);// la ubicacion del archivo debe ser en la carpeta raiz del proyecto, no dentro del src, prefentemente en la carpeta raiz hacer una carpeta llamada config y ahi meter todos los archivos que se necesiten leer.
        BufferedReader b = new BufferedReader(f);
        String cadena;
           while((cadena=b.readLine())!=null){
               String[] linea=cadena.split("\\|");//es necesario agregarle el doble \\ para que haga el split adecuado
               int cedula=Integer.parseInt(linea[1]);
               int numero=Integer.parseInt(linea[0]);
               li.addLast(new Puesto(numero, cedula));
           }
        b.close();
        return li;
    }
    
    public static CircularLinkedList<Video> leerVideos(String direccion) throws IOException{
        CircularLinkedList<Video> li= new CircularLinkedList<>();
        FileReader f = new FileReader(direccion);// la ubicacion del archivo debe ser en la carpeta raiz del proyecto, no dentro del src, prefentemente en la carpeta raiz hacer una carpeta llamada config y ahi meter todos los archivos que se necesiten leer.
        BufferedReader b = new BufferedReader(f);
        String cadena;
           while((cadena=b.readLine())!=null){
               String[] linea=cadena.split("\\|");//es necesario agregarle el doble \\ para que haga el split adecuado
               int numero=Integer.parseInt(linea[0]);
               String direc=linea[1];
               li.addLast(new Video(numero, direc));
           }
        b.close();
        return li;
    }
    
    public static SimplyLinkedList<Medico> leerMedicos(String direccion) throws IOException{
        SimplyLinkedList<Medico> li= new SimplyLinkedList<>();
        FileReader f = new FileReader(direccion);// la ubicacion del archivo debe ser en la carpeta raiz del proyecto, no dentro del src, prefentemente en la carpeta raiz hacer una carpeta llamada config y ahi meter todos los archivos que se necesiten leer.
        BufferedReader b = new BufferedReader(f);
        String cadena;
           while((cadena=b.readLine())!=null){
               String[] linea=cadena.split("\\|");//es necesario agregarle el doble \\ para que haga el split adecuado
               String nombre=linea[0];
               int cedula=Integer.parseInt(linea[1]);
               String especialidad=linea[2];
               int bol=Integer.parseInt(linea[3]);
               boolean sexo;
               if(bol==0) sexo=false;
               else sexo=true;
               li.addLast(new Medico(nombre, cedula, especialidad, sexo));
           }
        b.close();
        return li;
    }
}
