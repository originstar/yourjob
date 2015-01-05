package cn.originstar.yourjob.framework.statable;

import org.hibernate.Session;

import cn.originstar.yourjob.core.db.jpa.JPA;
import cn.originstar.yourjob.system.common.models.StatableModel.State;

/**
 * Helper class for StatableModel operations
 * 
 * @author Yonggang Yuan
 */
public class StatableHelper {

    public static void enableStatbleFilter() {
        enableStatbleFilter(State.NORMAL);
    }

    public static void enableStatbleFilter(State state) {
        ((Session) JPA.em().getDelegate()).enableFilter("statable").setParameter("state", state.ordinal());
    }

    public static void disableStatbleFilter() {
        ((Session) JPA.em().getDelegate()).disableFilter("statable");
    }

}
