package com.github.l3nnartt;

import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;

public class MessageReceiveListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String clean, String formatted) {

        if (LabyMod.getInstance().getPlayerName().equals("TimoliaBar")) {

            if (Barkeeper.getInstance().isEnabledBarkeeper()) {

                if (clean.toLowerCase().contains("@TimoliaBar".toLowerCase())) {

                    if (containsIgnoreCase(clean, "Mathe:")) {

                        String onlyMessage = getOnlyMessage(clean).replace("Mathe:", "").replace("@TimoliaBar", "").replaceAll("\\s","");
                        CalculationHandler calculationHandler = new CalculationHandler();
                        CalculationHandler.CalculationResult result = calculationHandler.calculate(onlyMessage);
                        if (result.isError()) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage("Bei der Rechnung ist irgendwas in meinem Kopf kaputt gegangen, bitte versuche es noch einmal oder schreibe L3nnart_");
                        } else {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage(onlyMessage + " = " + result.getValue());
                        }

                    } else {

                        boolean found = false;
                        for (DataContainer data : Barkeeper.getInstance().getConfigHandler().getQestions()) {
                            if (containsIgnoreCase(clean, data.getContains())) {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage(data.getAnswer().replace("%user%", getNameFromMessage(clean)));
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            Minecraft.getMinecraft().thePlayer.sendChatMessage(Barkeeper.getInstance().getConfigHandler().getConfig().getAsJsonObject().get("notFound").getAsString());
                        }

                    }

                }

            }

        } return false;

    }

    public boolean containsIgnoreCase(String string, String contains){
        return string.toLowerCase().contains(contains.toLowerCase());
    }

    public String getNameFromMessage(String msg){
        return ModColor.removeColor(msg.split("§r§7:")[0]);
    }

    public String getOnlyMessage(String msg){
        return ModColor.removeColor(msg.split("§r§7:")[1]);
    }

}