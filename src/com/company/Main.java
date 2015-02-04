package com.company;

import org.json.*;

public class Main {

    public static String URL_USUARIO = "https://api.github.com/users/arkadoel";
    public static String URL_REPOS = "";


    public static void main(String[] args) {
        URL_REPOS = URL_USUARIO + "/repos";
        String jsonSTR= jsonGithub.getJSONText(URL_REPOS);

        /*
        github suelta un jsonArray por tanto cogemos el texto json a saco y despues
        lo convertimos a array para ir accediendo a los elementos luego.
         */
        //System.out.println(jsonSTR.toString());

        JSONArray jArray = null;
        try {

            jArray = (JSONArray) new JSONTokener(jsonSTR).nextValue();

            // una vez tenemos el arra hay que recorrerlo
            //int elementos = jArray.length();
            int i = 0;
            //for( i = 0; i< elementos; i++) {
                JSONObject jObject = jArray.getJSONObject(i);

                System.out.println("Nombre del repositorio: " + jObject.get("name"));
                System.out.println("\turl: " + jObject.get("url"));
                System.out.println("\tLenguaje: " + jObject.get("language"));
                String commits_url = jObject.get("commits_url").toString().replace("{/sha}", "");
                System.out.println("\turl commits: " + commits_url);

                getCommitsInfo(commits_url);

                System.out.println("\r\n");
            //}

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void getCommitsInfo(String url){
        String jsonText = jsonGithub.getJSONText(url);
        try {
            JSONArray jArray = (JSONArray) new JSONTokener(jsonText).nextValue();
            for(int i=0; i< jArray.length(); i++) {
                JSONObject jobject = jArray.getJSONObject(i);
                JSONObject jcommit = jobject.getJSONObject("commit");
                //jcommit.toString(2) mostrar formateado
                //System.out.println(jcommit.toString(2));
                System.out.println(jcommit.get("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
