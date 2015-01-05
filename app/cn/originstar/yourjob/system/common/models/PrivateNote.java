package cn.originstar.yourjob.system.common.models;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import cn.originstar.yourjob.framework.common.models.BaseModel;
import cn.originstar.yourjob.system.user.models.User;

@MappedSuperclass
public abstract class PrivateNote extends BaseModel {

    @Type(type = "text")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

}
