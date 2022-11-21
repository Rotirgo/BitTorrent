package BitTorrent;

import java.util.Random;

public interface Options {

    static String getInfoHashForRequest(String infoHash, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < infoHash.length(); ++i) {
            if(i % 2 == 0){
                builder.append(delimiter);
            }
            builder.append(infoHash.charAt(i));
        }
        return builder.toString();
    }

    static String getPeerID() {
        StringBuilder peerID = new StringBuilder("-PC0001-");
        Random rn = new Random();
        for(int i = 0; i < 12; ++i){
            peerID.append(rn.nextInt(10));
        }
        return peerID.toString();
    }

    static void addParameter(StringBuilder request, String parameter, String value) {
        if (request.indexOf("?") == -1) {
            request.append('?').append(parameter).append("=").append(value);
        } else {
            request.append("&").append(parameter).append("=").append(value);
        }
    }
}
