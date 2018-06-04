package commands;



import net.dv8tion.jda.core.entities.Message;

import net.dv8tion.jda.core.entities.TextChannel;

import net.dv8tion.jda.core.entities.User;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import org.brunocvcunha.jiphy.Jiphy;

import org.brunocvcunha.jiphy.JiphyConstants;

import org.brunocvcunha.jiphy.requests.JiphySearchRequest;

import util.MSGS;
import util.STATIC;


import java.io.IOException;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Timer;

import java.util.TimerTask;

import java.util.stream.Collectors;





public class Gif implements commands {









    @Override

    public boolean called(String[] args, MessageReceivedEvent event) {

        return false;

    }



    @Override

    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {



        TextChannel tc = event.getTextChannel();

        User author = event.getAuthor();



        if (args.length < 1) {

            tc.sendMessage(MSGS.error().setDescription(help()).build()).queue();

            return;

        }



        String query = Arrays.stream(args).filter(s -> !s.startsWith("-")).collect(Collectors.joining("-"));

        int index = args[args.length - 1].startsWith("-") ? Integer.parseInt(args[args.length - 1].substring(1)) - 1 : 0;



        Message msg = event.getTextChannel().sendMessage("sammle daten...").complete();

        event.getMessage().delete().queue();



        Jiphy jiphy = Jiphy.builder()

                .apiKey(JiphyConstants.API_KEY_BETA)

                .build();



        ArrayList<String> gifs = new ArrayList<>();

        jiphy.sendRequest(new JiphySearchRequest(query)).getData().forEach(g ->

                gifs.add(g.getUrl())

        );



        if (gifs.size() == 0) {

            msg.delete().queue();

            tc.sendMessage(MSGS.error().setDescription("keine gifs gefunden für: `" + query + "`!").build()).queue(m ->

                    new Timer().schedule(new TimerTask() {

                        @Override

                        public void run() {

                            m.delete().queue();

                        }

                    }, 4000)

            );

            return;

        }

        else if (gifs.size() < index)

            index = gifs.size() - 1;





        msg.editMessage(

                String.format("[%s]\n", author.getName()) +

                        gifs.get(index)

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

        return "**USAGE:\n**" +

                "`" + STATIC.PREFIX + "gif <search query> (<-index>)`\n" +

                "*The `-` in front of the optional index numer ist really essential!*";

    }



    @Override

    public String description() {

        return "Send a gif in the chat!";

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