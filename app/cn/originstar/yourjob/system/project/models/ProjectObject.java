package cn.originstar.yourjob.system.project.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import cn.originstar.yourjob.core.json.VersionSerializer;
import cn.originstar.yourjob.system.common.models.StatableModel;
import cn.originstar.yourjob.system.user.models.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProjectObject extends StatableModel {

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonSerialize(using = VersionSerializer.class)
    private BigDecimal version;

    private boolean isCurrentVersion;

    @org.hibernate.annotations.Type(type = "text")
    private String description;

    private Integer position;

    private Integer commentsCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private User lastCommentedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCommentedOn;

    @Transient
    private String lastCommentInfo;

    @ManyToOne
    @JsonBackReference
    private ProjectObject parent;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public boolean isCurrentVersion() {
        return isCurrentVersion;
    }

    public void setCurrentVersion(boolean isCurrentVersion) {
        this.isCurrentVersion = isCurrentVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public User getLastCommentedBy() {
        return lastCommentedBy;
    }

    public void setLastCommentedBy(User lastCommentedBy) {
        this.lastCommentedBy = lastCommentedBy;
    }

    public Date getLastCommentedOn() {
        return lastCommentedOn;
    }

    public void setLastCommentedOn(Date lastCommentedOn) {
        this.lastCommentedOn = lastCommentedOn;
    }

    public String getLastCommentInfo() {
        return lastCommentInfo;
    }

    public void setLastCommentInfo(String lastCommentInfo) {
        this.lastCommentInfo = lastCommentInfo;
    }

    public ProjectObject getParent() {
        return parent;
    }

    public void setParent(ProjectObject parent) {
        this.parent = parent;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    @JsonIgnore
    public boolean isViewed(User user) {
        return ProjectObjectView.dao.findBySubjectAndUser(this, user).size() > 0;
    }

}
