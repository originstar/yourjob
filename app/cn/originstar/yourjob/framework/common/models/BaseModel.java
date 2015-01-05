package cn.originstar.yourjob.framework.common.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import cn.originstar.yourjob.framework.common.models.interfaces.UrlAccess;
import cn.originstar.yourjob.system.common.models.MultiTenancyModel;

/**
 * Base model which Long id as PK, super class of all cn.originstar.yourjob.models except MetaData
 * 
 * @author Yonggang Yuan
 */
@MappedSuperclass
public abstract class BaseModel extends MultiTenancyModel implements UrlAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ---------------------------------------------------
    // UrlAccess
    // ---------------------------------------------------

    @Override
    public String getViewUrl() {
        return "#";
    }

    @Override
    public String getAbsoluteViewUrl() {
        return "#";
    }

}
