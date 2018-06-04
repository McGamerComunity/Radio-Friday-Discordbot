package core;

import com.sun.corba.se.impl.activation.CommandHandler;
import commands.*;
import listener.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.omg.CORBA.ORB;
import util.STATIC;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class main {

    static JDABuilder builder;
    public static final CommandHandler parser = new CommandHandler() {
        @Override
        public String getCommandName() {
            return null;
        }

        @Override
        public void printCommandHelp(PrintStream out, boolean helpType) {

        }

        @Override
        public boolean processCommand(String[] cmd, ORB orb, PrintStream out) {
            return false;
        }
    };

    public static HashMap<String, commands> commands = new HashMap<>();

    public static JDA jda;

    public static void main(String[] Args) throws InterruptedException, IOException {

/*
            final URL url = new URL("https://ragefx.de/radiofriday/bot/version.txt");
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

*/
        JDABuilder builder = new JDABuilder(AccountType.BOT);


        builder = new JDABuilder(AccountType.BOT)
                .setToken(STATIC.TOKEN)
                .setAudioEnabled(true)
                .setAutoReconnect(true)
                .setStatus(STATIC.STATUS)
                .setGame(Game.listening(STATIC.GAME));

        initializeListeners();
        initializeCommands();


        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void initializeCommands() {

        commands.put("info", new Info());
        commands.put("about", new Info());
        commands.put("music", new Music());
        commands.put("hello", new Hello());
        commands.put("gif", new Gif());
        commands.put("speedtest", new Ping());

    }

    private static void initializeListeners() {

        builder
                .addEventListener(new VoiceListener())
                .addEventListener(new CommandListener())
                .addEventListener(new privateMessageListener())
                .addEventListener(new ChatListener())
                .addEventListener(new ReadyMusicListener())

        ;


    }
}
