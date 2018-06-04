package audioCore;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


public class StartTrackManager extends AudioEventAdapter {

    private final AudioPlayer PLAYER;
    private final Queue<AudioInfo> queue;

    public StartTrackManager(AudioPlayer player) {
        this.PLAYER = player;
        this.queue = new LinkedBlockingQueue<>();
    }



    public Set<AudioInfo> getQueue() {
        return new LinkedHashSet<>(queue);
    }


    public AudioInfo getInfo(AudioTrack track) {
        return queue.stream()
                .filter(info -> info.getTrack().equals(track))
                .findFirst().orElse(null);
    }

    public void purgeQueue() {
        queue.clear();
    }


    public void shuffleQueue() {
        List<AudioInfo> cQueue = new ArrayList<>(getQueue());
        AudioInfo current = cQueue.get(0);
        cQueue.remove(0);
        Collections.shuffle(cQueue);
        cQueue.add(0, current);
        purgeQueue();
        queue.addAll(cQueue);
    }


    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        AudioInfo info = queue.element();
        Guild g = null;
        VoiceChannel vChan = g.getJDA().getVoiceChannelByName("Radio Friday", false).get(0);

        if (vChan == null)
            player.stopTrack();
        else
            info.getAuthor().getGuild().getAudioManager().openAudioConnection(vChan);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        Guild g = queue.poll().getAuthor().getGuild();

        if (queue.isEmpty())
            g.getAudioManager().closeAudioConnection();
        else
            player.playTrack(queue.element().getTrack());
    }

}
