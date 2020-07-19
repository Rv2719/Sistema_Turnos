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
public class Puesto {
    private int num;
    private int medico;
    private Paciente paciente=null;

    public Puesto(int num, int medico) {
        this.num = num;
        this.medico = medico;
    }

    @Override
    public String toString() {
        return  num +"";
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCedulaMedico() {
        return medico;
    }

    public void setCedulaMedico(int medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
    
}
