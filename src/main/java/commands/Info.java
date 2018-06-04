package commands;

import core.UpdateClient;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.STATIC;

import java.awt.*;

public class Info implements commands {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        String version = "NOT AVAILABLE";
        try {

            version = UpdateClient.PRE.tag;

        } catch (Exception e) {
            e.printStackTrace();
        }

        event.getTextChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(Color.MAGENTA)
                        .setThumbnail("https://ragefx.de/radiofriday/bot/img.png")
                        .setDescription(":robot:   __**RadioFriday** JDA Discord Bot__")
                        .addField("Current Version", STATIC.VERSION, true)
                        .addField("Latest Version", version, true)
                        .addField("Copyright",
                                "Coded by McGamer_Comunity | Tim.\n" +
                                        "© 2018 McGamer_Comunity", false)
                        .addField("Information and Links",
                                "GitHub Repository: \n*bot source is private*\n\n" +
                                        "Readme/Changelogs: \n*bot source is private*\n\n" +
                                        "Webpage: \n*https://ragefx.de/*\n\n" +
                                        "Github Profile: \n*https://github.com/McGamerComunity/*", false)
                        .addField("Libraries and Dependencies",
                                " -  JDA  *(https://github.com/DV8FromTheWorld/JDA)*\n" +
                                        " -  Toml4J  *(https://github.com/mwanji/toml4j)*\n" +
                                        " -  lavaplayer  *(https://github.com/sedmelluq/lavaplayer)*\n" +
                                        " -  Steam-Condenser  *(https://github.com/koraktor/steam-condenser-java)*", false)
                        .addField("Bug Reporting / Idea Suggestion",
                                "If you got some bugs, please contact us here:\n" +
                                        " - **Please use this document to report bugs: https://bugs.ragefx.de/**\n" +
                                        " -  E-Mail:  mcgamer@ragefx.de\n" +
                                        " -  Discord:  http://discord.zekro.de (or directly: `\uD83D\uDD30 McGamer_Comunity \uD83D\uDD30 [RF]#8073`)", false)

                        .build()

        ).queue();
    }

    @Override
    public void executet(boolean sucess, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "USAGE: -info";
    }

    @Override
    public String description() {
        return "Get info about that bot";
    }

    @Override
    public boolean commandType() {
        return false;
    }

    @Override
    public int permission() {
        return 0;
    }
}
