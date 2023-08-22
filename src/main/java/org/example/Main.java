package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String weatherString = getWeather();
        System.out.println(weatherString);
    }

    public static String getWeather() {
        String url = "https://www.aviationweather.gov/metar/data?ids=KLAF&format=decoded&hours=0&taf=on&layout=on";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element table = doc.select("table").first();
        Elements rows = table.select("tr");
        String res = "Purdue University Weather \n";
        for (Element tr : rows) {
            String currRow = tr.text();
            if (!currRow.contains("METAR") && !currRow.contains("Text")) {
                res += tr.text() + "\n";
            }
        }
        return res;
    }
}