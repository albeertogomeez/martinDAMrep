package cifradoHash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Pedir la contraseña y almacenarla en un archivo
        System.out.print("Introduce una nueva contrasena: ");
        String nuevaContrasena = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contrasena.txt"))) {
            writer.write(nuevaContrasena);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            return;
        }
        System.out.println("Contraseña almacenada correctamente en el archivo.");
        
        // Pedir la contraseña nuevamente para verificar
        System.out.print("Introduce la contrasena para acceder al programa: ");
        String contrasenaIngresada = scanner.nextLine();
        
        // Leer la contraseña almacenada en el archivo
        String contrasenaAlmacenada = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("contrasena.txt"))) {
            contrasenaAlmacenada = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            return;
        }
        
        // Comparar contraseñas
        if (contrasenaIngresada.equals(contrasenaAlmacenada)) {
            System.out.println("Contraseña correcta. Acceso permitido.");
        } else {
            System.out.println("Contraseña incorrecta. Acceso denegado.");
        }
        
        scanner.close();
    }

}
