package iticbcn.xifratge;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class XifradorMonoAlfa implements Xifrador{
    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public static char[] shuffledArray;

    public XifradorMonoAlfa() {
        permutaAlfabet();
    }
    public void permutaAlfabet(){
        List<Character> charList = new ArrayList<>();
        for (char c : arrayChar) {
            charList.add(c);
        }
        Collections.shuffle(charList);
        shuffledArray = new char[charList.size()];
        for (int i = 0; i < charList.size(); i++) {
            shuffledArray[i] = charList.get(i);
        }
    } 
    public char swapChar(char c){
        for(int i = 0; i < arrayChar.length; i++){
            if(c == arrayChar[i])
                return (shuffledArray[i]);
        }
        return (c);
    }
    public String xifraMonoAlfa(String message){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c))));
            }
            else{
                newString.append(swapChar(c));
            }
        }
        return (newString.toString());
    }
    public String desxifraMonoAlfa(String message){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isUpperCase(c)){
                newString.append(Character.toUpperCase(swapChar(Character.toLowerCase(c))));
            }
            else{
                newString.append(swapChar(c));
            }
        }
        return (newString.toString());
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada{
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        try {
            return new TextXifrat(xifraMonoAlfa(msg).getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada("Error a l'encriptar: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if(clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        try {
            return desxifraMonoAlfa(new String(xifrat.getBytes()));
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al desencriptar: " + e.getMessage());
        }
    }
    /*
    public static void main(String args[]){
        String test = (args.length == 1) ? args[0] : "TEST: Bon Dia";
        char[] shuffledArray = permutaAlfabet();
        test = xifraMonoAlfa(test, shuffledArray);
        System.out.println(test);
        test = desxifraMonoAlfa(test, shuffledArray);
        System.out.println(test);
    }*/
}