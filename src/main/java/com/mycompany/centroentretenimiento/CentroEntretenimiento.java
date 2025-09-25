/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.centroentretenimiento;

import Model.*;
import Control.*;
import Util.Lectura;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author juancamilo.gonzalez
 * Simón Díaz
 */

public class CentroEntretenimiento {
    private static GestionarCliente gc = new GestionarCliente();
    private static GestionarFuncionarios gf = new GestionarFuncionarios();
    private static GestionarServicios gs = new GestionarServicios();
    private static GestionFactura gfactura = new GestionFactura();
    private static Lectura lectura = new Lectura();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== CENTRO DE ENTRETENIMIENTO ===");
            System.out.println("1. Cliente");
            System.out.println("2. Cajero");
            System.out.println("3. Entrenador");
            System.out.println("0. Salir");
            opcion = lectura.leerInt("Seleccione rol: ");

            switch (opcion) {
                case 1 -> menuCliente();
                case 2 -> menuCajero();
                case 3 -> menuEntrenador();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    private static void menuCliente() {
        int op;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver mi plan vigente");
            System.out.println("2. Inscribirme a un plan (30 dias)");
            System.out.println("3. Ver mis datos");
            System.out.println("0. Volver");
            op = lectura.leerInt("Opcion: ");

            switch (op) {
                case 1 -> {
                    int id = lectura.leerInt("Ingrese su identificación: ");
                    Cliente c = gc.buscarClientePorId(id);
                    if (c == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                    }
                    Servicio s = gs.obtenerServicioVigentePorCliente(c.getIdentificacion());
                    if (s == null) System.out.println("No tiene plan vigente.");
                    else System.out.println("Plan vigente: " + s + "\nDías restantes: " + s.diasRestantes());
                }
                case 2 -> {
                    int id = lectura.leerInt("Ingrese su identificación (o 0 para crear nuevo): ");
                    Cliente c;
                    if (id == 0) c = gc.crearCliente();
                    else {
                        c = gc.buscarClientePorId(id);
                        if (c == null) {
                            System.out.println("Cliente no encontrado. Se creará uno nuevo.");
                            c = gc.crearCliente();
                        }
                    }

                    System.out.println("Desea usar un entrenador existente? (s/n)");
                    String resp = lectura.leerString("");
                    Funcionario entrenador = null;
                    if ("s".equalsIgnoreCase(resp)) {
                        int idF = lectura.leerInt("Ingrese identificacion del entrenador: ");
                        entrenador = gf.buscarFuncionarioPorId(idF);
                        if (entrenador == null) {
                            System.out.println("No existe entrenador con ese ID.");
                        }
                    }
                    if (entrenador == null) {
                        System.out.println("Creando nuevo entrenador:");
                        entrenador = gf.crearFuncionario();
                    }

                    String fechaStr = lectura.leerString("Ingrese fecha de inicio (YYYY-MM-DD) o ENTER para hoy: ");
                    LocalDate fechaInicio = null;
                    if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                        try { fechaInicio = LocalDate.parse(fechaStr.trim()); } catch (Exception e) { fechaInicio = LocalDate.now(); }
                    }

                    Servicio s = gs.crearServicio(c, entrenador, fechaInicio);
                    if (s != null) System.out.println("Inscripción completa: " + s);
                }
                case 3 -> {
                    int id = lectura.leerInt("Ingrese su identificación: ");
                    Cliente c = gc.buscarClientePorId(id);
                    if (c == null) System.out.println("Cliente no encontrado.");
                    else System.out.println(c);
                }
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción invalida.");
            }
        } while (op != 0);
    }

    private static void menuCajero() {
        int op;
        do {
            System.out.println("\n--- MENU CAJERO ---");
            System.out.println("1. Crear factura / registrar pago");
            System.out.println("2. Consultar estado de cuenta de cliente");
            System.out.println("3. Arqueo de caja");
            System.out.println("0. Volver");
            op = lectura.leerInt("Opción: ");

            switch (op) {
                case 1 -> {
                
                    int idCliente = lectura.leerInt("Ingrese identificación del cliente: ");
                    Cliente c = gc.buscarClientePorId(idCliente);
                    if (c == null) {
                        System.out.println("Cliente no encontrado. Cree uno primero.");
                        c = gc.crearCliente();
                    }

                    Servicio servicio = gs.obtenerServicioVigentePorCliente(c.getIdentificacion());
                    if (servicio == null) {
                        System.out.println("No hay servicio vigente para el cliente. Crear uno ahora.");
                        int idEntr = lectura.leerInt("Ingrese ID del entrenador o 0 para crear uno nuevo: ");
                        Funcionario ent = null;
                        if (idEntr != 0) ent = gf.buscarFuncionarioPorId(idEntr);
                        if (ent == null) ent = gf.crearFuncionario();
                        servicio = gs.crearServicio(c, ent, null);
                    }

                    int idCajero = lectura.leerInt("Ingrese identificacion del cajero (funcionario): ");
                    Funcionario cajero = gf.buscarFuncionarioPorId(idCajero);
                    if (cajero == null) {
                        System.out.println("Cajero no encontrado. Creelo ahora.");
                        cajero = gf.crearFuncionario();
                    }

                    gfactura.crearFactura(c, servicio, cajero);
                }
                case 2 -> {
                    int idCliente = lectura.leerInt("Ingrese identificacion del cliente: ");
                    Cliente c = gc.buscarClientePorId(idCliente);
                    if (c == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                    }


                    Servicio s = gs.obtenerServicioVigentePorCliente(c.getIdentificacion());
                    double cuota = lectura.leerDouble("Ingrese cuota mensual (0 para usar precio del servicio vigente si existe): ");
                    if (cuota == 0.0 && s != null) {
                        cuota = parsePrecioServicio(s);
                        System.out.println("Usando cuota tomada del servicio vigente: " + cuota);
                    }

                    int mes = lectura.leerInt("Ingrese mes (1-12): ");
                    int anio = lectura.leerInt("Ingrese año (ej. 2025): ");
                    gfactura.consultarEstadoCuenta(c.getIdentificacion(), mes, anio, cuota);
                }
                case 3 -> {
                    String fecha = lectura.leerString("Ingrese fecha para arqueo (YYYY-MM-DD): ");
                    int idArq = lectura.leerInt("Ingrese id de arqueo (numero identificador): ");
                    gfactura.arqueoDeCaja(idArq, fecha);
                }
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private static void menuEntrenador() {
        int op;
        do {
            System.out.println("\n--- MENU ENTRENADOR ---");
            System.out.println("1. Ver mis clientes y planes");
            System.out.println("2. Asignar plan a un cliente");
            System.out.println("0. Volver");
            op = lectura.leerInt("Opcion: ");

            switch (op) {
                case 1 -> {
                    int idEntr = lectura.leerInt("Ingrese su identificación (entrenador): ");
                    Funcionario ent = gf.buscarFuncionarioPorId(idEntr);
                    if (ent == null) {
                        System.out.println("Entrenador no encontrado.");
                        break;
                    }
                    ArrayList<Servicio> list = gs.listarServiciosPorFuncionario(ent.getIdentificacion());
                    if (list.isEmpty()) System.out.println("No tiene clientes asignados.");
                    else {
                        System.out.println("Clientes y planes del entrenador " + ent.getNombre() + ":");
                        for (Servicio s : list) {
                            Cliente c = gc.buscarClientePorId(s.getClienteId());
                            System.out.println("Cliente: " + (c == null ? "ID " + s.getClienteId() : c.getNombre()) + " - Plan: " + s);
                        }
                    }
                }
                case 2 -> {
                    int idCliente = lectura.leerInt("Ingrese identificación del cliente: ");
                    Cliente c = gc.buscarClientePorId(idCliente);
                    if (c == null) {
                        System.out.println("Cliente no encontrado. Crear cliente ahora.");
                        c = gc.crearCliente();
                    }
                    int idEntr = lectura.leerInt("Ingrese identificación del entrenador (o 0 para crear nuevo): ");
                    Funcionario ent = null;
                    if (idEntr != 0) ent = gf.buscarFuncionarioPorId(idEntr);
                    if (ent == null) ent = gf.crearFuncionario();

                    String fechaStr = lectura.leerString("Ingrese fecha de inicio (YYYY-MM-DD) o ENTER para hoy: ");
                    LocalDate fechaInicio = null;
                    if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                        try { fechaInicio = LocalDate.parse(fechaStr.trim()); } catch (Exception e) { fechaInicio = LocalDate.now(); }
                    }

                    Servicio s = gs.crearServicio(c, ent, fechaInicio);
                    if (s != null) System.out.println("Plan asignado: " + s);
                }
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }


    private static double parsePrecioServicio(Servicio s) {
        if (s == null) return 0.0;
        String p = s.getPrecio();
        if (p == null || p.trim().isEmpty()) return 0.0;
        try {
            return Double.parseDouble(p.replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
}
}