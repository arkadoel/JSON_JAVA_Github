package com.company;


import org.json.*;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

/**
 * Created by fer on 3/02/15.
 */
public class jsonGithub {

    private String urlGitHubUser;
    public JSONObject json;

    public jsonGithub(){


    }


    public static String readAll(Reader rd) {
        StringBuilder sb = new StringBuilder();
        int cp;
        try {
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getJSONText(String url) {
        InputStream is = null;

        String jsonText = "";

        try {
            is = new URL(url).openStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            jsonText = readAll(rd);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException ex){}
        }
        return jsonText;
    }

    public static String jsonView(String json){
        String res;
        res = json.replaceAll("\\{", "\\{\r\n");
        res = json.replaceAll("\\}", "\\}\r\n");
        return res;

    }


    public static JSONObject readJsonObjectFromUrl(String url) {
        JSONObject json = null;
        try{
            String jsonText = getJSONText(url);

            json = new JSONObject(jsonText);
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return json;

    }


}
