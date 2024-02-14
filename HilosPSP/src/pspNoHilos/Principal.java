package pspNoHilos;

public class Principal {

	public static void main(String[] args) {
        long inicio, fin; // Variables para almacenar el tiempo inicial y final
        
        HiloMilCeros hiloMilCeros = new HiloMilCeros();
        HiloMilUnos hiloMilUnos = new HiloMilUnos();

        inicio = System.currentTimeMillis(); // Tiempo inicial
        hiloMilCeros.start(); // Llama al método que quieres medir
        fin = System.currentTimeMillis(); // Tiempo final


        inicio = System.currentTimeMillis(); // Tiempo inicial
        hiloMilUnos.start(); // Llama al método que quieres medir
        fin = System.currentTimeMillis(); // Tiempo final

        
        System.out.println("");
        System.out.println("Tiempo de ejecución de milCeros(): " + (fin - inicio) + " milisegundos");
        
        System.out.println("");
        System.out.println("Tiempo de ejecución de milUnos(): " + (fin - inicio) + " milisegundos");
        


        // Iniciar los hilos
//        hiloMilCeros.start();
//        hiloMilUnos.start();
	}
	
}
//	public static void milCeros() {
//		for (int i = 0; i < 1000; i++) {
//			int numero = 0;
//			System.out.print(numero);
//		}
//	}
	
//	public static void milUnos() {
//		for (int i = 0; i < 1000; i++) {
//			int numero = 1;
//			System.out.print(numero);
//		}
//	}

	
	class HiloMilCeros extends Thread {
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println("0");
			}
		}
	}
	
	class HiloMilUnos extends Thread {
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println("1");
			}
		}
	}
