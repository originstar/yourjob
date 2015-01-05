package cn.originstar.yourjob.core.db.jpa;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Result;
import play.mvc.Http;

public class TransactionalAction extends Action<Transactional> {

    public Promise<Result> call(final Http.Context ctx) throws Throwable {
        Logger.debug("TransactionalAction.call");

        TransactionalHelper.enableTransaction(configuration.value(), configuration.readOnly());

        return delegate.call(ctx);
    }

}
