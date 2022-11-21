
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import BitTorrent.Bencode;
import BitTorrent.Options;


public class Main {

    public static void main(String[] args) throws IOException {
        String file_path = "C:\\Users\\Никита\\Desktop\\Overlord__Raising_Hell_P__RUS_2__ENG_RUS__ENG__2007" +
                "__1_4__P2P__rutracker-6264723_torrent.torrent";
        ArrayList<String> data =Bencode.GetData(file_path);
        System.out.println("list "+data);

        // piece приводим к первоначальному виду
        byte[] pieces = Base64.getDecoder().decode(data.get(4));  // разбить по 20 байт
        String str = new String(pieces, StandardCharsets.UTF_8);

        // из data надо еще сделать по правилам bencode данные info
        String announce = data.get(0);
        int length = Integer.parseInt(data.get(1));
        String name = data.get(3);
        int pieceLength = Integer.parseInt(data.get(2));

        String infoBencode = "d6:lengthi"+length+"e4:name"+name.length()+":"+name+
                "12:piece lengthi"+pieceLength+"e6pieces:"+pieces.length+":"+str+"e";
        byte[] info = infoBencode.getBytes(StandardCharsets.UTF_8);
        String infoHash = Options.toSHA1(info);

//        System.out.println(infoHash);
//        System.out.println("\n");
//
//        FileOutputStream fos = new FileOutputStream("pathname");
//        fos.write(info);

        // запрос к трекеру
        String peerID = Options.getPeerID();
        System.out.println(peerID);
        String request = announce+"?info_hash="+infoHash+"&peer_id="+peerID+"&uploaded="+0+"&downloaded="+0+
                "&left="+length+"&port="+6889+"&compact="+1;  //подумать над запросом
        System.out.println(request);

//        Socket socket = new Socket(request, 8080);
//        OutputStream os = socket.getOutputStream();
//        os.write(request.getBytes());
//        os.flush();
//
//        InputStream is = socket.getInputStream();
//        int ch;
//        while( (ch=is.read())!= -1)
//            System.out.print((char)ch);
//        socket.close();

    }


};

