package cadena;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TipoProducto implements Runnable {
    private int tipo;
    private Semaphore cadenaSemaphore;
    private Semaphore[] tipoSemaphores;
    private Random random;
    private int totalEmpaquetados;

    public TipoProducto(int tipo, Semaphore cadenaSemaphore, Semaphore[] tipoSemaphores) {
        this.tipo = tipo;
        this.cadenaSemaphore = cadenaSemaphore;
        this.tipoSemaphores = tipoSemaphores;
        this.random = new Random();
        this.totalEmpaquetados = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Esperar a que haya un producto de mi tipo en la cadena
                tipoSemaphores[tipo - 1].acquire();
                System.out.println("Operario " + tipo + " retiró un producto de la cadena.");

                // Incrementar el contador de productos empaquetados
                totalEmpaquetados++;
                System.out.println("Total de productos empaquetados: " + totalEmpaquetados);

                // Simular el proceso de empaquetado
                Thread.sleep(random.nextInt(2000) + 1000);

                // Liberar espacio en la cadena
                cadenaSemaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}