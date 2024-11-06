package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class ClauPublica {

    public ClauPublica() {}

    //GENERAR PARELL DE CLAUS RSA
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        //Bytes del missatge
        byte[] textBytes = msg.getBytes();
        //Xifrar amb RSA
        Cipher cipher = Cipher.getInstance("RSA");
        //Inicialitzar xifrat amb clau publica
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        //Missatge xifrat
        byte[] encryptedBytes = cipher.doFinal(textBytes);
        //Retornar missatge xifrat
        return encryptedBytes;
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada)throws Exception{
        String msgDesxifrat = null;
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] decryptedBytes = cipher.doFinal(msgXifrat);        
        msgDesxifrat = new String(decryptedBytes);
        return msgDesxifrat;
    }
}
