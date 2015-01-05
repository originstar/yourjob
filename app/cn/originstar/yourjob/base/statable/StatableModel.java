package cn.originstar.yourjob.base.statable;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import cn.originstar.yourjob.core.model.BaseModel;

@MappedSuperclass
public abstract class StatableModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------
    // 记录操作痕迹
    // ---------------------------------------------------
    private Date createdOn;

    private Date updatedOn;

    private String createdBy;

    private String updatedBy;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
