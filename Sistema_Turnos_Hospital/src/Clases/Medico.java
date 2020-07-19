/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Josue
 */
public class Medico {
    private String nombre;
    private int cedula;
    private String especialidad;
    private boolean sexo;
    private static Puesto puesto;

    public Medico(String nombre, int cedula, String especialidad, boolean sexo) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cedula=cedula;
        this.sexo=sexo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public static Puesto getPuesto() {
        return puesto;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public static void setPuesto(Puesto puesto) {
        Medico.puesto = puesto;
    }
    
    
}
