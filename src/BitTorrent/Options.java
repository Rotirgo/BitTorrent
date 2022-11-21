package BitTorrent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public interface Options {

    static String toSHA1(byte[] convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        return new String(md.digest(convertme));
    }

    static String getPeerID() {
        StringBuilder peerID = new StringBuilder("-PC0001-");
        Random rn = new Random();
        for(int i = 0; i < 12; ++i){
            peerID.append(rn.nextInt(10));
        }
        return peerID.toString();
    }
}
