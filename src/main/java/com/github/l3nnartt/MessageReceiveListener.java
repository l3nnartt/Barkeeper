package com.github.l3nnartt;

import net.labymod.api.events.MessageReceiveEvent;
import net.minecraft.client.Minecraft;

public class MessageReceiveListener implements MessageReceiveEvent {
    @Override
    public boolean onReceive(String clean, String formatted) {
        if (Barkeeper.getInstance().isEnabledBarkeeper()) {

            if (clean.toLowerCase().contains("@TimoliaBar".toLowerCase())) {

                if (containsIgnoreCase(clean, "Hallo")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Guten Tag!");
                }

                if (containsIgnoreCase(clean, "Wie gehts")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Mir geht es super, wie geht es Ihnen?");
                }

                if (containsIgnoreCase(clean, "Wie geht es Ihnen")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Mir geht es super, wie geht es Ihnen?");
                }

                if (containsIgnoreCase(clean, "Ich möchte gerne etwas bestellen")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Ja?");
                }

                if (containsIgnoreCase(clean, "Gibt es hier Alkohol")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Nein, wir verkaufen ausschließlich alkoholfreie Getränke.");
                }

                if (containsIgnoreCase(clean, "Ein Bier")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Bitteschön, eine Flasche Bier für Sie");
                }

                if (containsIgnoreCase(clean, "Habt ihr Cocktails")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Ja, wir bieten aber ausschließlich alkoholfreie Cocktails an.");
                }

                if (containsIgnoreCase(clean, "Ein Radler bitte")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Du hast jetzt Hausverbot! (FlauschigesBiest tötet mich sonst)");
                }

                if (containsIgnoreCase(clean, "Ein Wasser bitte")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Bitteschön, eine Flasche Wasser für Sie.");
                }

                if (containsIgnoreCase(clean, "Gibt es hier Wasser")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Wasser? Warum gehen Sie in eine Bar wenn Sie Wasser trinken wollen?");
                }

                if (containsIgnoreCase(clean, "Wer bist du")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Wer ich bin? Das ist eine gute Frage.... ja, sehr gute Frage...");
                }

                if (containsIgnoreCase(clean, "Eine alkoholfreie Runde Mineception")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Bitte schreibe dafür L3nnart_ eine MSG");
                }

                if (containsIgnoreCase(clean, "Milch")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Milch ist Gift");
                }

                if (containsIgnoreCase(clean, "Haben Sie reines Ethanol")) {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("Nein, so etwas haben wir nicht in unserem Sortiment.");
                }

            }
        } return false;
    }

    public boolean containsIgnoreCase(String string, String contains){
        return string.toLowerCase().contains(contains.toLowerCase());
    }

}