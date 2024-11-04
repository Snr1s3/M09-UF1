package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {
    public byte[] data;

    public TextXifrat(byte[] data) {
        this.data = data;
    }

    public byte[] getBytes() {
        return data;
    }

     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append((char) b);
        }
        return sb.toString();
    }
}