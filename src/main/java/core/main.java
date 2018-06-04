package core;

import commands.*;
import listener.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import util.STATIC;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class main {

    public static void main(String[] Args) throws InterruptedException, IOException {


            final URL url = new URL("https://ragefx.de/");
            final URLConnection conn = url.openConnection();
            final InputStream is = new BufferedInputStream(conn.getInputStream());
            final OutputStream os =
                    new BufferedOutputStream(new FileOutputStream("version.txt"));
            byte[] chunk = new byte[1024];
            int chunkSize;
            while ((chunkSize = is.read(chunk)) != -1) {
                os.write(chunk, 0, chunkSize);
            }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JDABuilder builder = new JDABuilder(AccountType.BOT);


        builder = new JDABuilder(AccountType.BOT)
                .setToken(STATIC.TOKEN)
                .setAudioEnabled(true)
                .setAutoReconnect(true)
                .setStatus(STATIC.STATUS)
                .setGame(Game.listening(STATIC.GAME));

        builder.addEventListener(new CommandListener());
        builder.addEventListener(new ChatListener());
        builder.addEventListener(new ReadyMusicListener());
        builder.addEventListener(new VoiceListener());
        builder.addEventListener(new privateMessageListener());

        commandHandler.commands.put("ping", new Ping());
        commandHandler.commands.put("hello", new Hello());
        commandHandler.commands.put("gif",new  Gif());
        commandHandler.commands.put("music", new Music());
        commandHandler.commands.put("info", new Info());
        commandHandler.commands.put("about", new Info());


        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
