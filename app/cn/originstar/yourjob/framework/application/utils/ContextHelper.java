package cn.originstar.yourjob.framework.application.utils;

import play.mvc.Http.Context;
import cn.originstar.yourjob.system.user.models.User;


public class ContextHelper {

    public static final String CTX_KEY_LOGGED_USER = "_logged_user";

    /**
     * Get logger user from current HTTP context
     */
    public static User getLoggedUser() {
        return (User) Context.current().args.get(CTX_KEY_LOGGED_USER);
    }

    /**
     * Put logger user to current HTTP context
     * 
     * @param user
     */
    public static void setLoggedUser(User user) {
        Context.current().args.put(CTX_KEY_LOGGED_USER, user);
    }

}
