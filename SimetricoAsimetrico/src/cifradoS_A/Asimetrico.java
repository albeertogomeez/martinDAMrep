package cifradoS_A;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class Asimetrico {
    private static KeyPair keyPair; // Almacena el par de claves público y privado

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        // Menú
        do {
            System.out.println("1. Cifrar mensaje");
            System.out.println("2. Descifrar mensaje");
            System.out.println("3. Regenerar claves");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el mensaje a cifrar: ");
                    String mensajeSinCifrar = scanner.nextLine();
                    String mensajeCifrado = cifrarAsimetrico(mensajeSinCifrar); // Cifra el mensaje
                    System.out.println("Mensaje cifrado: " + mensajeCifrado);
                    break;
                case 2:
                    System.out.print("Ingrese el mensaje cifrado previamente: "); // Copia y pega el texto cifrado antes
                    String mensajeCifradoDescifrado = scanner.nextLine();
                    String mensajeDescifrado = descifrarAsimetrico(mensajeCifradoDescifrado); // Descifra el mensaje
                    System.out.println("Mensaje descifrado: " + mensajeDescifrado);
                    break;
                case 3:
                    generarKeysAsimetrica(); // Regenera el par de claves público y privado
                    System.out.println("Claves regeneradas.");
                    break;
                case 4:
                    System.out.println("Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 4);
        scanner.close();
    }

    // Método para cifrar un mensaje utilizando el algoritmo RSA
    public static String cifrarAsimetrico(String textoSinCifrar) {
        try {
            // Genera las claves si aún no se han generado
            if (keyPair == null) {
                generarKeysAsimetrica();
            }
            
            // Inicializa el cifrador con el modo de cifrado y la clave pública
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            // Cifra el texto y lo convierte a una cadena codificada en Base64
            byte[] textoCifrado = cifrador.doFinal(textoSinCifrar.getBytes());
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            System.out.println("Error al cifrar el mensaje: " + e.getMessage());
            return null;
        }
    }

    // Método para descifrar un mensaje cifrado utilizando el algoritmo RSA
    public static String descifrarAsimetrico(String textoCifrado) {
        try {
            // Inicializa el descifrador con el modo de descifrado y la clave privada
            Cipher descifrador = Cipher.getInstance("RSA");
            descifrador.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            // Decodifica la cadena Base64 y luego descifra el texto
            byte[] textoDescifrado = descifrador.doFinal(Base64.getDecoder().decode(textoCifrado));
            // Convierte el resultado a una cadena y lo devuelve
            return new String(textoDescifrado);
        } catch (Exception e) {
            System.out.println("Error al descifrar el mensaje: " + e.getMessage());
            return null;
        }
    }

    // Método para generar un nuevo par de claves público y privado utilizando el algoritmo RSA
    public static void generarKeysAsimetrica() {
        try {
            // Inicializa un generador de claves RSA con un tamaño de clave de 2048 bits
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048); // Tamaño de la clave, ajustable
            // Genera el par de claves público y privado y lo almacena en la variable keyPair
            keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al generar las claves: " + e.getMessage());
        }
    }
}