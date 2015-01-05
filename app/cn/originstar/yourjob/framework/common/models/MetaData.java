package cn.originstar.yourjob.framework.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import cn.originstar.yourjob.system.common.models.MultiTenancyModel;

@Entity
public class MetaData extends MultiTenancyModel {

    @Id
    @Column(name = "meta_data_key")
    private String key;

    @Column(name = "meta_data_value", columnDefinition = "text")
    private String value;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
