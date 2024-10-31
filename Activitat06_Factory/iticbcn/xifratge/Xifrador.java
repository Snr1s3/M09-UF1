package iticbcn.xifratge;
public interface Xifrador {
    public static void main(String[] args) {
        public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
        public String desxifra(TextXifrat text, String clau) throws
    }

    
}