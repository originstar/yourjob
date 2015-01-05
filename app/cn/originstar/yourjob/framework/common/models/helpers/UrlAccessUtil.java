package cn.originstar.yourjob.framework.common.models.helpers;

import cn.originstar.yourjob.framework.common.models.interfaces.UrlAccess;

public class UrlAccessUtil {

    private static IUrlAccessProvider urlAccessProvider = null;

    public static <T> String getViewUrl(UrlAccess object, Object... args) {
        return urlAccessProvider.getViewUrl(object, args);
    }

    public static <T> String getAbsoluteViewUrl(UrlAccess object, Object... args) {
        return urlAccessProvider.getViewUrl(object, args);
    }

    public static <T> String getCustomUrl(String type, Object... args) {
        return urlAccessProvider.getCustomUrl(type, args);
    }

    public static <T> String getAbsoluteCustomUrl(String type, Object... args) {
        return urlAccessProvider.getAbsoluteCustomUrl(type, args);
    }

    public static void setUrlAccessProvider(IUrlAccessProvider urlAccessProvider) {
        UrlAccessUtil.urlAccessProvider = urlAccessProvider;
    }

}
