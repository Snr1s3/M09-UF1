public class Xifrador {
    public static void main(String[] args) {
        String text = args[0];
        String key = args[1];
        String encrypted = encrypt(text, key);
        System.out.println(encrypted);
    }

    public static String encrypt(String text, String key) {
        String encrypted = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char k = key.charAt(i % key.length());
            encrypted += (char) (c ^ k);
        }
        return encrypted;
    }
}