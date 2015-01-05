package cn.originstar.yourjob.framework.statable;

import java.util.List;

import cn.originstar.yourjob.core.db.dao.AbstractDAO;
import cn.originstar.yourjob.system.common.models.StatableModel;
import cn.originstar.yourjob.system.common.models.StatableModel.State;

public abstract class AbstractStatableDAO<T extends StatableModel> extends AbstractDAO<T, Long> implements StatableDAO<T> {

    public AbstractStatableDAO() {
        this.typeOfID = Long.class;
    }

    // ------------------------------------------------------------------------
    // StatableDAO implementation
    // ------------------------------------------------------------------------

    @Override
    public void trash(T entity) {
        changeState(entity, State.NORMAL, State.TRASHED);
    }

    @Override
    public void restore(T entity) {
        changeState(entity, State.TRASHED, State.NORMAL);
    }

    @Override
    public void purge(T entity) {
        changeState(entity, State.TRASHED, State.PURGED);
    }

    @Override
    public void recover(T entity) {
        changeState(entity, State.PURGED, State.TRASHED);
    }

    @Override
    public boolean canBeTrashed(T entity) {
        return entity.getState() == State.NORMAL;
    }

    @Override
    public boolean canBeRestored(T entity) {
        StatableModel dpo = findDirectParentObject(entity);

        if (entity.getState() != State.TRASHED && entity.getState() != State.CASCADING_TRASHED) {
            return false;
        }

        if (dpo != null && dpo.getState() != State.NORMAL) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canBePurged(T entity) {
        return entity.getState() == State.TRASHED || entity.getState() == State.CASCADING_TRASHED;
    }

    @Override
    public boolean canBeRecovered(T entity) {
        StatableModel dpo = findDirectParentObject(entity);

        if (entity.getState() != State.PURGED && entity.getState() != State.CASCADING_PURGED) {
            return false;
        }

        if (dpo != null && (dpo.getState() == State.PURGED || dpo.getState() == State.CASCADING_PURGED)) {
            return false;
        }

        return true;
    }

    /**
     * Find direct cascading objects by state
     * 
     * @param state
     * @return
     */
    public List<List<? extends StatableModel>> findDirectCascadingObjects(T entity, State state) {
        return null;
    }

    /**
     * Find direct parent object
     * 
     * @param entity
     * @return
     */
    public StatableModel findDirectParentObject(T entity) {
        return null;
    }

    // @SuppressWarnings("unchecked")
    public void changeState(T entity, State from, State to) {
        /*
        if (entity.state != from) {
            return;
        }

        entity.state = to;

        State cascadingStateFrom = getCascadingState(from);
        State cascadingStateTo = getCascadingState(to);

        List<List<? extends StatableModel>> dco = entity.getStatableDAO().findDirectCascadingObjects(entity, cascadingStateFrom);

        if (dco == null || dco.size() == 0) {
            return;
        }

        for (List<? extends StatableModel> cascadingObjects : dco) {
            if (cascadingObjects == null || cascadingObjects.size() == 0) {
                continue;
            }

            for (StatableModel cascadingObject : cascadingObjects) {
                cascadingObject.getStatableDAO().changeState(cascadingObject, cascadingStateFrom, cascadingStateTo);
            }
        }
        */
    }

    // ------------------------------------------------------------------------
    // StatableDAO implementation fundamental methods
    // ------------------------------------------------------------------------
    /*
    private State getCascadingState(State state) {
        if (state == State.TRASHED) {
            return State.CASCADING_TRASHED;
        }

        if (state == State.PURGED) {
            return State.CASCADING_PURGED;
        }

        return state;
    }
    */

}
