package cn.originstar.yourjob.core.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    /**
     * Produces user's full name, username will be ignored if first name or last name is provided.
     * 
     * @param username User's username, which is the login name
     * @param firstName User's first name
     * @param lastName User's last name
     * @return User's full name
     */
    public static String getFullName(String username, String firstName, String lastName) {
        String fullname = firstName + " " + lastName;
        if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
            return username;
        } else {
            return fullname;
        }
    }

}
