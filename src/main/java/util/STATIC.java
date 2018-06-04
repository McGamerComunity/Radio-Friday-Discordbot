package util;

import net.dv8tion.jda.core.OnlineStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class STATIC {

    public static String PREFIX = "rf ";

    public static String VERSION = "1.0";
    public static final String TOKEN = "NDUxNzg5MzI4MDA4MzQ3NjQ4.DfG85Q.BwJH2Ax2ELdL8prsmdtGc78yqpc";
    public static String CUSTOM_MESSAGE = "By McGamer";
    public static int BOT_OWNER_ID = 0;

    public static String GAME = PREFIX + "help | v." + VERSION + " | " + CUSTOM_MESSAGE;

    public static OnlineStatus STATUS = OnlineStatus.ONLINE;

    public static String RFLINK = "http://server4.streamserver24.com:21396/stream.opus";

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        return df.format(date.getTime());
    }

}
