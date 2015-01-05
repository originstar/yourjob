package cn.originstar.yourjob.framework.multitenancy;

import play.Play;

public class InitializeDatabaseFileName {
    public static String initPreferences = "initial-preferences.yml";
    public static String initEmailTemplates = "initial-email-templates.yml";
    public static String initData = "initial-data.yml";

    static {
        if (Play.isTest()) {
            initPreferences = "test-initial-preferences.yml";
            initEmailTemplates = "test-initial-email-templates.yml";
            initData = "test-initial-data.yml";
        }
    }
}
