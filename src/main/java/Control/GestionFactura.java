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
public class GestionFactura implements Pago{
    private int siguienteId = 1;
    private ArrayList<Factura> facturas = new ArrayList<Factura>();
    private Lectura lectura = new Lectura();

    public GestionFactura() {}

    public Factura crearFactura(Cliente cliente, Servicio servicio, Funcionario cajero) {
        if (cliente == null || servicio == null || cajero == null) {
            System.out.println("Faltan datos (cliente/servicio/cajero). No se crea factura.");
            return null;
        }

        double precioServicio = 0.0;
        String precioStr = servicio.getPrecio();
        if (precioStr != null && !precioStr.trim().isEmpty()) {
            try {
                precioServicio = Double.parseDouble(precioStr.replace(",", "").trim());
            } catch (NumberFormatException e) {
                precioServicio = 0.0;
            }
        }
        if (precioServicio <= 0.0) {
            precioServicio = lectura.leerDouble("Precio del servicio no válido. Ingrese precio del mes: ");
        }

        double deudaAnterior = cliente.getDeuda();
        double valorTotal = deudaAnterior + precioServicio;
        System.out.println("Valor total a pagar (deuda anterior + mes) = " + valorTotal);

        double valorPagado = lectura.leerDouble("Ingrese el valor que paga ahora: ");
        if (valorPagado < 0) {
            System.out.println("Valor ingresado inválido. Se considera 0.");
            valorPagado = 0.0;
        }

        String fecha = lectura.leerString("Ingrese fecha (YYYY-MM-DD) o ENTER para hoy: ");
        if (fecha == null || fecha.trim().isEmpty()) {
            fecha = LocalDate.now().toString();
        } else {
            fecha = fecha.trim();
        }

        String mesPaga = lectura.leerString("Ingrese mes que paga (formato YYYY-MM) o ENTER para tomar la fecha: ");
        if (mesPaga == null || mesPaga.trim().isEmpty()) {
            try {
                LocalDate f = LocalDate.parse(fecha);
                mesPaga = String.format("%04d-%02d", f.getYear(), f.getMonthValue());
            } catch (Exception e) {
                mesPaga = "desconocido";
            }
        } else {
            mesPaga = mesPaga.trim();
        }

        String formaPago = lectura.leerString("Ingrese forma de pago (EFECTIVO/CHEQUE/TARJETA/FIADO): ");
        if (formaPago != null) formaPago = formaPago.trim();

        Factura factura = new Factura();
        factura.setNumeroFactura(siguienteId++);
        factura.setFecha(fecha);
        factura.setCliente(cliente);
        factura.setMesPaga(mesPaga);
        factura.setValorPagado(valorPagado); 
        factura.setServicio(servicio);
        factura.setFormaDePago(formaPago);
        factura.setCajero(cajero);

        facturas.add(factura);

        double nuevaDeuda = (deudaAnterior + precioServicio) - valorPagado;
        if (nuevaDeuda < 0) {
            System.out.println("El pago excede el total. Se deja deuda en 0 (no se gestiona saldo a favor).");
            nuevaDeuda = 0;
        }
        cliente.setDeuda(nuevaDeuda);

        System.out.println("Factura creada: " + factura);
        System.out.println("Nueva deuda del cliente (" + cliente.getIdentificacion() + ") = " + cliente.getDeuda());
        return factura;
    }

    public void mostrar() {
        System.out.println("=== LISTADO DE FACTURAS ===");
        for (Factura f : facturas) {
            System.out.println(f);
        }
        System.out.println("=== FIN LISTADO ===");
    }

    public Factura buscarFactura(int numero) {
        for (Factura f : facturas) {
            if (f.getNumeroFactura() == numero) return f;
        }
        System.out.println("No se encontró factura con número " + numero);
        return null;
    }

