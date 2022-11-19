package BitTorrent;

import unet.bencode.variables.BencodeObject;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Bencode {

    static void printSymbol(char c){
        System.out.println("Symbol: " + c);
    }

    static ArrayList<String> GetData(String file) throws IOException {
        byte[] b = Files.readAllBytes(Paths.get(file));
        return Separate(new BencodeObject(b).toString());
    }

    static ArrayList<String> Separate(String data){
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        System.out.println(data);
        patterns.add(Pattern.compile("comment.+(http.+\u001b\\[0m)"));
        //patterns.add(Pattern.compile("comment:(.{15,80}).{1,3}created"));
        //patterns.add(Pattern.compile("length:([0-9]{4,32})"));
        //patterns.add(Pattern.compile("name:(.{10,}\\.{2,10})"));
        ArrayList<String> lst = new ArrayList<>();
        for(Pattern pattern : patterns)
        {
            Matcher matcher  =  pattern.matcher(data);
            while(matcher.find())
                lst.add(matcher.group(1));
        }

        return lst;
    }
}
