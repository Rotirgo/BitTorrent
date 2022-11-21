
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import BitTorrent.*;
import BitTorrent.UrlConnectionTracker;


public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String file_path = "C:\\Users\\Никита\\Desktop\\MoralPsychHandbook.torrent";
                //"C:\\Users\\Никита\\Desktop\\ComputerNetworks.torrent";
                //"C:\\Users\\Никита\\Desktop\\Overlord__Raising_Hell_P__RUS_2__ENG_RUS__ENG__2007__1_4__P2P__rutracker-6264723_torrent.torrent";
        // Парсинг нужных данных
        ArrayList<String> data =Bencode.GetData(file_path);
        System.out.println("list "+data);

        // Создание info, чтобы потом хэшировать
        byte[] pieces = Base64.getDecoder().decode(data.get(4));
        String announce = data.get(0);

        byte[] info = Bencode.getInfo(data, pieces);
        String infoHashHex = SHA1.toSHA1(info);
        String infoHash = Options.getInfoHashForRequest(infoHashHex, "%");

//        FileOutputStream fos = new FileOutputStream("pathname");
//        fos.write(info);

        // запрос к трекеру
        StringBuilder request = new StringBuilder();
        request.append(announce);
        Options.addParameter(request, "info_hash", infoHash);

        String peerID = Options.getPeerID();
        Options.addParameter(request, "peer_id", peerID);

        Options.addParameter(request, "port", "6881");

        System.out.println("\ninfo_hash: " + infoHash);
        System.out.println("peer_id: " + peerID + "\n");
        System.out.println(request.toString());

        UrlConnectionTracker.Connect(request.toString());

    }

};