    public Factura buscarFactura(Cliente cliente) {
        if (cliente == null) return null;
        Factura ultima = null;
        for (Factura f : facturas) {
            if (f.getCliente() != null && f.getCliente().getIdentificacion() == cliente.getIdentificacion()) {
                ultima = f;
            }
        }
        if (ultima == null) {
            System.out.println("No se encontraron facturas para el cliente " + cliente.getIdentificacion());
        }
        return ultima;
    }

    
    public void arqueoDeCaja(int id, String fecha) {
        double totalGeneral = 0.0;
        double totalEfectivo = 0.0;
        double totalCheque = 0.0;
        double totalTarjeta = 0.0;
        double totalFiado = 0.0;

        if (fecha == null) fecha = "";

        for (Factura f : facturas) {
            if (f.getFecha() != null && f.getFecha().equals(fecha)) {
                double v = f.getValorPagado();
                totalGeneral += v;
                String forma = f.getFormaDePago() == null ? "" : f.getFormaDePago().trim().toLowerCase();
                if (forma.contains("efect")) totalEfectivo += v;
                else if (forma.contains("cheq")) totalCheque += v;
                else if (forma.contains("tarj") || forma.contains("card") || forma.contains("tarjeta")) totalTarjeta += v;
                else if (forma.contains("fiad") || forma.contains("fiado")) totalFiado += v;
            }
        }

        System.out.println("=== ARQUEO DE CAJA id=" + id + " fecha=" + fecha + " ===");
        System.out.println("Efectivo: " + totalEfectivo);
        System.out.println("Cheque: " + totalCheque);
        System.out.println("Tarjeta: " + totalTarjeta);
        System.out.println("Fiado: " + totalFiado);
        System.out.println("Total General: " + totalGeneral);
        System.out.println("=== FIN ARQUEO ===");
    }


    public void consultarEstadoCuenta(int clienteId, int mes, int anio, double cuotaMensual) {
        String mesStr = String.format("%04d-%02d", anio, mes); // p.ej. "2025-09"
        double totalPagado = 0.0;
        ArrayList<Factura> pagosMes = new ArrayList<Factura>();

        for (Factura f : facturas) {
            if (f.getCliente() == null) continue;
            if (f.getCliente().getIdentificacion() != clienteId) continue;


            String mesFactura = null;
            try {
                mesFactura = f.getMesPagaSeguro();
            } catch (Exception e) {
                mesFactura = null;
            }

            if (mesFactura == null || mesFactura.trim().isEmpty() || mesFactura.equalsIgnoreCase("desconocido")) {
                // intentar extraer de la fecha
                LocalDate ld = null;
                try { ld = f.getFechaComoLocalDate(); } catch (Exception ex) { ld = null; }
                if (ld != null) mesFactura = String.format("%04d-%02d", ld.getYear(), ld.getMonthValue());
                else mesFactura = "desconocido";
            }

            if (mesFactura.equals(mesStr)) {
                totalPagado += f.getValorPagado();
                pagosMes.add(f);
            }
        }

        double saldo = cuotaMensual - totalPagado;
        boolean puedeIngresar = (totalPagado >= cuotaMensual) || (saldo <= 0.0);

        System.out.println("=== ESTADO DE CUENTA clienteId=" + clienteId + " mes=" + mesStr + " ===");
        System.out.println("Cuota mensual esperada: " + cuotaMensual);
        System.out.println("Total pagado en el mes: " + totalPagado);
        System.out.println("Saldo (cuota - pagado): " + saldo);
        System.out.println("Puede ingresar: " + (puedeIngresar ? "SI" : "NO"));
        System.out.println("--- Detalle de pagos del mes ---");
        if (pagosMes.isEmpty()) {
            System.out.println("No se encontraron pagos para ese mes.");
        } else {
            for (Factura pf : pagosMes) {
                System.out.println("Factura #" + pf.getNumeroFactura()
                        + " | fecha=" + pf.getFecha()
                        + " | valor=" + pf.getValorPagado()
                        + " | forma=" + pf.getFormaDePago());
            }
        }
        System.out.println("=== FIN ESTADO ===");
    }

    @Override
    public void pagoEfectivo() {
        System.out.println("Ingrese el valor a pagar: ");
    }

    @Override
    public void pagoCheque() {
        System.out.println("Banco del cheque");
        System.out.println("Numero");
    }
}
    

