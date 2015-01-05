package cn.originstar.yourjob.framework.common.models.helpers;

import cn.originstar.yourjob.framework.common.models.interfaces.UrlAccess;

public interface IUrlAccessProvider {

    String getViewUrl(UrlAccess object, Object... args);

    String getAbsoluteViewUrl(UrlAccess object, Object... args);

    String getCustomUrl(String type, Object... args);

    String getAbsoluteCustomUrl(String type, Object... args);

}
