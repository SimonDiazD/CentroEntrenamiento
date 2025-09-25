/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Funcionario;
import Util.Lectura;
import java.util.ArrayList;

/**
 *
 * @author juancamilo.gonzalez
 */
public class GestionarFuncionarios {

    private Lectura lectura = new Lectura();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public GestionarFuncionarios() {}

    public Funcionario crearFuncionario() {
        System.out.println("=== CREAR FUNCIONARIO ===");
        int id = lectura.leerInt("Ingrese la identificacion: ");

        if (buscarFuncionarioPorId(id) != null) {
            System.out.println("Ya existe un funcionario con ID " + id + ". No se crea uno nuevo.");
            return buscarFuncionarioPorId(id);
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setIdentificacion(id);
        funcionario.setNombre(lectura.leerString("Ingrese el nombre: "));
        funcionario.setDireccion(lectura.leerString("Ingrese la direccion: "));
        funcionario.setTelefono(lectura.leerString("Telefono: "));
        funcionario.setCargo(lectura.leerString("Cargo en la empresa: "));
        funcionario.setAntiguedad(lectura.leerInt("Ingrese la antiguedad: "));
        funcionario.setSalario(lectura.leerInt("Ingrese el salario: "));

        funcionarios.add(funcionario);
        System.out.println("Funcionario creado y agregado al sistema: " + funcionario.getNombre() + " (ID " + funcionario.getIdentificacion() + ")");
        return funcionario;
    }

    public void listarFuncionarios() {
        System.out.println("=== LISTADO DE FUNCIONARIOS ===");
        if (funcionarios.isEmpty()) {
            System.out.println("No hay funcionarios registrados.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
    }

    public Funcionario buscarFuncionarioPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getIdentificacion() == id) {
                return f;
            }
        }
        return null;
    }

    public Funcionario buscarPorCargo(String cargo) {
        if (cargo == null) return null;
        String cLower = cargo.trim().toLowerCase();
        for (Funcionario f : funcionarios) {
            if (f.getCargo() != null && f.getCargo().toLowerCase().contains(cLower)) {
                return f;
            }
        }
        return null;
    }
    
    
}