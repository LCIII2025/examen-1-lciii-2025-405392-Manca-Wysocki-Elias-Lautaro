package org.example.parking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cliente {
    private final String dni;
    private final String nombre;
    private final List<Vehiculo> vehiculos;

    public Cliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        // TODO implementar la carga de vehiculos en el cliente
        this.vehiculos.add(vehiculo);
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        // TODO implementar la busqueda de un vehiculo segun su patente
        return this.vehiculos.stream().filter(vehiculo -> vehiculo.getPatente().equals(patente)).findFirst().orElse(null);
    }
}