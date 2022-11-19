import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import BitTorrent.Bencode;
import unet.bencode.variables.BencodeObject;



public class Main {

    public static void main(String[] args) throws IOException {
        String file_path = "C:\\Users\\user\\Downloads\\Overlord + Raising Hell [P] [RUS(2) + ENG RUS + ENG] (2007) (1.4) [P2P] [rutracker-6264723].torrent";
        ArrayList<String> data =Bencode.GetData(file_path);
        System.out.println("list "+data);

    }


};

