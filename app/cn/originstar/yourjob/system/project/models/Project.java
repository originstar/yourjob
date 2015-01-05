package cn.originstar.yourjob.system.project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import cn.originstar.yourjob.system.common.models.WithLogoModel;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.permission.models.Role;
import cn.originstar.yourjob.system.project.daos.ProjectDAO;
import cn.originstar.yourjob.system.project.daos.impl.ProjectDAOImpl;
import cn.originstar.yourjob.system.user.models.User;

@Entity
public class Project extends WithLogoModel implements Cloneable {

    public enum Status {

        ACTIVE,

        COMPLETED,

        ONHOLD,

        CLOSED,

        TEMPLATE,

        DRAFT,

        KILLED

    }

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String code;

    private String nickname;

    @org.hibernate.annotations.Type(type = "text")
    private String description;

    private String color;

    private Date startDate;

    // 在页面中首次展现该Project
    @Column(columnDefinition="int default 0",nullable=false)
    private Integer firstVisitFlag = 0;

    @Temporal(TemporalType.TIMESTAMP)
    private Date liveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project projectTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organization client;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role projectRole;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project parent;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectType projectType;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectCategory category;

    private boolean scheduleFrom;

    private boolean billable;

    private String adClock;

    @org.hibernate.annotations.Type(type = "text")
    private String currentStatus;

    @org.hibernate.annotations.Type(type = "text")
    private String nextSteps;

    @org.hibernate.annotations.Type(type = "text")
    private String budget;

    private String mediaFees;

    private boolean announced;

    @ManyToOne(fetch = FetchType.LAZY)
    private User announcedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date announcedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private User completedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date completedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private User pausedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pausedOn;

    private String pauseReason;

    @ManyToOne(fetch = FetchType.LAZY)
    private User cancelledBy;

    // 临时存储项目结束日期
    @Transient
    private Date dueDate;

    @Transient
    private Integer taskCount;

    @Transient
    private Integer completedTaskCount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelledOn;

    private String cancelReason;

    @org.hibernate.annotations.Type(type = "text")
    private String applicableCompanies;

    @ManyToMany
    private List<Role> applicableRoles = new ArrayList<Role>();

    // 这里使用@JoinTable的原因
    // by default undirectional @OneToMany mappings and all @ManyToMany mappings are implemented using join table,
    // and default generation strategy for join table name doesn't allow you to have multiple such relationships between the same classes.
    @ManyToMany
    @JoinTable(name = "project_agency_partners")
    private List<Organization> agencyPartners = new ArrayList<Organization>();

    @ManyToMany
    @JoinTable(name = "project_vendors")
    private List<Organization> vendors = new ArrayList<Organization>();

    private boolean inStatusReport;

    // 在project plan排序的
    private Integer position;

    private String alfrescoNodeId;

    /**
     * 当share chat以后，share对象会自动获得channel watcher的资格，可以看到该项目的所有聊天记录了，但是不影响team的权限
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_chat_channel_watchers")
    private List<User> chatChannelWatchers = new ArrayList<User>();

    /**
     * Generic DAO for Project model
     */
    public static ProjectDAO dao = new ProjectDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(Date liveDate) {
        this.liveDate = liveDate;
    }

    public Project getProjectTemplate() {
        return projectTemplate;
    }

    public void setProjectTemplate(Project projectTemplate) {
        this.projectTemplate = projectTemplate;
    }

    public Organization getClient() {
        return client;
    }

    public void setClient(Organization client) {
        this.client = client;
    }

    public Role getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(Role projectRole) {
        this.projectRole = projectRole;
    }

    public Project getParent() {
        return parent;
    }

    public void setParent(Project parent) {
        this.parent = parent;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public List<User> getChatChannelWatchers() {
        return chatChannelWatchers;
    }

    public void setChatChannelWatchers(List<User> chatChannelWatchers) {
        this.chatChannelWatchers = chatChannelWatchers;
    }

    public ProjectCategory getCategory() {
        return category;
    }

    public void setCategory(ProjectCategory category) {
        this.category = category;
    }

    public boolean isScheduleFrom() {
        return scheduleFrom;
    }

    public void setScheduleFrom(boolean scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getAdClock() {
        return adClock;
    }

    public void setAdClock(String adClock) {
        this.adClock = adClock;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getNextSteps() {
        return nextSteps;
    }

    public void setNextSteps(String nextSteps) {
        this.nextSteps = nextSteps;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getMediaFees() {
        return mediaFees;
    }

    public void setMediaFees(String mediaFees) {
        this.mediaFees = mediaFees;
    }

    public boolean isAnnounced() {
        return announced;
    }

    public void setAnnounced(boolean announced) {
        this.announced = announced;
    }

    public User getAnnouncedBy() {
        return announcedBy;
    }

    public void setAnnouncedBy(User announcedBy) {
        this.announcedBy = announcedBy;
    }

    public Date getAnnouncedOn() {
        return announcedOn;
    }

    public void setAnnouncedOn(Date announcedOn) {
        this.announcedOn = announcedOn;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(User completedBy) {
        this.completedBy = completedBy;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public User getPausedBy() {
        return pausedBy;
    }

    public void setPausedBy(User pausedBy) {
        this.pausedBy = pausedBy;
    }

    public Date getPausedOn() {
        return pausedOn;
    }

    public void setPausedOn(Date pausedOn) {
        this.pausedOn = pausedOn;
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason;
    }

    public User getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(User cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public void setCompletedTaskCount(Integer completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public Date getCancelledOn() {
        return cancelledOn;
    }

    public void setCancelledOn(Date cancelledOn) {
        this.cancelledOn = cancelledOn;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getApplicableCompanies() {
        return applicableCompanies;
    }

    public void setApplicableCompanies(String applicableCompanies) {
        this.applicableCompanies = applicableCompanies;
    }

    public List<Role> getApplicableRoles() {
        return applicableRoles;
    }

    public void setApplicableRoles(List<Role> applicableRoles) {
        this.applicableRoles = applicableRoles;
    }

    public List<Organization> getAgencyPartners() {
        return agencyPartners;
    }

    public void setAgencyPartners(List<Organization> agencyPartners) {
        this.agencyPartners = agencyPartners;
    }

    public List<Organization> getVendors() {
        return vendors;
    }

    public void setVendors(List<Organization> vendors) {
        this.vendors = vendors;
    }

    public boolean isInStatusReport() {
        return inStatusReport;
    }

    public void setInStatusReport(boolean inStatusReport) {
        this.inStatusReport = inStatusReport;
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

    @Column(columnDefinition="int default 0",nullable=false)
    public Integer getFirstVisitFlag() {
        return firstVisitFlag;
    }

    public void setFirstVisitFlag(Integer firstVisitFlag) {
        this.firstVisitFlag = firstVisitFlag;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
