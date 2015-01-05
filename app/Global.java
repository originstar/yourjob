import java.lang.reflect.Method;

import play.*;
import play.libs.F.*;
import play.mvc.*;
import play.mvc.Http.*;
import views.html.httperror.*;


public class Global extends GlobalSettings {

    /**
     * 内部错误
     */
    @Override
    public Promise<Result> onError(RequestHeader request, Throwable t) {
        Logger.error("Internal server error.", t);
        return Promise.<Result>pure((Results.internalServerError(exception.render())));
    }

    /**
     * 找不到action
     */
    @Override
    public Promise<Result> onHandlerNotFound(RequestHeader request) {
        Logger.warn("Page not found:" + request + "\nremote address:" + request.remoteAddress());
        return Promise.<Result>pure(Results.notFound(notfound.render()));
    }

    /**
     * 找到action，但是无法绑定参数
     */
    @Override
    public Promise<Result> onBadRequest(RequestHeader request, String error) {
        Logger.warn("Bad request:" + request + "\nremote address:" + request.remoteAddress());
        return Promise.<Result>pure(Results.badRequest("Don't try to hack the URI!"));
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Action onRequest(Request request, Method method) {
        Logger.info("Request:" + request + "\naction：" + method + "\nremote address:" + request.remoteAddress());
        return super.onRequest(request, method);
    }

    @Override
    public void onStop(final Application app) {
        Logger.info("Stop server...");
    }

}
