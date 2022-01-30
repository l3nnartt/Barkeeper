package com.github.l3nnartt;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConfigHandler {

    private JsonElement config;
    private ArrayList<DataContainer> qestions;

    public ConfigHandler() {
        qestions = new ArrayList<>();
        fetch();
    }

    public JsonElement getURLContent(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.setConnectTimeout(5000);
        con.connect();
        String jsonString = IOUtils.toString(con.getInputStream(), "UTF-8");
        JsonParser parser = new JsonParser();
        return  parser.parse(jsonString);
    }

    public void fetch() {
        try {
            JsonObject content = getURLContent("https://dl.lennartloesche.de/barkeeper/questions.json").getAsJsonObject();
            config = content.get("config").getAsJsonObject();
            for (JsonElement jsonElement : content.get("questions").getAsJsonArray()) {
                qestions.add(new DataContainer(jsonElement.getAsJsonObject().get("contains").getAsString(), jsonElement.getAsJsonObject().get("answer").getAsString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataContainer> getQestions() {
        return qestions;
    }

    public JsonElement getConfig() {
        return config;
    }
}
