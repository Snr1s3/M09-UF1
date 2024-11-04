package iticbcn.xifratge;
public class XifradorRotX implements Xifrador{
    public final static char[] arrayChar = "abcdefghijklmnopqrstuvwxyzñáéíóúüàèìòùâêîôûäëïöüç".toCharArray();
    public final static char[] arrayCharUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZÑÁÉÍÓÚÜÀÈÌÒÙÂÊÎÔÛÄËÏÖÜÇ".toCharArray();
    public char swapChar(char c, char[] array, boolean bool, int num) {
        num = num % array.length; // Ensure num is within the bounds of the array length
        if (bool) {
            for (int i = 0; i < array.length; i++) {
                if (c == array[i]) {
                    return ((i + num < array.length) ? array[i + num] : array[i + num - array.length]);
                }
            }
        } else {
            for (int o = array.length - 1; o >= 0; o--) {
                if (c == array[o]) {
                    return ((o - num >= 0) ? array[o - num] : array[o - num + array.length]);
                }
            }
        }
        return c;
    }

    public StringBuffer xifraRotX(String message, int num) {
        StringBuffer newString = new StringBuffer();
        for (int i = 0; i < message.length(); i++) {
            newString.append((Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, true, num) : swapChar(message.charAt(i), arrayChar, true, num));
        }
        return newString;
    }

    public StringBuffer desxifraRotX(String message, int num) {
        StringBuffer newString = new StringBuffer();
        for (int i = 0; i < message.length(); i++) {
            newString.append((Character.isUpperCase(message.charAt(i))) ? swapChar(message.charAt(i), arrayCharUpper, false, num) : swapChar(message.charAt(i), arrayChar, false, num));
        }
        return newString;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            int num = Integer.parseInt(clau);
            if (num < 0 || num > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return new TextXifrat(xifraRotX(msg, num).toString().getBytes());
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error a l'encriptar: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            int num = Integer.parseInt(clau);
            if (num < 0 || num > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desxifraRotX(new String(xifrat.getBytes()), num).toString();
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al desencriptar: " + e.getMessage());
        }
    }

    /*
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
    }*/
}