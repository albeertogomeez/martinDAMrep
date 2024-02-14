package hilos;

import java.util.concurrent.Semaphore;

public class Orden {
    // Semáforos para controlar el orden de ejecución
    private static Semaphore semaforo1 = new Semaphore(0);
    private static Semaphore semaforo2 = new Semaphore(0);

    public static void main(String[] args) {
        // Crear los hilos
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Esperar a que se libere el semáforo 1
                try {
                    semaforo1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Imprimir el mensaje del hilo 1
                System.out.println("Hola desde el hilo 1");
            }
        });

        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Imprimir el mensaje del hilo 2
                System.out.println("Hola desde el hilo 2");

                // Liberar el semáforo 1
                semaforo1.release();
            }
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
    }
}
