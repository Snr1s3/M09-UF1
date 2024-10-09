import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PoliAlfabetic{

    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public static char[] shuffledArray = new char[arrayChar.length];
    public static Long clauSecreta = 123456789L;
    public static Random random; 


    public static void initRandom(){
        random = new Random(clauSecreta);
    } 
    public static char[] permutaAlfabet(char[] shuffled){
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
    public static char swapChar(char c, char[] shuffled, boolean bool){
        if(bool)
            for(int i = 0; i < arrayChar.length; i++){
                if(c == arrayChar[i])
                    return (shuffled[i]);
            }
        else
            for(int o = shuffled.length - 1; o >= 0; o--){
                if(c == shuffled[o])
                    return (arrayChar[o]);
            }
        return (c);
    }
    public static String xifraPoliAlfa(String message){
        char[] shuffledArray = permutaAlfabet(arrayChar);
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray, true)));
            }
            else{
                newString.append(swapChar(c, shuffledArray, true));
            }
            shuffledArray = permutaAlfabet(shuffledArray);
        }
        return (newString.toString());
    }
    public static String desxifraPoliAlfa(String message){
        char[] shuffledArray = permutaAlfabet(arrayChar);
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray, false)));
            }
            else{
                newString.append(swapChar(c, shuffledArray, false));
            }
            shuffledArray = permutaAlfabet(shuffledArray);
        }
        return (newString.toString());
    }

    public static void main ( String [] args ) {
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
    }
}