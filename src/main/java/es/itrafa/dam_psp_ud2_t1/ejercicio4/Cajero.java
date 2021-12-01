/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio4;

import java.util.ArrayList;

/**
 * Recurso a compartir. Representa el cajero central con todos las localidades a
 * vender
 *
 *
 * @author it-ra
 */
public class Cajero {

    // ATTRIBUTES
    // nombre cajero
    private String nombre;
    // cant localidades vendidas sacadas del cajero
    private int localidades;
// cant localidades que le entregaron para vender
    private int localidadesInicio;
    //terminales a traves de las que se hace la venta
    private ArrayList<Terminal> listaTerminales;

    // CONSTRUCTORS
    Cajero(String nombre, int localidadesInicio, int cantTerminales) {
        this.nombre = nombre;
        localidades = localidadesInicio;
        this.localidadesInicio = localidadesInicio;

        // Prepara contenedor para terminales de venta para cajero
        listaTerminales = new ArrayList<>();
        // reparte localidades entre terminales
        int maxLocTerminal = localidadesInicio / cantTerminales;
        // crea terminales
        for (int i = 1; i <= cantTerminales; i++) {
            String nombreTerminal = "terminal " + i;
            listaTerminales.add(new Terminal(nombreTerminal, maxLocTerminal, this));
        }

    }

    // GETTER & SETTER
    /**
     * Permite manipular las terminales desde fuera de la clase
     *
     * @return
     */
    ArrayList<Terminal> getListaTerminales() {
        return listaTerminales;
    }

    // METHODS
    /**
     * Inicia los terminales del objeto Cajero
     */
    public void initTerminales() {
        for (Terminal t : listaTerminales) {
            t.start();
        }
    }

    /**
     * Suma localidades de cada terminal del Cajero
     *
     * @return Cantidad localidades vendidas
     */
    public void sumarLocalidades() {
        int total = 0;
        for (Terminal t : listaTerminales) {
            total += t.getLocalidadesVendidas();
        }
        localidades = total;
    }

    /**
     * Muestra las localidades vendidas
     *
     * @return String con las localidades vendidas
     */
    public int mostrarLocalidades() {
        return localidadesInicio - localidades;
    }

    /**
     * Devuelve el nombre del cajero
     *
     * @return
     */
    String getName() {
        return nombre;
    }

}
