package cn.originstar.yourjob.system.user.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import cn.originstar.yourjob.base.statable.StatableModel;
import cn.originstar.yourjob.system.user.daos.UserSocialDAO;
import cn.originstar.yourjob.system.user.daos.impl.UserSocialDAOImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User social accounts
 * 
 */
@Entity
public class UserSocial extends StatableModel {

    private static final long serialVersionUID = 856310829229781344L;

    @Required
    private String type;

    @MaxLength(100)
    private String account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    /**
     * Generic DAO for User model
     */
    @Deprecated
    public static UserSocialDAO dao = new UserSocialDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
