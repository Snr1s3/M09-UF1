public class Rot13
{
    public final static char[] array_char = 
    {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
    };
    public final static char[] array_char_m = 
    {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
        'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'
    };

    public static char swap_char(char c, int type)
    {
        for(int i = 0; i < array_char_m.length; i++)
        {
            if(type == 1)
            {
                if(c == array_char_m[i])
                {
                    c = array_char_m[ i+ 13];
                    return (c);
                }
            }
            else if(type == 2)
            {
                if(c == array_char_m[i])
                {
                    c = array_char_m[i+ 13];
                    return (c);
                }
            }
            else if(type == 3)
            {
                if(c == array_char[i])
                {
                    c = array_char[i+ 13];
                    return (c);
                }
            }
            else
            {
                if(c == array_char[i])
                {
                    c = array_char[i + 13];
                    return (c);
                }
            }
        }
        return (c);
    }
    public static String xifraRot13(String message, int rot)
    {
        String newString = "";
        for(int i = 0; i < message.length(); i++)
        {
            if(Character.isUpperCase(message.charAt(i)))
            {
                newString += swap_char(message.charAt(i), 1);
            }
            else
            {
                newString += swap_char(message.charAt(i), 0);
            }
        }
        return (newString);
    }
    public static String desxifraRot13(String message)
    {
        String newString = "";
        for(int i = 0; i < message.length(); i++)
        {
            if(Character.isUpperCase(message.charAt(i)))
            {
                newString += swap_char(message.charAt(i), 2);
            }
            else
            {
                newString += swap_char(message.charAt(i), 3);
            }
        }
        return (newString);
    }

    public static void main(String args[])
    {
        String test = rot13("HoLa BoN DiA", 0)
        System.out.println(test);
        test = rot13("HoLa BoN DiA", 0)
    }
}