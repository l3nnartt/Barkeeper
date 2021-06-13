package com.github.l3nnartt;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Barkeeper extends LabyModAddon {

    private static Barkeeper instance;
    private boolean enabledBarkeeper;
    private ArrayList<DataContainer> qestions;

    @Override
    public void onEnable() {
        instance = this;
        api.getEventManager().register(new MessageReceiveListener());
        System.out.println("Barkeeper loaded");

        qestions = new ArrayList<>();
        fetch();
    }

    @Override
    public void loadConfig() {
        this.enabledBarkeeper = !getConfig().has("enabledBarkeeper") || getConfig().get("enabledBarkeeper").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new HeaderElement("Allgemein"));
        list.add(new BooleanElement("BarKeeper", this, new ControlElement.IconData(Material.LEVER), "enabledBarkeeper", this.enabledBarkeeper));
        list.add(new UpdateButton("Update Questions", ()->{
            qestions.clear();
            fetch();
        }));
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
            for (JsonElement jsonElement : getURLContent("http://hosting151773.a2e37.netcup.net/lennart/timolia/addon/questions.json").getAsJsonArray()) {
                qestions.add(new DataContainer(jsonElement.getAsJsonObject().get("contains").getAsString(), jsonElement.getAsJsonObject().get("answer").getAsString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Barkeeper getInstance() {
        return instance;
    }

    public boolean isEnabledBarkeeper() {
        return enabledBarkeeper;
    }

    public ArrayList<DataContainer> getQestions() {
        return qestions;
    }
}