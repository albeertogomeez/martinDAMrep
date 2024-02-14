package cadena;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Operario implements Runnable {

    private int tipo;
    private Semaphore cadenaSemaphore;
    private Semaphore[] tipoSemaphores;
    private Random random;

    public Operario(int tipo, Semaphore cadenaSemaphore, Semaphore[] tipoSemaphores) {
        this.tipo = tipo;
        this.cadenaSemaphore = cadenaSemaphore;
        this.tipoSemaphores = tipoSemaphores;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Crear un paquete de tipo aleatorio
                int tipoPaquete = random.nextInt(3) + 1;

                // Si el paquete no es de mi tipo, continuar
                if (tipoPaquete != tipo) continue;

                // Esperar a que haya espacio en la cadena
                cadenaSemaphore.acquire();
                System.out.println("Operario " + tipo + " colocó un producto en la cadena.");

                // Incrementar el contador de productos de mi tipo
                tipoSemaphores[tipo - 1].release();
                System.out.println("Operario " + tipo + " ha procesado un paquete.");

                // Dormir un tiempo aleatorio antes de colocar otro producto
                Thread.sleep(random.nextInt(2000) + 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}