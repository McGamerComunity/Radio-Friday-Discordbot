package listener;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;
import core.*;


public class CommandListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        //System.out.println(event.getTextChannel().getName() + " " + event.getMember().getUser().getName() + ": " + event.getMessage().getContentRaw());

        if (event.getMessage().getContentRaw().toLowerCase().startsWith(STATIC.PREFIX)) {
            commandHandler.handleCommand(commandHandler.parse.parse(event.getMessage().getContentRaw(), event));
            System.out.println("[MESSAGE] " + event.getTextChannel().getName() + " " + event.getMember().getUser().getName() + ": " + event.getMessage().getContentRaw());
            event.getMessage().delete().queue();

        }

    }

}
