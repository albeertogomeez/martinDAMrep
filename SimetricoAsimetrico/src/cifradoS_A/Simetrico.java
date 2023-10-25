package cifradoS_A;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Scanner;

public class Simetrico {

    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("¿Tiene una clave secreta? (Y/N): ");
        String respuesta = scanner.nextLine();
        
        Key secretKeyBytes;
        
        // Si el usuario tiene una clave secreta, se solicita y valida
        if (respuesta.equalsIgnoreCase("Y")) {
            while (true) {
                System.out.print("Ingrese la clave secreta en formato decimal (16 caracteres): ");
                String decimalKey = scanner.nextLine();
                
                // Verifica si la clave tiene 16 caracteres en formato decimal
                if (decimalKey.length() == 16) {
                    // Convierte la clave en un arreglo de bytes y crea una instancia de SecretKeySpec
                    byte[] arrayBytes = decimalKey.getBytes("UTF8");
                    secretKeyBytes = new SecretKeySpec(arrayBytes, "AES");
                    break;
                } else {
                    System.out.println("La clave debe tener exactamente 16 caracteres en formato decimal.");
                }
            }
        
            System.out.print("Ingrese el texto a cifrar: ");
            String textoSinCifrar = scanner.nextLine();
        
            // Obtiene los bytes de la clave secreta y cifra el texto
            byte[] claveSecretaBytes = secretKeyBytes.getEncoded();
            byte[] textoCifradoBytes = cifrarSimetrico(claveSecretaBytes, textoSinCifrar);
            String textoCifrado = byteArrayToHexString(textoCifradoBytes);
            System.out.println("Texto cifrado: " + textoCifrado);
        
            // Descifra el texto cifrado utilizando la clave secreta y lo imprime
            byte[] textoDescifradoBytes = descifrarSimetrico(claveSecretaBytes, textoCifradoBytes);
            String textoDescifrado = new String(textoDescifradoBytes, StandardCharsets.UTF_8);
            System.out.println("Texto descifrado: " + textoDescifrado);
        }
    }

    // Genera una clave secreta utilizando el algoritmo AES
    public static SecretKey generarClaveSecreta() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    // Cifra el texto utilizando la clave secreta y devuelve los bytes cifrados
    public static byte[] cifrarSimetrico(byte[] secretKeyBytes, String textoSinCifrar) throws Exception {
        SecretKeySpec clave = new SecretKeySpec(secretKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        return cipher.doFinal(textoSinCifrar.getBytes(StandardCharsets.UTF_8));
    }

    // Descifra el texto cifrado utilizando la clave secreta y devuelve los bytes descifrados
    public static byte[] descifrarSimetrico(byte[] secretKeyBytes, byte[] textoCifrado) throws Exception {
        SecretKeySpec clave = new SecretKeySpec(secretKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clave);
        return cipher.doFinal(textoCifrado);
    }

    // Convierte una cadena hexadecimal en un arreglo de bytes
    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                                 + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    // Convierte un arreglo de bytes en una cadena hexadecimal
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(String.format("%02x", aByte));
        }
        return sb.toString();
    }
}