package cn.originstar.yourjob.framework.statable;

import java.util.List;

import cn.originstar.yourjob.core.db.dao.DAO;
import cn.originstar.yourjob.system.common.models.StatableModel;
import cn.originstar.yourjob.system.common.models.StatableModel.State;

public interface StatableDAO<T extends StatableModel> extends DAO<T, Long> {

    void trash(T entity);

    void restore(T entity);

    void purge(T entity);

    void recover(T entity);

    boolean canBeTrashed(T entity);

    boolean canBeRestored(T entity);

    boolean canBePurged(T entity);

    boolean canBeRecovered(T entity);

    void changeState(T entity, State from, State to);

    /**
     * Find direct cascading objects by state
     * 
     * @param state
     * @return
     */
    List<List<? extends StatableModel>> findDirectCascadingObjects(T entity, State state);

    /**
     * Find direct parent object
     * 
     * @param entity
     * @return
     */
    StatableModel findDirectParentObject(T entity);

}
