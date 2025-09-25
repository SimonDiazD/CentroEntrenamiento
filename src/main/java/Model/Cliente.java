/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author juancamilo.gonzalez
 */
public class Cliente extends Persona{
    private int estratoSE;
    private String trabajaEn;
    private Boolean practicaActividadFisica;
    private String actividadFisica;
    private int cantidadAFminutos;
    private double peso;
    private double deuda;

    public Cliente() {
    }

    public Cliente(int estratoSE, String trabajaEn, Boolean practicaActividadFisica, String actividadFisica, int cantidadAFminutos, double peso, double deuda) {
        this.estratoSE = estratoSE;
        this.trabajaEn = trabajaEn;
        this.practicaActividadFisica = practicaActividadFisica;
        this.actividadFisica = actividadFisica;
        this.cantidadAFminutos = cantidadAFminutos;
        this.peso = peso;
        this.deuda = deuda;
    }

    public Cliente(int estratoSE, String trabajaEn, Boolean practicaActividadFisica, String actividadFisica, int cantidadAFminutos, double peso, double deuda, String nombre, int identificacion, String telefono, String direccion) {
        super(nombre, identificacion, telefono, direccion);
        this.estratoSE = estratoSE;
        this.trabajaEn = trabajaEn;
        this.practicaActividadFisica = practicaActividadFisica;
        this.actividadFisica = actividadFisica;
        this.cantidadAFminutos = cantidadAFminutos;
        this.peso = peso;
        this.deuda = deuda;
    }
    
    public int getEstratoSE() {
        return estratoSE;
    }

    public void setEstratoSE(int estratoSE) {
        this.estratoSE = estratoSE;
    }

    public String getTrabajaEn() {
        return trabajaEn;
    }

    public void setTrabajaEn(String trabajaEn) {
        this.trabajaEn = trabajaEn;
    }

    public Boolean getPracticaActividadFisica() {
        return practicaActividadFisica;
    }

    public void setPracticaActividadFisica(Boolean practicaActividadFisica) {
        this.practicaActividadFisica = practicaActividadFisica;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public int getCantidadAFminutos() {
        return cantidadAFminutos;
    }

    public void setCantidadAFminutos(int cantidadAFminutos) {
        this.cantidadAFminutos = cantidadAFminutos;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public boolean tieneDeuda() {
        return this.deuda > 0.0;
    }

    public void reducirDeuda(double cantidad) {
        if (cantidad <= 0) return;
        this.deuda = this.deuda - cantidad;
        if (this.deuda < 0) this.deuda = 0.0; // no permitimos deuda negativa
    }

    public void aumentarDeuda(double cantidad) {
        if (cantidad <= 0) return;
        this.deuda = this.deuda + cantidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append(super.toString()).append(", "); // incluye nombre/identificacion/telefono/direccion
        sb.append("estratoSE=").append(estratoSE);
        sb.append(", trabajaEn=").append(trabajaEn);
        sb.append(", practicaActividadFisica=").append(practicaActividadFisica);
        sb.append(", actividadFisica=").append(actividadFisica);
        sb.append(", cantidadAFminutos=").append(cantidadAFminutos);
        sb.append(", peso=").append(peso);
        sb.append(", deuda=").append(deuda);
        sb.append('}');
        return sb.toString();
    }
   
}