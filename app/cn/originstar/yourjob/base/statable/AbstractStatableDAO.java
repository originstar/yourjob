package cn.originstar.yourjob.base.statable;

import cn.originstar.yourjob.core.dao.AbstractDAO;

public abstract class AbstractStatableDAO<T extends StatableModel> extends AbstractDAO<T, Long> implements StatableDAO<T> {

    public AbstractStatableDAO() {
        this.typeOfID = Long.class;
    }

}
