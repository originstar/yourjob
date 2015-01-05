package cn.originstar.yourjob.system.common.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import cn.originstar.yourjob.core.utils.StringUtil;
import cn.originstar.yourjob.framework.common.models.BaseModel;
import cn.originstar.yourjob.system.user.models.User;

@MappedSuperclass
@FilterDef(name = "statable", parameters = {
    @ParamDef(name = "state", type = "int")
}, defaultCondition = "state = :state")
@Filter(name = "statable")
public abstract class StatableModel extends BaseModel {

    public static enum State {
        NORMAL,

        TRASHED,

        CASCADING_TRASHED,

        PURGED,

        CASCADING_PURGED,

        ARCHIVE,

        CASCADING_ARCHIVE,

        TEMP
    }

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Column(nullable = false)
    private StatableModel.State state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    // Use createdById, createdByEmail, createdByUsername and createdByFullname instead of ManyToOne here in order to reduce table join

    private Long createdById;

    private String createdByEmail;

    private String createdByUsername;

    private String createdByFirstName;

    private String createdByLastName;

    private Long updatedById;

    private String updatedByEmail;

    private String updatedByUsername;

    private String updatedByFirstName;

    private String updatedByLastName;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public StatableModel.State getState() {
        return state;
    }

    public void setState(StatableModel.State state) {
        this.state = state;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public String getCreatedByFirstName() {
        return createdByFirstName;
    }

    public void setCreatedByFirstName(String createdByFirstName) {
        this.createdByFirstName = createdByFirstName;
    }

    public String getCreatedByLastName() {
        return createdByLastName;
    }

    public void setCreatedByLastName(String createdByLastName) {
        this.createdByLastName = createdByLastName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByEmail() {
        return updatedByEmail;
    }

    public void setUpdatedByEmail(String updatedByEmail) {
        this.updatedByEmail = updatedByEmail;
    }

    public String getUpdatedByUsername() {
        return updatedByUsername;
    }

    public void setUpdatedByUsername(String updatedByUsername) {
        this.updatedByUsername = updatedByUsername;
    }

    public String getUpdatedByFirstName() {
        return updatedByFirstName;
    }

    public void setUpdatedByFirstName(String updatedByFirstName) {
        this.updatedByFirstName = updatedByFirstName;
    }

    public String getUpdatedByLastName() {
        return updatedByLastName;
    }

    public void setUpdatedByLastName(String updatedByLastName) {
        this.updatedByLastName = updatedByLastName;
    }

    /**
     * Convenience method to set attributes of createdBy
     */
    public void setCreatedBy(User createdBy) {
        setCreatedById(createdBy.getId());
        setCreatedByEmail(createdBy.getEmail());
        setCreatedByUsername(createdBy.getUsername());
        setCreatedByFirstName(createdBy.getFirstName());
        setCreatedByLastName(createdBy.getLastName());
    }

    /**
     * Convenience method to set attributes of updatedBy
     */
    public void setUpdatedBy(User updatedBy) {
        setUpdatedById(updatedBy.getId());
        setUpdatedByEmail(updatedBy.getEmail());
        setUpdatedByUsername(updatedBy.getUsername());
        setUpdatedByFirstName(updatedBy.getFirstName());
        setUpdatedByLastName(updatedBy.getLastName());
        setUpdatedOn(new Date());
    }

    public String getCreatedByFullName() {
        return StringUtil.getFullName(getCreatedByUsername(), getCreatedByFirstName(), getCreatedByLastName());
    }

    public String getUpdatedByFullName() {
        return StringUtil.getFullName(getUpdatedByUsername(), getUpdatedByFirstName(), getUpdatedByLastName());
    }

    public Date getLastEditedOn() {
        return getUpdatedOn() == null ? getCreatedOn() : getUpdatedOn();
    }

    public Long getLastEditedById() {
        return getUpdatedOn() == null ? getCreatedById() : getUpdatedById();
    }

    public String getLastEditedByFirstName() {
        return getUpdatedOn() == null ? getCreatedByFirstName() : getUpdatedByFirstName();
    }

    public String getLastEditedByLastName() {
        return getUpdatedOn() == null ? getCreatedByLastName() : getUpdatedByLastName();
    }

    public String getLastEditedByFullName() {
        return getUpdatedOn() == null ? getCreatedByFullName() : getUpdatedByFullName();
    }

}
