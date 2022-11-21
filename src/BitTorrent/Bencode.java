package BitTorrent;

import unet.bencode.variables.BencodeObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public interface Bencode {

    static ArrayList<String> GetData(String file) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(file));
        return Separate(new BencodeObject(b).toString());
    }

    static ArrayList<String> Separate(String data){
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        System.out.println(data);
        patterns.add(Pattern.compile("announce.{12}(.+)\u001b\\[0m"));
        patterns.add(Pattern.compile("length.{12}(\\d+)\u001b\\[0m"));
        patterns.add(Pattern.compile("name.{12}(\\w.+)\u001b\\[0m"));
//        patterns.add(Pattern.compile("piece length.{12}(\\d+)\u001b\\[0m"));
        patterns.add(Pattern.compile("piece.{20} \\{ (.+) \\}\u001b\\[0m"));
        ArrayList<String> lst = new ArrayList<>();
        for(Pattern pattern : patterns)
        {
            Matcher matcher  =  pattern.matcher(data);
            while(matcher.find())
                lst.add(matcher.group(1));
        }
        return lst;
    }

    static byte[] getInfo(ArrayList<String> data, byte[] pieces){
        String piecesStr = new String(pieces, StandardCharsets.UTF_8);
        int length = Integer.parseInt(data.get(1));
        String name = data.get(3);
        int pieceLength = Integer.parseInt(data.get(2));
        String result = "d6:lengthi"+length+"e4:name"+name.length()+":"+name+
                "12:piece lengthi"+pieceLength+"e6pieces:"+pieces.length+":"+piecesStr+"e";
        return result.getBytes(StandardCharsets.UTF_8);
    }

}
