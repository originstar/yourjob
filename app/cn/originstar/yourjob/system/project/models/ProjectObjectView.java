package cn.originstar.yourjob.system.project.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.originstar.yourjob.framework.common.models.BaseModel;
import cn.originstar.yourjob.system.project.daos.ProjectObjectViewDAO;
import cn.originstar.yourjob.system.project.daos.impl.ProjectObjectViewDAOImpl;
import cn.originstar.yourjob.system.user.models.User;

@Entity
public class ProjectObjectView extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProjectObject subject;

    /**
     * Generic DAO for ProjectTask model
     */
    @JsonIgnore
    public static ProjectObjectViewDAO dao = new ProjectObjectViewDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProjectObject getSubject() {
        return subject;
    }

    public void setSubject(ProjectObject subject) {
        this.subject = subject;
    }

}
