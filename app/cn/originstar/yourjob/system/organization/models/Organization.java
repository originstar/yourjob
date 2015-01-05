package cn.originstar.yourjob.system.organization.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import cn.originstar.yourjob.base.statable.StatableModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Organization extends StatableModel implements Cloneable {

    private static final long serialVersionUID = 7281039525571823440L;

    public enum Type {
        CLIENT,

        VENDOR,

        AGENCY_PARTNER,

        INTERNAL
    }

    @NotNull
    @Column(nullable = false)
    @Required
    private String name;

    @NotNull
    @Column(nullable = false)
    @Required
    private String abbreviation;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Column(nullable = false)
    @Required
    private Type type;

    // TODO 第一级organization必须有company, 不同的分公司company可以有相同的部门department
    private String company;

    // TODO department与 Role的department相关联, 如果要在Organization里添加一个department，必须先创建相应department的Role
    private String department;

    @org.hibernate.annotations.Type(type = "text")
    private String description;

    private String address;

    private String phone;

    private String fax;

    private String website;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Organization parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Organization root;

    private Integer level = 1;

    private Integer position = 0;

    private String alfrescoNodeId;

    // transient fields/methods

    @Transient
    private List<Organization> childrenOrganizations = new ArrayList<Organization>();

    @JsonIgnore
    @Transient
    private OrganizationPrivateNote privateNote;

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public Organization getRoot() {
        return root;
    }

    public void setRoot(Organization root) {
        this.root = root;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getAlfrescoNodeId() {
        return alfrescoNodeId;
    }

    public void setAlfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
    }

    public List<Organization> getChildrenOrganizations() {
        return childrenOrganizations;
    }

    public void setChildrenOrganizations(List<Organization> childrenOrganizations) {
        this.childrenOrganizations = childrenOrganizations;
    }

    public OrganizationPrivateNote getPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(OrganizationPrivateNote privateNote) {
        this.privateNote = privateNote;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
