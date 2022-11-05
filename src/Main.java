import java.io.IOException;
import BitTorrent.Bencode;


public class Main {
    public static void main(String[] args) throws IOException {
        char x = 0;
        System.out.println("Start main: " + x);
        Bencode code = new Bencode(123);
        code.printSymbol(x);
    }
}
