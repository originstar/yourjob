package cn.originstar.yourjob.framework.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import cn.originstar.yourjob.system.common.daos.SequenceDAO;
import cn.originstar.yourjob.system.common.daos.impl.SequenceDAOImpl;

@Entity
public class Sequence extends BaseModel {

    @Column(name = "seq_key")
    private String key;

    @Column(name = "seq_value")
    private Integer value;

    public Sequence() {

    }

    public Sequence(String key) {
        this.setKey(key);
        this.setValue(0);
    }

    /**
     * Generic DAO for Sequence model
     */
    public static SequenceDAO dao = new SequenceDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
