package BitTorrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionTracker {

    public static void Connect(String request) throws IOException {
        URLConnection urlConnection = null;
        URL url = null;
        InputStreamReader isR = null;
        BufferedReader bfR = null;


        try{
            url = new URL(request.toString());
            urlConnection = url.openConnection();
            isR = new InputStreamReader(urlConnection.getInputStream());
            bfR = new BufferedReader(isR);
            String line;

            while((line = bfR.readLine()) != null) {
                System.out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert isR != null;
            isR.close();
            assert bfR != null;
            bfR.close();
        }

    }
}
