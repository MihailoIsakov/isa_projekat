package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by zieghailo on 5/4/15.
 */
public class JsonUtility {

    public static String pullDataFromRequest(HttpServletRequest request) {
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();
        String line;
        try {
            reader = request.getReader();
            while ((line = reader.readLine()) != null)
                buffer.append(line);
            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
