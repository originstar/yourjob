package cn.originstar.yourjob.system.permission.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import cn.originstar.yourjob.core.model.BaseModel;
import cn.originstar.yourjob.system.permission.daos.ACLPermissionDAO;
import cn.originstar.yourjob.system.permission.daos.impl.ACLPermissionDAOImpl;
import cn.originstar.yourjob.system.user.models.User;

@Entity
public class ACLPermission extends BaseModel {

    private static final long serialVersionUID = -6794632864681083816L;

    private String permissionName;

    @ManyToOne
    private User user;

    private Long objectId;

    private boolean broad;

    public static ACLPermissionDAO dao = new ACLPermissionDAOImpl();

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public boolean getBroad() {
        return broad;
    }

    public void setBroad(boolean broad) {
        this.broad = broad;
    }

}
