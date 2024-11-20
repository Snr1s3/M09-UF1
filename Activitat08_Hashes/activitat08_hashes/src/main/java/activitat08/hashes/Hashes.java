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
        for(char c1 : arrayChar){
            String comb1 = c1 + "";
            npass++;
            if(getCombinacions( alg,  hash, comb1, salt)){
                return comb1;
            }
            for(char c2 : arrayChar){
                String comb2 = "" + c1 + c2;
                npass++;
                if(getCombinacions( alg,  hash, comb2, salt)){
                    return comb2;
                }
                for(char c3 : arrayChar){
                    String comb3 = "" + c1 + c2 + c3;
                    npass++;
                    if(getCombinacions( alg,  hash, comb3, salt)){
                        return comb3;
                    }
                    for(char c4 : arrayChar){
                        String comb4 = "" + c1 + c2 + c3 + c4;
                        npass++;
                        if(getCombinacions( alg,  hash, comb4, salt)){
                            return comb4;
                        }
                        for(char c5 : arrayChar){
                            String comb5 = "" + c1 + c2 + c3  + c4 + c5;
                            npass++;
                            if(getCombinacions( alg,  hash, comb5, salt)){
                                return comb5;
                            }
                            for(char c6 : arrayChar){
                                String comb6 = "" + c1 + c2 +c3 + c4 + c5 + c6;     
                                npass++;
                                if(getCombinacions( alg,  hash, comb6, salt)){
                                    return comb6;
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
