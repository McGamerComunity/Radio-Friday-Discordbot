package listener;

import core.commandHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

import java.util.Timer;
import java.util.TimerTask;

public class ChatListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        //System.out.println(event.getTextChannel().getName() + " " + event.getMember().getUser().getName() + ": " + event.getMessage().getContentRaw());


        if (event.getMessage().getContentRaw().toLowerCase().contains("lol") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId() ) {

            event.getTextChannel().sendTyping().queue();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    event.getTextChannel().sendMessage("lel").queue();
                }
            }, 1000);

        }

        if (event.getMessage().getContentRaw().toLowerCase().contains("xd") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {

            event.getTextChannel().sendTyping().queue();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    event.getTextChannel().sendMessage("Krasss\nentweder Bernhard räumt mal wieder sein Zimmer aus \noder er holt sich wieder Duplo und schmeißt sie durch sein Zimmer").queue();
                }
            }, 7000);
        }


    }
}
