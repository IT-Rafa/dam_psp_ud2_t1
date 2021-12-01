/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainCajero → es el programa principal desde el que creamos un cajero, 3
 * terminales que trabajen con ese cajero, ponemos en funcionamiento los 3
 * terminales y cuando terminen mostramos un mensaje que nos indique cuántas
 * localidades han vendido (debería mostrar 60000, en ejecución sincronizada).
 *
 * @note: Al poner nombre MainCajero, asumo clase. Creo método init donde irá la
 * lógica indicada
 *
 * @author it-ra
 */
public class MainCajero {

    /**
     * instrucciones programa principal.
     */
    void init() {
        // Creamos cajero,
        // Le pasamos el nombre del cajero, la cantidad de localidades a vender y cant terminales
        Cajero c = new Cajero("Cajero Alameda", 6000, 3);

        // Inicamos terminales asignadas a cajero
        c.initTerminales();

        // Esperamos a que terminales finalicen
        for (Terminal t : c.getListaTerminales()) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainCajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Fin del programa");
    }

}
