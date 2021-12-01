/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio4;

/**
 * Representa terminal de venta. la terminal simula varias ventas y las coordina
 * con el cajero, hasta que no quedan localidades en la terminal
 *
 * @author it-ra
 */
public class Terminal extends Thread {

    // ATTRIBUTES
    private final String nombre;
    private int cantLocalidades;
    private int cantInitLocalidades;
    final private Cajero c;
    private static final Object lock = new Object();

    // CONSTRUCTORS
    Terminal(String nombreTerminal, int maxVentaLocalidades, Cajero cajero) {
        this.nombre = nombreTerminal;
        this.cantLocalidades = maxVentaLocalidades;
        this.cantInitLocalidades = maxVentaLocalidades;
        this.c = cajero;
    }

    // GETTER & SETTER
    public int getLocalidadesVendidas() {
        return cantLocalidades;
    }

    // METHODS
    /**
     * Simula venta, actualiza datos según venta y los envia al cajero.
     *
     * @warning. Los mensajes de las ventas no salen bien coordinados, aunque
     * los finales están ok
     */
    @Override
    public void run() {
        while (true) {

            int venta = deseaComprar();// simula venta

            if (venta <= cantLocalidades) {// si venta es correcta

                synchronized (c) {

                    //System.out.printf("A %s le piden %2d localidades; ", nombre, venta);
                    cantLocalidades -= venta;
                    c.sumarLocalidades();
                    //System.out.printf("Vende %2d; Quedan %3d en terminal y en cajero se vendieron %d%n", venta, cantLocalidades, c.mostrarLocalidades());

                    if (cantLocalidades == 0) {// si no quedan localidades
                        System.out.printf("FIN TERMINAL: %s; vendio %d localidades y cierra%n", nombre, cantInitLocalidades - cantLocalidades);
                        System.out.printf("Total localidades vendidas: %d%n", c.mostrarLocalidades());
                        return;
                    }
                }
            } else { // si no hay localidades suficientes para venta
                //System.out.printf("A %s le piden %2d localidades; ", nombre, venta);
                //System.out.printf("No llegan, solo tiene %s%n", cantLocalidades);
            }

        }

    }

    /**
     * cantidad para simular una compra entre 1 y 20 billetes
     */
    private int deseaComprar() {
        int min = 1;
        int max = 20;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
