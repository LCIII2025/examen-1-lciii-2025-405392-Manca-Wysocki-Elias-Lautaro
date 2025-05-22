package org.example.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstacionamientoTest {

    private Estacionamiento estacionamiento;
    private Ticket ticketMock;
    private Cliente clienteMock;
    private Vehiculo vehiculoMock;

    @BeforeEach
    public void setUp() {
        estacionamiento = new Estacionamiento();

        clienteMock = new Cliente("ABC123", "Cliente Mock");
        vehiculoMock = new Vehiculo("ABC123", "Gol", Vehiculo.Tipo.AUTO);
        ticketMock = new Ticket(clienteMock, vehiculoMock);
        estacionamiento.ingresarVehiculo("ABC123", "Cliente Mock", vehiculoMock);
    }

    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO test
        Ticket ticket = estacionamiento.retirarVehiculo("ABC123");

        assertNotNull(ticket.getHoraSalida());
        assertTrue(ticket.getHoraSalida().isAfter(ticket.getHoraEntrada()));
    }

    @Test
    public void testRetirarVehiculoThrowException() throws Exception {
        //TODO test
        Exception exception = assertThrows(Exception.class, () -> {
            this.estacionamiento.retirarVehiculo("ABC450");
        });

        assertEquals("Vehiculo no encontrado", exception.getMessage());
    }

    @Test
    void testCalcularPrecioAuto() {
        LocalDateTime horaEntrada = LocalDateTime.of(2025, 4, 22, 18, 0);
        LocalDateTime horaSalida = horaEntrada.plusMinutes(80);

        Vehiculo vehiculo = new Vehiculo("ABC123", "Corsa", Vehiculo.Tipo.AUTO);
        Cliente cliente = new Cliente("12345678", "Juan");
        Ticket ticket = new Ticket(cliente, vehiculo, horaEntrada);

        ticket.setHoraSalida(horaSalida);

        assertEquals(200.0, ticket.calcularPrecio());
    }

    @Test
    void testCalcularPrecioSUV() {
        LocalDateTime horaEntrada = LocalDateTime.of(2025, 4, 22, 18, 0);
        LocalDateTime horaSalida = horaEntrada.plusMinutes(80);

        Vehiculo vehiculo = new Vehiculo("DEF456", "Tracker", Vehiculo.Tipo.SUV);
        Cliente cliente = new Cliente("87654321", "Lu");
        Ticket ticket = new Ticket(cliente, vehiculo, horaEntrada);

        ticket.setHoraSalida(horaSalida);

        assertEquals(260.0, ticket.calcularPrecio());
    }

    @Test
    void testCalcularPrecioPickup() {
        LocalDateTime horaEntrada = LocalDateTime.of(2025, 4, 22, 18, 0);
        LocalDateTime horaSalida = horaEntrada.plusMinutes(80);

        Vehiculo vehiculo = new Vehiculo("XYZ789", "Amarok", Vehiculo.Tipo.PICKUP);
        Cliente cliente = new Cliente("11223344", "Pepe");
        Ticket ticket = new Ticket(cliente, vehiculo, horaEntrada);

        ticket.setHoraSalida(horaSalida);

        assertEquals(360.0, ticket.calcularPrecio());
    }
}