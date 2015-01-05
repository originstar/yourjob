package cn.originstar.yourjob.core.utils;

import play.Play;

public class ApplicationUtil {

    /**
     * @return Whether https is on
     */
    public static boolean isSecure() {
        return Play.application().configuration().getBoolean("application.secure", false);
    }

}
