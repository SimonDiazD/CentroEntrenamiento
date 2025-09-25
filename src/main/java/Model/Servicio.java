/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author juancamilo.gonzalez
 */
public class Servicio {
    private int id;
    private int clienteId;        
    private int funcionarioId;    
    private LocalDate fechaInicio;
    private LocalDate fechaFin;   
    private String descripcion;   // plan generado según peso/actividad
    private String nombre;        // opcional (ej: "Plan 30 días - Tonificación")
    private String precio;        // si quieres conservar precio como texto

    public Servicio() { }

  
    public Servicio(int id, int clienteId, int funcionarioId, LocalDate fechaInicio, String descripcion, String nombre, String precio) {
        this.id = id;
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
        if (fechaInicio == null) fechaInicio = LocalDate.now();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusDays(30);
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public LocalDate getFechaInicio(){ 
        return fechaInicio; 
    }
    
    public void setFechaInicio(LocalDate fechaInicio){ 
        this.fechaInicio = fechaInicio;
        if (fechaInicio != null) this.fechaFin = fechaInicio.plusDays(30);
    }

    public LocalDate getFechaFin(){ 
        return fechaFin; 
    }
    
    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    public double getPrecioDouble() {
    if (precio == null) return 0.0;
    try {
        return Double.parseDouble(precio.replace(",", "").trim());
    } catch (NumberFormatException e) {
        return 0.0;
    }
    }
     public boolean planVigente(LocalDate fecha) {
        if (fechaInicio == null || fechaFin == null) return false;
        return (!fecha.isBefore(fechaInicio)) && (!fecha.isAfter(fechaFin));
    }
     
    public long diasRestantes() {
        if (fechaInicio == null) return 0;
        LocalDate fin = fechaInicio.plusDays(30); // duración del plan
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(fin)) {
            return 0; // ya venció
        }
        return ChronoUnit.DAYS.between(hoy, fin);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Servicio{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", clienteId=").append(clienteId);
        sb.append(", funcionarioId=").append(funcionarioId);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append('}');
        return sb.toString();
    }
    
}