package activitat08.hashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.util.ArrayList;
import java.util.List;

public class Hashes {
    public final char[] arrayChar = "abcdefABCDEF1234567890!".toCharArray();
    public int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            // Obtenir els Bytes de la contrasenya i el salt
            byte[] pwBytes = pw.getBytes();
            byte[] saltBytes = salt.getBytes();

            // Crear un objecte MessageDigest amb l'algorisme SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            // Actualitzar el digest amb el salt i la contrasenya
            digest.update(saltBytes);
            byte[] hashedBytes = digest.digest(pwBytes);

            // Comvertir els bytes a format hexadecimal
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            // Obtenir els Bytes de la contrasenya i el salt
            byte[] saltBytes = salt.getBytes();

            // Crear una especificació de clau PBEKeySpec
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, 10000, 512);

            // Crear una instància de SecretKeyFactory per PBKDF2 amb HMAC SHA-512
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            // Generar la clau derivada
            byte[] hashedBytes = factory.generateSecret(spec).getEncoded();

            // Convertir els bytes a format hexadecimal
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metode per convertir un array de bytes a un String hexadecimal
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public String forcaBruta(String alg, String hash, String salt) {
        StringBuilder sb = new StringBuilder();
        int nChar = -1;
        int counter = 0;
        
        
        
        return null;
    }


    public List<String> getCombinacions() {
        
    }
    public String getInterval(long t1, long t2) {
        long interval = Math.abs(t2 - t1);

        long milliseconds = interval % 1000;
        long seconds = (interval / 1000) % 60;
        long minutes = (interval / (1000 * 60)) % 60;
        long hours = (interval / (1000 * 60 * 60)) % 24;
        long days = interval / (1000 * 60 * 60 * 24);

        return String.format("%d days, %02d hours, %02d minutes, %02d seconds, %03d milliseconds",
                days, hours, minutes, seconds, milliseconds);
    }
    public static void main(String[] args) {
        Hashes hashes = new Hashes();
        hashes.getCombinacions();
    }
}
