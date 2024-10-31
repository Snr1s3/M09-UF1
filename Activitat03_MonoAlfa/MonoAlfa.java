import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MonoAlfa{
    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public static char[] permutaAlfabet(){
        List<Character> charList = new ArrayList<>();
        for (char c : arrayChar) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        char[] shuffledArray = new char[charList.size()];
        for (int i = 0; i < charList.size(); i++) {
            shuffledArray[i] = charList.get(i);
        }
        return (shuffledArray);
    } 
    public static char swapChar(char c, char[] shuffled){
            for(int i = 0; i < arrayChar.length; i++){
                if(c == arrayChar[i])
                    return (shuffled[i]);
            }
        return (c);
    }
    public static String xifraMonoAlfa(String message, char[] shuffledArray){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray)));
            }
            else{
                newString.append(swapChar(c, shuffledArray));
            }
        }
        return (newString.toString());
    }
    public static String desxifraMonoAlfa(String message, char[] shuffledArray){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c), shuffledArray)));
            }
            else{
                newString.append(swapChar(c, shuffledArray));
            }
        }
        return (newString.toString());
    }
    public static void main(String args[]){
        String test = (args.length == 1) ? args[0] : "TEST: Bon Dia";
        char[] shuffledArray = permutaAlfabet();
        test = xifraMonoAlfa(test, shuffledArray);
        System.out.println(test);
        test = desxifraMonoAlfa(test, shuffledArray);
        System.out.println(test);
    }
}