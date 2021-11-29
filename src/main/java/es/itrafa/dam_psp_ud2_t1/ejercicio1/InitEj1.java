/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Inicia ejercicio 1
 *
 * Enunciado Ejercicio 1: Crea un pequeño programa donde dos hilos tienen que
 * agregar elementos a una lista de objetos (por ejemplo integers) de manera
 * síncrona y ordenada. Al menos 20 elementos entre los dos hilos. Elige una
 * colección de java que aporte concurrencia para hilos.
 *
 * @author it-ra
 */
public class InitEj1 {

    private final static Logger LOG = Logger.getLogger(InitEj1.class.getName());
    // creating object of List<String>

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOG.info("Inicio Ejercicio 1");
        try {
            // create a synchronized list
            List<Integer> synlist = Collections
                    .synchronizedList(list);


            // populate the list
            Hilo1 h1 = new Hilo1();
            Hilo2 h2 = new Hilo2();
            // printing the Collection
            System.out.println("List : " + list);


            // printing the Collection
            System.out.println("Synchronized list is : " + synlist);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception thrown : " + e);
        }

    }
}
