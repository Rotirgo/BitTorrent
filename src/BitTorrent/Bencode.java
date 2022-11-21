package BitTorrent;

import unet.bencode.Bencoder;
import unet.bencode.variables.BencodeBytes;
import unet.bencode.variables.BencodeObject;
import unet.bencode.variables.BencodeVariable;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    static Map<String, String> getData(String bencode){
        Pattern format = Pattern.compile("d\\d+");
        System.out.println(format);
        return null;
    }
}
