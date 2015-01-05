package cn.originstar.yourjob.system.common.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import cn.originstar.yourjob.framework.multitenancy.MultiTenancyHelper;

/**
 * The base class of all multitenancy cn.originstar.yourjob.models.
 * 
 * @see http://blog.lunatech.com/2011/03/04/play-framework-writing-multitenancy-application-hibernate-filters
 * 
 * @author Yonggang Yuan
 */
@MappedSuperclass
@FilterDef(name = "tenant", parameters = {
    @ParamDef(name = "tenantId", type = "long")
}, defaultCondition = "tenantId = :tenantId")
@Filter(name = "tenant")
public abstract class MultiTenancyModel {

    @NotNull
    @Column(nullable = false)
    private Long tenantId;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    // ---------------------------------------------------
    // JPA Event Handlers
    // ---------------------------------------------------

    @PrePersist
    public void prePersist() {
        if (getTenantId() == null) {
            setTenantId(MultiTenancyHelper.getCurrentTenantId());
        }
    }

}
