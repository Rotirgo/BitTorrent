
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import BitTorrent.*;
import BitTorrent.UrlConnectionTracker;


public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String file_path = "C:\\Users\\Никита\\Desktop\\MoralPsychHandbook.torrent";
                //"C:\\Users\\Никита\\Desktop\\ComputerNetworks.torrent";
                //"C:\\Users\\Никита\\Desktop\\Overlord__Raising_Hell_P__RUS_2__ENG_RUS__ENG__2007" +
                //"__1_4__P2P__rutracker-6264723_torrent.torrent";
        ArrayList<String> data =Bencode.GetData(file_path);
        System.out.println("list "+data);

        // piece приводим к первоначальному виду
        byte[] pieces = Base64.getDecoder().decode(data.get(4));  // разбить по 20 байт
        String piecesStr = new String(pieces, StandardCharsets.UTF_8);

        // из data надо еще сделать по правилам bencode данные info
        String announce = data.get(0);
        int length = Integer.parseInt(data.get(1));
        String name = data.get(3);
        int pieceLength = Integer.parseInt(data.get(2));

        String infoBencode = "d6:lengthi"+length+"e4:name"+name.length()+":"+name+
                "12:piece lengthi"+pieceLength+"e6pieces:"+pieces.length+":"+piecesStr+"e";
        byte[] info = infoBencode.getBytes(StandardCharsets.UTF_8);
        String infoHashHex = SHA1.toSHA1(info);
        String infoHash = Options.getInfoHashForRequest(infoHashHex, "%"); // 06661137d3fabf9b27e75deb4d0a0906bbe789ad

//        System.out.println("\n");
//
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

