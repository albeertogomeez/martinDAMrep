package cadena;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // Pedir al usuario el tamaño de la cadena
        int tamanoCadena = 5; // Tamaño por defecto
        // Inicializar semáforos
        Semaphore cadenaSemaphore = new Semaphore(tamanoCadena);
        Semaphore[] tipoSemaphores = {new Semaphore(0), new Semaphore(0), new Semaphore(0)}; // Semáforos por tipo

        // Crear operarios (hilos)
        Thread[] operarios = new Thread[3]; // Un operario por tipo de producto
        for (int i = 0; i < operarios.length; i++) {
            operarios[i] = new Thread(new Operario(i + 1, cadenaSemaphore, tipoSemaphores));
            operarios[i].start();
        }

        // Crear TipoProducto (hilos)
        Thread[] tipoProductos = new Thread[3]; // Un hilo por tipo de producto
        for (int i = 0; i < tipoProductos.length; i++) {
            tipoProductos[i] = new Thread(new TipoProducto(i + 1, cadenaSemaphore, tipoSemaphores));
            tipoProductos[i].start();
        }
    }
}
