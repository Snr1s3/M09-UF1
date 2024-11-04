package iticbcn.xifratge;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class XifradorPoliAlfabetic implements Xifrador{

    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public static char[] shuffledArray = new char[arrayChar.length];
    public static Long clauSecreta = 123456789L;
    public static Random random; 

    public XifradorPoliAlfabetic() {
        initRandom();
    }
    public void initRandom(){
        random = new Random();
    } 
    public char[] permutaAlfabet(char[] shuffled){
        List<Character> charList = new ArrayList<>();
        for (char c : shuffled) {
            charList.add(c);
        }
        Collections.shuffle(charList, random);
        char[] shuffledArray = new char[charList.size()];
        for (int i = 0; i < charList.size(); i++) {
            shuffledArray[i] = charList.get(i);
            //System.out.print(shuffledArray[i]);
        }
        //System.out.println();
        return (shuffledArray);
    } 
    public char swapChar(char c, char[] shuffled){
        for(int i = 0; i < arrayChar.length; i++){
            if(c == arrayChar[i])
                return (shuffled[i]);
        }
        return (c);
    }
    public String xifraPoliAlfa(String message){
        char[] shuffledArray = permutaAlfabet(arrayChar);
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray)));
            }
            else{
                newString.append(swapChar(c, shuffledArray));
            }
            shuffledArray = permutaAlfabet(shuffledArray);
        }
        return (newString.toString());
    }
    public String desxifraPoliAlfa(String message){
        char[] shuffledArray = permutaAlfabet(arrayChar);
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray)));
            }
            else{
                newString.append(swapChar(c, shuffledArray));
            }
            shuffledArray = permutaAlfabet(shuffledArray);
        }
        return (newString.toString());
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            clauSecreta = Long.parseLong(clau);
            random.setSeed(clauSecreta);
            String encrypted = xifraPoliAlfa(msg);
            return new TextXifrat(encrypted.getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error a l'encriptar: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            clauSecreta = Long.parseLong(clau);
            random.setSeed(clauSecreta);
            String decrypted = desxifraPoliAlfa(new String(xifrat.getBytes()));
            return decrypted;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al desencriptar: " + e.getMessage());
        }
    }
    /*public static void main ( String [] args ) {
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre" ,
            "Test 02 Taüll, DÍA, año" ,
            "Test 03 Peça, Òrrius, Bòvila" };
        String msgsXifrats [] = new String [ msgs. length ];
        System . out . println ( "Xifratge: \n --------" );
        for ( int i = 0; i < msgs. length ; i ++) {
            initRandom();
            msgsXifrats [ i ] = xifraPoliAlfa ( msgs[ i ]);
            System . out . printf ( "%-34s -> %s%n", msgs[ i ], msgsXifrats [ i ]);
        }
        System . out . println ( "Desxifratge: \n -----------" );
        for ( int i = 0; i < msgs. length ; i ++) {
            initRandom();
            String msg = desxifraPoliAlfa ( msgsXifrats [ i ]);
            System . out . printf ( "%-34s -> %s%n", msgsXifrats [ i ], msg);
        }
    }*/
}