package impl;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Maksym_Moroz1 on 9/14/2017.
 */
public class Runner {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try (InputStream in = url.openStream();
             JsonReader resJsn = Json.createReader(in)) {
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
