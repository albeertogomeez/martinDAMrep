package Cipher;
import java.util.*;
public class Principal {

	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		String textoOriginal;
		int desplazamiento;
		
		System.out.println("Introduce un texto cualquiera");
		textoOriginal = sc.nextLine();
		System.out.println("Introduce el numero de caracteres a desplazar");
		desplazamiento = sc.nextInt();
		
		String textoCifrado = Cifrado(textoOriginal, desplazamiento);
		System.out.println("El texto '" + textoOriginal + "' ha sido desplazado un numero de " + desplazamiento + 
				" caracteres, de manera que el texto se ve como '" + textoCifrado + "'.");
		
	}
	
	public static String Cifrado(String texto, int despl) {
		StringBuilder SB = new StringBuilder();
		
		for (int i = 0; i < texto.length(); i++) {
			char caracter = texto.charAt(i);
			
			if (Character.isLetter(caracter)) {
				char newCaracter = (char) (caracter + despl);
				
				if ((Character.isUpperCase(caracter) && newCaracter > 'Z') || 
					(Character.isLowerCase(caracter) && newCaracter > 'z')) {
					newCaracter = (char) (caracter - (26 - despl));
				}
			
				SB.append(newCaracter);
		} else {
				SB.append(caracter);
			}
		
		}
		return SB.toString();
	}
}
