package cn.originstar.yourjob.system.user.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import cn.originstar.yourjob.core.model.BaseModel;
import cn.originstar.yourjob.system.user.daos.UserPrivateNoteDAO;
import cn.originstar.yourjob.system.user.daos.impl.UserPrivateNoteDAOImpl;

@Entity
public class UserPrivateNote extends BaseModel {

    private static final long serialVersionUID = -2815624155711792527L;

    @ManyToOne(fetch = FetchType.LAZY)
    private User target;

    /**
     * Generic DAO for User model
     */
    @Deprecated
    public static UserPrivateNoteDAO dao = new UserPrivateNoteDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

}
