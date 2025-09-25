/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Cliente;
import Util.Lectura;
import java.util.ArrayList;

/**
 *
 * @author juancamilo.gonzalez
 */
public class GestionarCliente {
    private Lectura lectura = new Lectura();
    private ArrayList<Cliente> listaClientes = new ArrayList();
    
    public Cliente crearCliente(){
        Cliente cliente = new Cliente();
        System.out.println("=== CREAR CLIENTE ===");
        cliente.setIdentificacion(lectura.leerInt("Ingrese la identificación: "));
        cliente.setNombre(lectura.leerString("Ingrese el nombre: "));
        cliente.setDireccion(lectura.leerString("Ingrese la dirección: "));
        cliente.setTelefono(lectura.leerString("Ingrese el teléfono: "));
        cliente.setEstratoSE(lectura.leerInt("Ingrese el estrato socioeconómico: "));
        cliente.setTrabajaEn(lectura.leerString("Ingrese el lugar de trabajo: "));
        cliente.setPracticaActividadFisica(lectura.leerBoolean("¿Practica actividad física? (true/false): "));
        if (cliente.getPracticaActividadFisica()) {
            cliente.setActividadFisica(lectura.leerString("Ingrese qué actividad física realiza: "));
            cliente.setCantidadAFminutos(lectura.leerInt("Ingrese minutos semanales de actividad física: "));
        }
        cliente.setPeso(lectura.leerDouble("Ingrese el peso (kg): "));
        cliente.setDeuda(0.0);

        listaClientes.add(cliente);
        System.out.println("Cliente creado y agregado a la lista.");
        return cliente;
    }
    
    
    public Cliente crearCliente(String mensaje){
        Cliente cliente = new Cliente();
        System.out.println(mensaje);
        cliente.setIdentificacion(lectura.leerInt("Ingrese la identificción: "));
        listaClientes.add(cliente);
        return cliente;
    }
    
     public Cliente buscarClientePorId(int id) {
        for (Cliente c : listaClientes) {
            if (c.getIdentificacion() == id) {
                return c;
            }
        }
        System.out.println("No se encontró cliente con ID: " + id);
        return null;
    }

 
    public void listarClientes() {
        System.out.println("=== LISTADO DE CLIENTES ===");
        for (Cliente c : listaClientes) {
            System.out.println(c);
        }
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        }
    }
    
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
   
}