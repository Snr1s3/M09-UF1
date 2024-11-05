package iticbcn.xifratge;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];

    public  IvParameterSpec generateIv() {
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public byte[] generateHash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        return digest.digest(input.getBytes());
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] textBytes = msg.getBytes();
        IvParameterSpec iv = generateIv();
        byte[] keyBytes = generateHash(clau);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(textBytes);
        byte[] encryptedMessage = new byte[iv.getIV().length + encryptedBytes.length];
        System.arraycopy(iv.getIV(), 0, encryptedMessage, 0, iv.getIV().length);
        System.arraycopy(encryptedBytes, 0, encryptedMessage, iv.getIV().length, encryptedBytes.length);
        return encryptedMessage;
    }

    public String desxifraAES (byte[] bIvIMsgXifrat , String clau) throws Exception {
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, iv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        byte[] encryptedBytes = new byte[bIvIMsgXifrat.length - iv.length];
        System.arraycopy(bIvIMsgXifrat, iv.length, encryptedBytes, 0, encryptedBytes.length);
        byte[] keyBytes = generateHash(clau);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error a l'esencriptar: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
            
        } catch (Exception e) {
            System.err.println("Error al desencriptar: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
    /*public void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet","Hola Andrés cómo está tu cuñado","Àgora ïlla Ôtto"};
            for (int i = 0; i < msgs.length; i++) {
                String msg = msgs[i];
                byte[] bXifrats = null;
                String desxifrat = "";
                try {
                    bXifrats = xifraAES(msg, CLAU);
                    desxifrat = desxifraAES (bXifrats, CLAU);
                }
                catch (Exception e) {
                    System.err.println("Error de xifrat: " + e.getLocalizedMessage ());
                }
                System.out.println("--------------------" );
                System.out.println("Msg: " + msg);
                System.out.println("Enc: " + new String(bXifrats));
                System.out.println("DEC: " + desxifrat);
            }
    }*/
}