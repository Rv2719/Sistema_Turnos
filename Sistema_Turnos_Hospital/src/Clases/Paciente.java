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
public class Paciente {
    private int turno;
    private String nombreApellido;
    private int edad;
    private boolean genero;
    private Sintoma sintoma;
 

    public Paciente(String nombreApellido, int edad, boolean genero, Sintoma sintoma, int turno) {
        this.nombreApellido = nombreApellido;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
        this.turno=turno;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    
    

    @Override
    public String toString() {
        return nombreApellido;
    }
    
    
    
    
}
