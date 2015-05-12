package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zieghailo on 5/4/15.
 */
public class RESTUtility {

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

    public static String map2Json(Map map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String object2Json(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public static void flushJson(HttpServletResponse response, Map data) {
        response.setContentType("application/json");
        try {
            String json = map2Json(data);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void flushJson(HttpServletResponse response, Object resource) {
        response.setContentType("application/json");
        try {
            String json = object2Json(resource);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Takes the residual url, removes the starting slash,
     * and either returns the following id, or 0.
     * Zero means the user didn't ask for a specific resource, but a list.
     * @param restURL - the url specified by the asterisk in the servlet mapping.
     * @return interger for a resource id, zero for the resource list.
     */
    public static int parseURL(String restURL) throws IllegalArgumentException {
        matcher = regexIDPattern.matcher(restURL);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            return id;
        }
        System.out.println(restURL+".");
        if (restURL.equals("/") || restURL.equals("")) {
            System.out.println("All cats");
            return 0;
        }
        else {
            return 0;
        }

//        throw (new IllegalArgumentException("The passed URL is mismatched. "));
    }

    private static Matcher matcher;
    private static Pattern regexIDPattern = Pattern.compile("/([0-9]+)");
    private static ObjectMapper mapper = new ObjectMapper();
}
