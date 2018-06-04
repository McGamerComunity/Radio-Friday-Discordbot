package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;


public interface commands {
    boolean called(String[] args, MessageReceivedEvent event);
    void action(String[] args, MessageReceivedEvent event) throws IOException, ParseException;
    void executet(boolean sucess, MessageReceivedEvent event);

    void executed(boolean success, MessageReceivedEvent event);

    String help();

    String description();

    boolean commandType();

    int permission();
}
