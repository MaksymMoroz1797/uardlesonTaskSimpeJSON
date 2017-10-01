package impl;

import javax.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym_Moroz1 on 9/14/2017.
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        final String fileInputName = "fileInput.txt";
        JsonReader jsonReader = Json.createReader(new InputStreamReader(new BufferedInputStream(
                new FileInputStream(fileInputName)), "cp1251"));
        JsonArray jsonArray = jsonReader.readArray();
        List<ActionData> exchangeData = new ArrayList<>();
        final String r030 = "r030";
        final String txt = "txt";
        final String rate = "rate";
        final String cc = "cc";
        final String exchangedate = "exchangedate";

        for (JsonObject object : jsonArray.getValuesAs(JsonObject.class)) {
            ActionData actionData = new ActionData();
            actionData.setR030(object.getInt(r030));
            actionData.setTxt(object.getString(txt));
            actionData.setRate(object.getJsonNumber(rate).doubleValue());
            actionData.setCc(object.getString(cc));
            actionData.setExchangedate(object.getString(exchangedate));
            exchangeData.add(actionData);
        }

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        final double lim = 10;
        for (ActionData tempActiondata : exchangeData) {
            if (tempActiondata.getRate() > lim) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add(r030, tempActiondata.getR030());
                objectBuilder.add(txt, tempActiondata.getTxt());
                objectBuilder.add(rate, tempActiondata.getRate());
                objectBuilder.add(cc, tempActiondata.getCc());
                objectBuilder.add(exchangedate, tempActiondata.getExchangedate());
                jsonArrayBuilder.add(objectBuilder.build());
            }
        }
        //System.out.println(jsonArrayBuilder.build().toString());
        final String fileOutputName = "C:/Users/Moroz/Documents/GitHub/uardlesonTaskSimpeJSON/fileOutput.txt";
        PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileOutputName)));
        printWriter.write(jsonArrayBuilder.build().toString());
        printWriter.flush();

    }
}