package core;
import commands.commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class commandHandler {
    public static final commandParser parse = new commandParser();
    public static HashMap<String, commands> commands = new HashMap<>();

    public static void handleCommand(commandParser.commandContainer cmd) {

        if(commands.containsKey(cmd.invoke)) {

            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if (!safe) {
                try {
                    commands.get(cmd.invoke).action(cmd.args, cmd.event);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                commands.get(cmd.invoke).executet(safe, cmd.event);
            } else {
                commands.get(cmd.invoke).executet(safe, cmd.event);
            }

        } else {



        }

    }
}
