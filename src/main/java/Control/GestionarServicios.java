/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.*;
import Util.Lectura;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author juancamilo.gonzalez
 */
public class GestionarServicios {
    private Lectura lectura = new Lectura();  
    private ArrayList<Servicio> servicios = new ArrayList<Servicio>();
    private int siguienteId = 1;
    
    public Servicio crearServicio(Cliente cliente, Funcionario funcionario, LocalDate fechaInicio){
        if (cliente == null){
            System.out.println("No se encontró cliente. Operación cancelada.");
            return null;
        }
        if (funcionario == null){
            System.out.println("No se encontró funcionario. Operación cancelada.");
            return null;
        }
        
        if (fechaInicio == null){
            fechaInicio = LocalDate.now();
        }
       
        Servicio servicio = new Servicio();
        servicio.setId(siguienteId++); 
        servicio.setClienteId(cliente.getIdentificacion());   // referencia al cliente PONER GET Y SET EN CLIENTE Y FUNCIONARIO
        servicio.setFuncionarioId(funcionario.getIdentificacion()); // referencia al entrenador
        servicio.setFechaInicio(fechaInicio);
        
        System.out.println("Ingrese los datos del servicio.");
        servicio.setNombre(lectura.leerString("Ingrese el nombre del plan (ej: Plan 30 días - Tonificación): "));
        servicio.setDescripcion(lectura.leerString("Ingrese la descripción breve del plan (o ENTER para autogenerar): "));
        servicio.setPrecio(lectura.leerString("Ingrese el precio (opcional): "));

        servicios.add(servicio);

        System.out.println("Servicio (plan) creado correctamente:");
        System.out.println(servicio); 

        return servicio;
    }
    
    public Servicio obtenerServicioVigentePorCliente(int clienteId) {
        LocalDate hoy = LocalDate.now();
        for (int i = 0; i < servicios.size(); i++) {
            Servicio s = servicios.get(i);
            if (s.getClienteId() == clienteId && s.planVigente(hoy)) {
                return s;
            }
        }
        return null; 
    }
    
    public ArrayList<Servicio> listarServiciosPorFuncionario(int funcionarioId) {
        ArrayList<Servicio> resultado = new ArrayList<Servicio>();
        for (int i = 0; i < servicios.size(); i++) {
            Servicio s = servicios.get(i);
            if (s.getFuncionarioId() == funcionarioId) {
                resultado.add(s);
            }
        }
        return resultado;
    }
    
    public boolean cancelarServicio(int servicioId) {
        for (int i = 0; i < servicios.size(); i++) {
            if (servicios.get(i).getId() == servicioId) {
                servicios.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public String generarDescripcionPlan(Cliente c) {
        if (c == null) return "Plan 30 días - descripcion no disponible";
        String resultado = "Plan 30 dias: enfoque en ";
        Boolean practica = c.getPracticaActividadFisica();
        int minutos = c.getCantidadAFminutos();
        double peso = c.getPeso();

        if (practica != null && practica) {
            if (minutos >= 150) {
                resultado += "mejora de resistencia y fuerza";
            } else if (minutos >= 60) {
                resultado += "incrementar intensidad y mantener condicion";
            } else {
                resultado += "aumentar frecuencia y resistencia básica";
            }
        } else {
            resultado += "inicio de actividad y acondicionamiento general";
        }

        if (peso >= 95) {
            resultado += " con control de peso";
        } else if (peso < 60) {
            resultado += " con objetivo de ganancia muscular";
        } else {
            resultado += " con tono y definición";
        }
        return resultado;
    }
    
}
