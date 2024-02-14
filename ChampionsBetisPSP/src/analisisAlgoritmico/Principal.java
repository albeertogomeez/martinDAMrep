package analisisAlgoritmico;

import java.util.Scanner;

public class Principal {

	    public static int ejemploPeorCaso(int n) {
	        int suma = 0;
	        for (int i = 0; i < n; i++) {
	            suma += i;
	        }
	        return suma;
	    }

	    public static void main(String[] args) {
	    	Scanner sc = new Scanner(System.in);
	    	System.out.println("Introduce un numero");
	        int n = sc.nextInt();
	        int resultado = ejemploPeorCaso(n);
	        System.out.println("Resultado: " + resultado);
	    }
	}
