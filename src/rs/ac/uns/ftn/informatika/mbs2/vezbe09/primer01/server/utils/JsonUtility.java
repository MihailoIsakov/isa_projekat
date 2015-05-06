package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

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

    public static Map<String, Object> json2Map(String json) {
        try {
            Map<String, Object> map = mapper.readValue(json, Map.class);
            return map;
        } catch (JsonParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper mapper = new ObjectMapper();
}
