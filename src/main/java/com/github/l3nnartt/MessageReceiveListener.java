package com.github.l3nnartt;

import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;

public class MessageReceiveListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String clean, String formatted) {
        if (LabyMod.getInstance().getPlayerName().equals("TimoliaBar")) {
            if (Barkeeper.getInstance().isEnabledBarkeeper()) {

                if (clean.toLowerCase().contains("@TimoliaBar".toLowerCase())) {
                    boolean found = false;
                    for (DataContainer data : Barkeeper.getInstance().getConfigHandler().getQestions()) {
                        if (containsIgnoreCase(clean, data.getContains())) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage(data.getAnswer());
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        Minecraft.getMinecraft().thePlayer.sendChatMessage(Barkeeper.getInstance().getConfigHandler().getConfig().getAsJsonObject().get("notFound").getAsString());
                    }

                }

            }
        } return false;
    }

    public boolean containsIgnoreCase(String string, String contains){
        return string.toLowerCase().contains(contains.toLowerCase());
    }

}