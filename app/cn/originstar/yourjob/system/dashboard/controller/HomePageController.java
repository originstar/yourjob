package cn.originstar.yourjob.system.dashboard.controller;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import cn.originstar.yourjob.system.dashboard.views.html.index;

public class HomePageController extends Controller {

    @Transactional
    public static Result index() {
        return ok(index.render());
    }
}
