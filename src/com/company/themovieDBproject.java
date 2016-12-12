package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 46406163y on 12/12/16.
 */
public class themovieDBproject {


    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "";
        String api_key = "";

        for (int i = 0; i < 40; i++) {
            int peli = 600 + i;
            String film = String.valueOf(peli);
            String peticio = "https://api.themoviedb.org/3/movie/" + film + "?api_key=" + api_key;
            try {
                s = getHTML(peticio);
                SJS(s);
            } catch (Exception e) {
                System.out.println("La peli " + film + " no existeix");
            }
        }


    }

    public static void SJS(String cadena) {

        Object obj02 = JSONValue.parse(cadena);
        JSONObject arra02 = (JSONObject) obj02;
        System.out.println(arra02.get("original_title"));
        System.out.println(arra02.get("release_date"));

        insertSQLite.listaMovies(arra02.get("original_title").toString(), Integer.parseInt(arra02.get("id").toString()), arra02.get("release_date").toString());

    }

    public static void SJC(String cadena) {
        Object obj02 = JSONValue.parse(cadena);
        JSONObject arra02 = (JSONObject) obj02;
        JSONArray arra03 = (JSONArray) arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb = (JSONObject) arra03.get(i);
            System.out.println(jb.get("character") + "<-->" + jb.get("name"));

        }


    }
}