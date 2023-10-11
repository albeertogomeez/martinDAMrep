package cifradoHash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CifradoMD {

	public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        // Pedir la contraseña y almacenar su hash en un archivo
        System.out.print("Introduce una nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();
        String hashContrasena = obtenerHash(nuevaContrasena);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contrasenaHash.txt"))) {
            writer.write(hashContrasena);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            return;
        }
        System.out.println("Contraseña almacenada correctamente en el archivo.");
        
        // Pedir la contraseña nuevamente para verificar
        System.out.print("Introduce la contraseña para acceder al programa: ");
        String contrasenaIngresada = scanner.nextLine();
        String hashContrasenaIngresada = obtenerHash(contrasenaIngresada);
        
        // Leer el hash de la contraseña almacenada en el archivo
        String hashAlmacenado = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("contrasenaHash.txt"))) {
            hashAlmacenado = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
            return;
        }
        
        // Comparar hashes
        if (hashContrasenaIngresada.equals(hashAlmacenado)) {
            System.out.println("Contraseña correcta. Acceso permitido.");
        } else {
            System.out.println("Contraseña incorrecta. Acceso denegado.");
        }
        
        scanner.close();
    }
    
    private static String obtenerHash(String contrasena) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(contrasena.getBytes());
            for (byte b : hashedBytes) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

}