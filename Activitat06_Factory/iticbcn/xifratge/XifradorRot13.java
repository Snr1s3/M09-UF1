package iticbcn.xifratge;
public class XifradorRot13 implements Xifrador{
    public final static char[] arrayChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public final static char[] arrayCharUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char swapChar(char c, char[] array, boolean bool){
        if(bool)
            for(int i = 0; i < array.length; i++){
                if(c == array[i])
                    return ((i + 13 < array.length) ? array[i + 13] : array[i - array.length + 13]);
            }
        else
            for(int o = array.length - 1; o >= 0; o--){
                if(c == array[o])
                    return ((o - 13 >= 0) ? array[o - 13] : array[o + array.length - 13]);
            }
        return (c);
    }
    public static String xifraRot13(String message){
        String newString = "";
        for(int i = 0; i < message.length(); i++){
            newString +=  (Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, true) : swapChar(message.charAt(i), arrayChar, true);
        }
        return (newString);
    }
    public static String desxifraRot13(String message){
        String newString = "";
        for(int i = 0; i < message.length(); i++){
            newString += (Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, false) : swapChar(message.charAt(i), arrayChar, false);
        }
        return (newString);
    }
    public static void main(String args[]){
        String test = (args.length == 1) ? args[0] : "TEST: Bon Dia";
        test = xifraRot13(test);
        System.out.println(test);
        test = desxifraRot13(test);
        System.out.println(test);
    }
}