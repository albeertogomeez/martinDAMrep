package pspIterator;
import java.util.*;
public class Principal {
	
	static Scanner sc = new Scanner(System.in);
	static Iterator<String> iterator;
	
	
	public static void insertData(ArrayList<String> collector) {
		
		iterator = collector.iterator();

		System.out.println("Inserta un dato cualquiera");
		String dato = sc.nextLine();
		
		
		while (iterator.hasNext()) {
			iterator.next();
		}
		
		collector.add(dato);
		
	}
	
	public static void deleteData(ArrayList<String> collector) {
		
		iterator = collector.iterator();
		
		System.out.println("Inserta un dato a eliminar");
		String dato = sc.nextLine();
		
		while (iterator.hasNext()) {
			String element = iterator.next();
			if (element.equals(dato)) {
				iterator.remove();
			}
		}
		
	}
	
	public static void showData(ArrayList<String> collector) {
		
		iterator = collector.iterator();
		
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
	
	
	public static void main(String[] args) {

		ArrayList<String> collector = new ArrayList<String>();
		
		collector.add("Dato 1");
		collector.add("Dato 2");
		
		int opcion = 0;
		
		do {
		System.out.println("-------- MENU --------");
		System.out.println("1. Insertar datos");
		System.out.println("2. Eliminar datos");
		System.out.println("3. Mostrar datos");
		System.out.println("0. Salir");
		
		opcion = sc.nextInt();
		sc.nextLine();
		
		switch (opcion) {
		case 0:
			System.out.println("Ha elegido salir");
			break;
		case 1:
			System.out.println("Ha elegido insertar datos");
			insertData(collector);
			break;
		case 2:
			System.out.println("Ha elegido eliminar datos");
			deleteData(collector);
			break;
		case 3:
			System.out.println("Ha elegido mostrar datos:");
			showData(collector);
			break;
		default:
			System.out.println("Opcion invalida. Por favor, escoja un numero del 1 al 3");
			break;
		}
		
		} while (opcion != 0);
		
 
	}

}
