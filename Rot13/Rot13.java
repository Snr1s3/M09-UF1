public class Rot13
{
    public final static char[] arrayChar = 
    {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
    };
    public final static char[] arrayCharUpper = 
    {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
        'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'
    };

    public static char SwapChar(char c, int type)
    {
        for(int i = 0; i < arrayCharUpper.length; i++)
        {
            if(type == 1)
            {
                if(c == arrayCharUpper[i])
                {
                    c = arrayCharUpper[ i+ 13];
                    return (c);
                }
            }
            else if(type == 2)
            {
                if(c == arrayCharUpper[i])
                {
                    c = arrayCharUpper[i- 13];
                    return (c);
                }
            }
            else if(type == 3)
            {
                if(c == arrayChar[i])
                {
                    c = arrayChar[i - 13];
                    return (c);
                }
            }
            else
            {
                if(c == arrayChar[i])
                {
                    c = arrayChar[i + 13];
                    return (c);
                }
            }
        }
        return (c);
    }

    public static String xifraRot13(String message)
    {
        String newString = "";
        for(int i = 0; i < message.length(); i++)
        {
            if(Character.isUpperCase(message.charAt(i)))
            {
                newString += SwapChar(message.charAt(i), 1);
            }
            else
            {
                newString += SwapChar(message.charAt(i), 0);
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
                newString += swapChar(message.charAt(i), 2);
            }
            else
            {
                newString += swapChar(message.charAt(i), 3);
            }
        }
        return (newString);
    }

    public static void main(String args[])
    {
        String test = xifraRot13("TbXn ÑbZ PuN");
        System.out.println(test);
        test = desxifraRot13(test);
        System.out.println(test);
    }
}