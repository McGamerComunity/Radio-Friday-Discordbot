package listener;

import audioCore.AudioInfo;
import audioCore.PlayerSendHandler;
import audioCore.StartTrackManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import util.STATIC;

import java.awt.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ReadyMusicListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {

        String input = STATIC.RFLINK;

        for (Guild g : event.getJDA().getGuilds() ) {
            VoiceChannel vChan = event.getJDA().getVoiceChannelByName("Radio Friday", true).get(0);
            g.getTextChannelsByName("bot", true).get(0).sendMessage("rf music play " + input).queue();
            g.getAudioManager().openAudioConnection(vChan);
        }

    }

}
