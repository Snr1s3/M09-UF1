package activitat08.hashes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public final char[] arrayChar = "abcdefABCDEF1234567890!".toCharArray();
    public int npass = 0;

    public Hashes() {}

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
        npass = 0;   
        StringBuilder sb = new StringBuilder();
        for(char c1 : arrayChar){
            npass++;
            sb.setLength(0);
            sb.append(c1);
            if(getCombinacions( alg,  hash, sb.toString(), salt)){
                return sb.toString();
            }
            for(char c2 : arrayChar){
                npass++;
                sb.setLength(1);
                sb.append(c2);
                if(getCombinacions( alg,  hash, sb.toString(), salt)){
                    return sb.toString();
                }
                for(char c3 : arrayChar){
                    npass++;
                    sb.setLength(2);
                    sb.append(c3);
                    if(getCombinacions( alg,  hash, sb.toString(), salt)){
                        return sb.toString();
                    }
                    for(char c4 : arrayChar){
                        npass++;
                        sb.setLength(3);
                        sb.append(c4);
                        if(getCombinacions( alg,  hash, sb.toString(), salt)){
                            return sb.toString();
                        }
                        for(char c5 : arrayChar){
                            npass++;
                            sb.setLength(4);
                            sb.append(c5);
                            if(getCombinacions( alg,  hash, sb.toString(), salt)){
                                return sb.toString();
                            }
                            for(char c6 : arrayChar){     
                                npass++;
                                sb.setLength(5);
                                sb.append(c6);
                                if(getCombinacions( alg,  hash,sb.toString(), salt)){
                                    return sb.toString();
                                }           
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public Boolean getCombinacions(String alg, String hash, String comb, String salt) {
        if(alg.equals("SHA-512")){
            return getSHA512AmbSalt(comb, salt).equals(hash);
        }else if(alg.equals("PBKDF2")){
            return getPBKDF2AmbSalt(comb, salt).equals(hash);
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long interval = Math.abs(t2 - t1);
        long m = interval % 1000;
        long s = (interval / 1000) % 60;
        long min = (interval / (1000 * 60)) % 60;
        long h = (interval / (1000 * 60 * 60)) % 24;
        long d = interval / (1000 * 60 * 60 * 24);
        return String.format("%d dies, %02d hores, %02d minuts, %02d segons, %03d mil·lisegons",  d, h, min, s, m);
    }
}
