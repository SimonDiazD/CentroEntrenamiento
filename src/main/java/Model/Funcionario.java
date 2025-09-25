/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author juancamilo.gonzalez
 */
public class Funcionario extends Persona {
    private int salario;
    private int antiguedad;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(int salario, int antiguedad, String cargo) {
        this.salario = salario;
        this.antiguedad = antiguedad;
        this.cargo = cargo;
    }

    public Funcionario(int salario, int antiguedad, String cargo, String nombre, int identificacion, String telefono, String direccion) {
        super(nombre, identificacion, telefono, direccion);
        this.salario = salario;
        this.antiguedad = antiguedad;
        this.cargo = cargo;
    }

    // getters y setters
    public int getSalario() { return salario; }
    public void setSalario(int salario) { this.salario = salario; }

    public int getAntiguedad() { return antiguedad; }
    public void setAntiguedad(int antiguedad) { this.antiguedad = antiguedad; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public boolean esEntrenador() {
        if (this.cargo == null) return false;
        String c = this.cargo.trim().toLowerCase();
        return c.contains("entren") || c.contains("trainer") || c.contains("coach");
    }

    public boolean esCajero() {
        if (this.cargo == null) return false;
        String c = this.cargo.trim().toLowerCase();
        return c.contains("caj") || c.contains("cash") || c.contains("collector");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario{");
        sb.append(super.toString()).append(", ");
        sb.append("salario=").append(salario);
        sb.append(", antiguedad=").append(antiguedad);
        sb.append(", cargo=").append(cargo);
        sb.append('}');
        return sb.toString();
    }
}
