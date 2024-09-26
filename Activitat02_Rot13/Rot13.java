public class Rot13{
    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public final static char[] arrayCharUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZÑÁÉÍÓÚÜÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÇ".toCharArray();
    public static char swapChar(char c, char[] array, boolean bool, int num){
        if(bool)
            for(int i = 0; i < array.length; i++){
                if(c == array[i])
                    return ((i + num < array.length) ? array[i + num] : array[i - array.length + num]);
            }
        else
            for(int o = array.length - 1; o >= 0; o--){
                if(c == array[o])
                    return ((o - num >= 0) ? array[o - num] : array[o + array.length - num]);
            }
        return (c);
    }
    public static StringBuffer xifraRot13(String message, int num){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            newString.append((Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, true, num) : swapChar(message.charAt(i), arrayChar, true, num));
        }
        return (newString);
    }
    public static StringBuffer desxifraRot13(String message, int num){
        StringBuffer newString = new StringBuffer();
        for(int i = 0; i < message.length(); i++){
            newString.append((Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, false, num) : swapChar(message.charAt(i), arrayChar, false, num));
        }
        return (newString);
    }
    public static void test(String args[]){
        String test = (args.length >= 1) ? args[0] : "TEST: Bon Dia";
        int num = (args.length >= 2 && args[1] != null && args[1].matches("[0-9]+") && Integer.parseInt(args[1])<= arrayChar.length-1) ? Integer.parseInt(args[1]) : 13;
        test = xifraRot13(test, num).toString();
        System.out.println(test);
        System.out.println(desxifraRot13(test, num).toString());
    }
    public static void forcaBrutaRotX(String args[]){
        String test = (args.length >= 1) ? args[0] : "TEST: Bon Dia";
        for(int num = 0; num <= arrayChar.length-1; num++){
            System.out.println("Rot"+num+":");
            String[] newArgs = {test, String.valueOf(num)};
            test(newArgs);
        }
    }
    public static void main(String args[]){
        if(args.length > 1 && args[1].equals("F"))
            forcaBrutaRotX(args);
        else
            test(args);
    }
}