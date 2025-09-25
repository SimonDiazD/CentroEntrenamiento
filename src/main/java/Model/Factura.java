/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 *
 * @author juancamilo.gonzalez
 */

public class Factura {
    
    private String fecha;
    private int numeroFactura;
    private Cliente cliente;
    private String mesPaga;
    private double valorPagado;
    private Servicio servicio;
    private String formaDePago;
    private Funcionario cajero;

    public Factura() {
    }
    
    public Factura(String fecha, int numeroFactura, Cliente cliente, String mesPaga, double valorPagado, Servicio servicio, String formaDePago, Funcionario cajero) {
        this.fecha = fecha;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.mesPaga = mesPaga;
        this.valorPagado = valorPagado;
        this.servicio = servicio;
        this.formaDePago = formaDePago;
        this.cajero = cajero;
    }
    
    public void setValorPagado(double valorPagado) {
        if (valorPagado < 0) {
            this.valorPagado = 0.0;
        } else {
            this.valorPagado = valorPagado;
        }
    }

    public LocalDate getFechaComoLocalDate() {
        if (this.fecha == null) return null;
        try {
            return LocalDate.parse(this.fecha.trim());
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    public String getMesPagaSeguro() {
        if (this.mesPaga != null && !this.mesPaga.trim().isEmpty()) {
            return this.mesPaga.trim();
        }
        LocalDate ld = getFechaComoLocalDate();
        if (ld != null) {
            return String.format("%04d-%02d", ld.getYear(), ld.getMonthValue());
        }
        return "desconocido";
    }

    public Funcionario getCajero() {
        return cajero;
    }

    public void setCajero(Funcionario cajero) {
        this.cajero = cajero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMesPaga() {
        return mesPaga;
    }

    public void setMesPaga(String mesPaga) {
        this.mesPaga = mesPaga;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{");
        sb.append("fecha=").append(fecha);
        sb.append(", numeroFactura=").append(numeroFactura);
        sb.append(", cliente=").append(cliente);
        sb.append(", mesPaga=").append(mesPaga);
        sb.append(", valorPagado=").append(valorPagado);
        sb.append(", servicio=").append(servicio);
        sb.append(", formaDePago=").append(formaDePago);
        sb.append(", cajero=").append(cajero);
        sb.append('}');
        return sb.toString();
    }


    
}