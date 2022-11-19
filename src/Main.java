import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import BitTorrent.Bencode;
import unet.bencode.variables.BencodeObject;



public class Main {

    public static void main(String[] args) throws IOException {
        String file_path = "C:\\Users\\Никита\\Desktop\\Overlord__Raising_Hell_P__RUS_2__ENG_RUS__ENG__2007" +
                "__1_4__P2P__rutracker-6264723_torrent.torrent";
        ArrayList<String> data =Bencode.GetData(file_path);
        System.out.println("list "+data);
        byte[] b = Base64.getDecoder().decode(data.get(4));
        FileOutputStream fos = new FileOutputStream("pathname");
        fos.write(b);

    }


};

