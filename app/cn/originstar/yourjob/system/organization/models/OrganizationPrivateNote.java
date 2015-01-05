package cn.originstar.yourjob.system.organization.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import cn.originstar.yourjob.core.model.BaseModel;

@Entity
public class OrganizationPrivateNote extends BaseModel{

    private static final long serialVersionUID = -2179663836296162204L;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization target;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public Organization getTarget() {
        return target;
    }

    public void setTarget(Organization target) {
        this.target = target;
    }

    // ---------------------------------------------------
    // DataAccess
    // ---------------------------------------------------

}
