import java.io.IOException;

import BitTorrent.Bencode;


public class Main {
    public static void main(String[] args) throws IOException {
        char x = '1';
        System.out.println("Start main: " + x);
        Bencode.printSymbol(x);
    }
}
