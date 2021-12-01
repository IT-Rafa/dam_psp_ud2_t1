/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio3;

import es.itrafa.dam_psp_ud2_t1.ejercicio2.InitEj2;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Inicia ejercicio 3
 *
 * Enunciado Ejercicio 3: Crea un programa donde se lanzan cuatro hilos. Estos
 * hilos compartirán un recurso que es Contador.java(...) Cada hilo una vez que
 * obtiene dicho recurso lo aumenta en 1, y sólo lo podrá hacerlo 3 veces.
 * Puedes ejecutar algún sleep para que no vaya tan rápido la ejecución. Una
 * posible salida:(...)
 *
 * @author it-ra
 */
public class InitEj3 {

    private final static Logger LOG = Logger.getLogger(InitEj3.class.getName());

    ThreadGroup tg1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crea instancia Contador
        Contador c = new Contador();

        // Crea hilos que modifiquen contador (3, de forma sincroinzada)
        Incrementer h1 = new Incrementer("Hilo1", c);
        Incrementer h2 = new Incrementer("Hilo2", c);
        Incrementer h3 = new Incrementer("Hilo3", c);
        Incrementer h4 = new Incrementer("Hilo4", c);
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(InitEj3.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Terminamos el programa");
    }

}

class Incrementer extends Thread {

    // Attributes
    private final Contador c;

    // Contructor
    Incrementer(String threadName, Contador contador) {
        this.setName(threadName);
        this.c = contador;
    }

    // Methods
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            synchronized (c) {
                c.setContador(c.getContador() + 1);
                System.out.println(getName() + " - contador - " + c.getContador());
            }
        }
        System.out.println("Fin..." + getName());

    }
}

/**
 * PARTE DE ENUNCIADO Almacena entero encapsulado
 *
 * @author enunciado
 */
class Contador {

    int contador = 0;

    public int getContador() {
        return this.contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
