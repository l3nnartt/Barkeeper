package com.github.l3nnartt;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.HeaderElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;
import java.util.List;

public class Barkeeper extends LabyModAddon {

    private static Barkeeper instance;
    private boolean enabledBarkeeper;

    @Override
    public void onEnable() {
        instance = this;
        api.getEventManager().register(new MessageReceiveListener());
        System.out.println("Barkeeper loaded");
    }

    @Override
    public void loadConfig() {
        this.enabledBarkeeper = !getConfig().has("enabledBarkeeper") || getConfig().get("enabledBarkeeper").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new HeaderElement("Allgemein"));
        list.add(new BooleanElement("BarKeeper", this, new ControlElement.IconData(Material.LEVER), "enabledBarkeeper", this.enabledBarkeeper));
    }

    public static Barkeeper getInstance() {
        return instance;
    }

    public boolean isEnabledBarkeeper() {
        return enabledBarkeeper;
    }
}