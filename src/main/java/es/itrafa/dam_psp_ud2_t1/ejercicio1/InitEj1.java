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

        final List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

        Thread h1 = new Thread(() -> {
            synchronized (syncList) {
                for (int i = 0; i < 10; i++) {
                    String msg = String.format("Añadiendo %d%n", i);
                    LOG.info(msg);
                    syncList.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOG.severe("Thread h1 was innterrupted");
                    }
                }
            }
        });


        Thread h2 = new Thread(() -> {
            synchronized (syncList) {
                for (int i = 10; i < 20; i++) {
                    String msg = String.format("Añadiendo %d%n", i);
                    LOG.info(msg);
                    syncList.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        LOG.severe("Thread h2 was innterrupted");
                    }
                }
            }
        });
        h1.start();
        h2.start();
    }
}
