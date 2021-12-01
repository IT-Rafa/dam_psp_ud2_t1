/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
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
    // Attributes
    private final static Logger LOG = Logger.getLogger(InitEj1.class.getName());
    // creating object of List<String>

    /**
     * Crea 2 listas con datos y los guarda combinados y en orden en otra.Los
     * datos se guardan en hilos, y cada hilo guarda una lista. Una lista va
     * antes que la otra.
     *
     * @param args
     */
    public static void main(String[] args) {
        LOG.info("Inicio Ejercicio 1");

        // lista enteros sincronizada. Solo el hilo que llegue antes puede manipularla
        // independientemente de cual arranque(start) primero
        final List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

        // lista con datos a rellenar
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i + 10);
        }
        System.out.printf("Lista de datos a rellenar:%n%s%n%s%n", list1, list2);

        FillerToList h1 = new FillerToList(list1, syncList);
        FillerToList h2 = new FillerToList(list2, syncList);

        // Ordenamos hilos para que el h2 no entre primero
        // sin los join, los hilos h1,h2 y main se ejecutarán cuando cuadre 
        try {
            h1.start(); // ejecuta run del hilo 1
            h1.join(); // bloquea hasta fin hilo 1
            h2.start(); // ejecuta run del hilo 2
            h2.join();  // bloquea hasta fin hilo 2
        } catch (InterruptedException ex) {

            LOG.log(Level.SEVERE, "Error: {0} interrumpido", Thread.currentThread().getName());
        }
        // Continua al finalizar ambos hilos
        System.out.printf("Lista completada:%n%s%n", syncList);
        LOG.info("Fin Ejercicio 1");
    }
}

/**
 * Rellena lista con datos de otra lista de forma concurrente.
 * @author it-ra
 */
class FillerToList extends Thread {
    // Attributes
    private final static Logger LOG = Logger.getLogger(FillerToList.class.getName());

    private final List<Integer> listToAdd; // lista con datos
    private final List<Integer> destList; // lista a rellenar

    // Contructor
    FillerToList(List<Integer> listToAdd, List<Integer> destList) {
        this.listToAdd = listToAdd;
        this.destList = destList;
    }

    /**
     * Tareas a ejecutar en hilo: Rellenar una lista con otra
     */
    @Override
    public void run() {
        LOG.log(Level.INFO, "Añadiendo a lista desde {0}", Thread.currentThread().getName());
        destList.addAll(listToAdd);
    }
}
