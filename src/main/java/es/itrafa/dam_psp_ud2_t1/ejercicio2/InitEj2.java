/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.itrafa.dam_psp_ud2_t1.ejercicio2;

import static java.lang.Math.random;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Inicia ejercicio 2
 *
 * Enunciado Ejercicio 2: Escoge un ejemplo de java donde hay hilos y pásalo a
 * expresión o forma lambda. Comprueba que funciona igual.
 *
 * @author it-ra
 */
public class InitEj2 {
    // Attributes
    private final static Logger LOG = Logger.getLogger(InitEj2.class.getName());

    //Methods
    /**
     * Ejecuta 5 hilos, uno detrás de otro.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOG.info("Inicio Ejercicio 2");
        // lanza 5 hilos. 
        int cantHilos = 5;
        for (int i = 0; i < cantHilos; i++) {
            try {
                lanzaHilos();

            } catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        LOG.info("Fin Ejercicio 2");

    }

    /**
     *
     * Crea hilo con expresión lambda. lo lanza, espera entre 1 y 4 segs hasta
     * finalizar El concepto concurrencia se pierde por el uso de join, ya que
     * no se seguirá ninguna orden hasta que acabe este hilo.
     *
     * @throws InterruptedException
     */
    private static void lanzaHilos() throws InterruptedException {
        // limites tiempo
        int max = 4;
        int min = 1;
        
        // hilo como contrucción lambda.
        // new Thread(Expresión lambda)
        // Expresión lambda = () -> {cuerpo función única} 
        // función única es run(), en este caso
        // Añadimos ordenes al objeto creado: 
        //   primero start
        //   y al objeto devuelto por start, que es
        new Thread(() -> {
            try {
                Random rn = new Random();
                int t = rn.nextInt(max -min+1) + min;
                LOG.log(Level.INFO, "{0} iniciado", Thread.currentThread().getName());
                Thread.sleep(t*1000);
                String msg = String.format("%s interrumpido %d segs y finalizando",
                        Thread.currentThread().getName(), t);
                LOG.info(msg);
            } catch (InterruptedException e) {
                LOG.log(Level.SEVERE, "{0} interrumpido", Thread.currentThread().getName());
            }
        }) {
            {
                start();
            }
        }.join(); // para que se inicien uno detras de otro

    }
}
