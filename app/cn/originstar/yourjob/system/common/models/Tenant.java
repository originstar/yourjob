package cn.originstar.yourjob.system.common.models;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import cn.originstar.yourjob.system.common.daos.TenantDAO;
import cn.originstar.yourjob.system.common.daos.impl.TenantDAOImpl;

@Entity
public class Tenant {

    public enum Status {
        NEW, ACTIVE, SUSPENDED, CLOSED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String subDomain;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private boolean isInitialized;

    /**
     * Generic DAO for Tenant model
     */
    public static TenantDAO dao = new TenantDAOImpl();

    public static Map<String, String> mapStatusesForOptions() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        for (Status status : Status.values()) {
            map.put(status.toString(), status.toString());
        }
        return map;
    }

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }
}
