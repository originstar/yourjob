package cn.originstar.yourjob.system.permission.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import cn.originstar.yourjob.base.statable.StatableModel;
import cn.originstar.yourjob.system.permission.daos.RoleDAO;
import cn.originstar.yourjob.system.permission.daos.impl.RoleDAOImpl;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role extends StatableModel implements Cloneable {

    private static final long serialVersionUID = 3376289354386457728L;

    /**
     * Project Role Name (Title)
     */
    @NotNull
    private String name;

    /**
     * System Role Name
     */
    private String sysName;

    @NotNull
    private String abbreviation;

    @NotNull
    private String department;

    private Integer position = 0;

    private boolean activeInReport;

    private boolean system = true;

    private boolean admin;

    private boolean trash;

    private boolean search;

    private boolean reports;

    /**
     * total 4 levels, 0~3
     * 0, Cannot access Status Report
     * 1, Can report on own organization tree's projects
     * 2, Can report on applicable organizations' projects
     * 3, Can report on any projects
     * tip: clients' max level is limited to 1
     */
    private Integer reportStatus = 0;

    /**
     * total 3 levels, 0~2
     * 0, Cannot use Global RM
     * 1, Can use Global RM to manage own department
     * 2, Can use Global RM to manage any department
     */
    private Integer resource = 0;

    /**
     * view own and project-client
     */
    private boolean organizationView;

    /**
     * view all applicable orgs
     * only for internal
     */
    private boolean organizationViewApplicable;

    /**
     * view all orgs
     * only for internal
     */
    private boolean organizationViewAll;

    /**
     * edit own
     * create sub org of own
     */
    private boolean organizationEdit;

    /**
     * edit all visible
     * create any org
     * only for internal
     */
    private boolean organizationEditAll;

    /**
     * trash sub of own
     */
    private boolean organizationTrash;

    /**
     * trash all visible
     * only for internal
     */
    private boolean organizationTrashAll;

    /**
     * can see users of visible orgs
     */
    private boolean userView;

    /**
     * edit own
     * create own
     */
    private boolean userEdit;

    /**
     * edit all visible
     * create any org
     * only for internal
     */
    private boolean userEditAll;

    /**
     * trash own
     */
    private boolean userTrash;

    /**
     * trash all visible
     * only for internal
     */
    private boolean userTrashAll;

    /**
     * member
     */
    private boolean projectView;

    /**
     * member and own
     */
    private boolean projectViewApplicable;

    /**
     * member and own
     */
    private boolean projectViewAll;

    private boolean projectCreate;

    private boolean projectCreateAll;

    private boolean templateCreate;

    private boolean templateCreateAll;

    private boolean projectEdit;

    private boolean projectEditAll;

    private boolean projectTrash;

    private boolean projectTrashAll;

    private boolean projectAnnounce;

    private boolean projectAnnounceAll;

    private boolean projectTeam;

    private boolean projectTeamAll;

    private boolean projectScheduleView;

    /**
     * only for internal
     */
    private boolean projectScheduleEdit;

    private boolean projectTaskView;

    private boolean projectTaskViewAll;

    private boolean projectTaskEdit;

    private boolean projectTaskEditAll;

    private boolean projectTaskTrash;

    private boolean projectTaskTrashAll;

    private boolean projectFileView;

    private boolean projectFileViewAll;

    private boolean projectFileEdit;

    private boolean projectFileEditAll;

    private boolean projectFileTrash;

    private boolean projectFileTrashAll;

    /**
     * total 4 levels, 0~3
     * 0, Cannot see discussions tab
     * 1, Can view/reply discussions
     * 2, Include L1 and can CRUD own discussions
     * 3, Can CRUD all discussions
     * tip: clients' max level is limited to 1
     */
    private Integer discussion = 0;

    /**
     * total 4 levels, 0~3
     * 0, Cannot see notes tab
     * 1, Can view/reply notes
     * 2, Include L1 and can CRUD own notes
     * 3, Can CRUD all notes
     * tip: clients' max level is limited to 1
     */
    private Integer note = 0;

    private boolean form;

    /**
     * total 4 levels, 0~3
     * 0, Cannot CRUD any form of this type
     * 1, Can view all forms of this type
     * 2, Can view/create all forms of this type
     * 3, Can manage all forms of this type
     * tip: clients' max level is limited to 1
     */
    private Integer formCreativeRequest = 0;

    private Integer formEstimate = 0;

    private Integer formWorkOrder = 0;

    private Integer folder = 0;

    public static RoleDAO dao = new RoleDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean isActiveInReport() {
        return activeInReport;
    }

    public void setActiveInReport(boolean activeInReport) {
        this.activeInReport = activeInReport;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isTrash() {
        return trash;
    }

    public void setTrash(boolean trash) {
        this.trash = trash;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isReports() {
        return reports;
    }

    public void setReports(boolean reports) {
        this.reports = reports;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

    public boolean isOrganizationView() {
        return organizationView;
    }

    public void setOrganizationView(boolean organizationView) {
        this.organizationView = organizationView;
    }

    public boolean isOrganizationViewApplicable() {
        return organizationViewApplicable;
    }

    public void setOrganizationViewApplicable(boolean organizationViewApplicable) {
        this.organizationViewApplicable = organizationViewApplicable;
    }

    public boolean isOrganizationViewAll() {
        return organizationViewAll;
    }

    public void setOrganizationViewAll(boolean organizationViewAll) {
        this.organizationViewAll = organizationViewAll;
    }

    public boolean isOrganizationEdit() {
        return organizationEdit;
    }

    public void setOrganizationEdit(boolean organizationEdit) {
        this.organizationEdit = organizationEdit;
    }

    public boolean isOrganizationEditAll() {
        return organizationEditAll;
    }

    public void setOrganizationEditAll(boolean organizationEditAll) {
        this.organizationEditAll = organizationEditAll;
    }

    public boolean isOrganizationTrash() {
        return organizationTrash;
    }

    public void setOrganizationTrash(boolean organizationTrash) {
        this.organizationTrash = organizationTrash;
    }

    public boolean isOrganizationTrashAll() {
        return organizationTrashAll;
    }

    public void setOrganizationTrashAll(boolean organizationTrashAll) {
        this.organizationTrashAll = organizationTrashAll;
    }

    public boolean isUserView() {
        return userView;
    }

    public void setUserView(boolean userView) {
        this.userView = userView;
    }

    public boolean isUserEdit() {
        return userEdit;
    }

    public void setUserEdit(boolean userEdit) {
        this.userEdit = userEdit;
    }

    public boolean isUserEditAll() {
        return userEditAll;
    }

    public void setUserEditAll(boolean userEditAll) {
        this.userEditAll = userEditAll;
    }

    public boolean isUserTrash() {
        return userTrash;
    }

    public void setUserTrash(boolean userTrash) {
        this.userTrash = userTrash;
    }

    public boolean isUserTrashAll() {
        return userTrashAll;
    }

    public void setUserTrashAll(boolean userTrashAll) {
        this.userTrashAll = userTrashAll;
    }

    public boolean isProjectView() {
        return projectView;
    }

    public void setProjectView(boolean projectView) {
        this.projectView = projectView;
    }

    public boolean isProjectViewApplicable() {
        return projectViewApplicable;
    }

    public void setProjectViewApplicable(boolean projectViewApplicable) {
        this.projectViewApplicable = projectViewApplicable;
    }

    public boolean isProjectViewAll() {
        return projectViewAll;
    }

    public void setProjectViewAll(boolean projectViewAll) {
        this.projectViewAll = projectViewAll;
    }

    public boolean isProjectCreate() {
        return projectCreate;
    }

    public void setProjectCreate(boolean projectCreate) {
        this.projectCreate = projectCreate;
    }

    public boolean isProjectCreateAll() {
        return projectCreateAll;
    }

    public void setProjectCreateAll(boolean projectCreateAll) {
        this.projectCreateAll = projectCreateAll;
    }

    public boolean isTemplateCreate() {
        return templateCreate;
    }

    public void setTemplateCreate(boolean templateCreate) {
        this.templateCreate = templateCreate;
    }

    public boolean isTemplateCreateAll() {
        return templateCreateAll;
    }

    public void setTemplateCreateAll(boolean templateCreateAll) {
        this.templateCreateAll = templateCreateAll;
    }

    public boolean isProjectEdit() {
        return projectEdit;
    }

    public void setProjectEdit(boolean projectEdit) {
        this.projectEdit = projectEdit;
    }

    public boolean isProjectEditAll() {
        return projectEditAll;
    }

    public void setProjectEditAll(boolean projectEditAll) {
        this.projectEditAll = projectEditAll;
    }

    public boolean isProjectTrash() {
        return projectTrash;
    }

    public void setProjectTrash(boolean projectTrash) {
        this.projectTrash = projectTrash;
    }

    public boolean isProjectTrashAll() {
        return projectTrashAll;
    }

    public void setProjectTrashAll(boolean projectTrashAll) {
        this.projectTrashAll = projectTrashAll;
    }

    public boolean isProjectAnnounce() {
        return projectAnnounce;
    }

    public void setProjectAnnounce(boolean projectAnnounce) {
        this.projectAnnounce = projectAnnounce;
    }

    public boolean isProjectAnnounceAll() {
        return projectAnnounceAll;
    }

    public void setProjectAnnounceAll(boolean projectAnnounceAll) {
        this.projectAnnounceAll = projectAnnounceAll;
    }

    public boolean isProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(boolean projectTeam) {
        this.projectTeam = projectTeam;
    }

    public boolean isProjectTeamAll() {
        return projectTeamAll;
    }

    public void setProjectTeamAll(boolean projectTeamAll) {
        this.projectTeamAll = projectTeamAll;
    }

    public boolean isProjectScheduleView() {
        return projectScheduleView;
    }

    public void setProjectScheduleView(boolean projectScheduleView) {
        this.projectScheduleView = projectScheduleView;
    }

    public boolean isProjectScheduleEdit() {
        return projectScheduleEdit;
    }

    public void setProjectScheduleEdit(boolean projectScheduleEdit) {
        this.projectScheduleEdit = projectScheduleEdit;
    }

    public boolean isProjectTaskView() {
        return projectTaskView;
    }

    public void setProjectTaskView(boolean projectTaskView) {
        this.projectTaskView = projectTaskView;
    }

    public boolean isProjectTaskViewAll() {
        return projectTaskViewAll;
    }

    public void setProjectTaskViewAll(boolean projectTaskViewAll) {
        this.projectTaskViewAll = projectTaskViewAll;
    }

    public boolean isProjectTaskEdit() {
        return projectTaskEdit;
    }

    public void setProjectTaskEdit(boolean projectTaskEdit) {
        this.projectTaskEdit = projectTaskEdit;
    }

    public boolean isProjectTaskEditAll() {
        return projectTaskEditAll;
    }

    public void setProjectTaskEditAll(boolean projectTaskEditAll) {
        this.projectTaskEditAll = projectTaskEditAll;
    }

    public boolean isProjectTaskTrash() {
        return projectTaskTrash;
    }

    public void setProjectTaskTrash(boolean projectTaskTrash) {
        this.projectTaskTrash = projectTaskTrash;
    }

    public boolean isProjectTaskTrashAll() {
        return projectTaskTrashAll;
    }

    public void setProjectTaskTrashAll(boolean projectTaskTrashAll) {
        this.projectTaskTrashAll = projectTaskTrashAll;
    }

    public boolean isProjectFileView() {
        return projectFileView;
    }

    public void setProjectFileView(boolean projectFileView) {
        this.projectFileView = projectFileView;
    }

    public boolean isProjectFileViewAll() {
        return projectFileViewAll;
    }

    public void setProjectFileViewAll(boolean projectFileViewAll) {
        this.projectFileViewAll = projectFileViewAll;
    }

    public boolean isProjectFileEdit() {
        return projectFileEdit;
    }

    public void setProjectFileEdit(boolean projectFileEdit) {
        this.projectFileEdit = projectFileEdit;
    }

    public boolean isProjectFileEditAll() {
        return projectFileEditAll;
    }

    public void setProjectFileEditAll(boolean projectFileEditAll) {
        this.projectFileEditAll = projectFileEditAll;
    }

    public boolean isProjectFileTrash() {
        return projectFileTrash;
    }

    public void setProjectFileTrash(boolean projectFileTrash) {
        this.projectFileTrash = projectFileTrash;
    }

    public boolean isProjectFileTrashAll() {
        return projectFileTrashAll;
    }

    public void setProjectFileTrashAll(boolean projectFileTrashAll) {
        this.projectFileTrashAll = projectFileTrashAll;
    }

    public Integer getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Integer discussion) {
        this.discussion = discussion;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public Integer getFormCreativeRequest() {
        return formCreativeRequest;
    }

    public void setFormCreativeRequest(Integer formCreativeRequest) {
        this.formCreativeRequest = formCreativeRequest;
    }

    public Integer getFormEstimate() {
        return formEstimate;
    }

    public void setFormEstimate(Integer formEstimate) {
        this.formEstimate = formEstimate;
    }

    public Integer getFormWorkOrder() {
        return formWorkOrder;
    }

    public void setFormWorkOrder(Integer formWorkOrder) {
        this.formWorkOrder = formWorkOrder;
    }

    public Integer getFolder() {
        return folder;
    }

    public void setFolder(Integer folder) {
        this.folder = folder;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @JsonIgnore
    public static List<Long> getIdsByRoles(List<Role> roles) {
        List<Long> ids = new ArrayList<Long>();
        if (roles.size() > 0) {
            for (Role role : roles) {
                if (!ids.contains(role.getId())) {
                    ids.add(role.getId());
                }
            }
        }
        return ids;
    }

    public static Map<String, Map<String, String>> groupOptions() {
        Map<String, Map<String, String>> options = new LinkedHashMap<String, Map<String, String>>();
        List<Role> roles = dao.findAllByOrder("department, name");
        if (roles != null) {
            List<String> departments = Role.dao.findDepartments();
            for (String department : departments) {
                Map<String, String> rolesOptions = new LinkedHashMap<String, String>();
                for (Role role : roles) {
                    if (role.getDepartment().equals(department)) {
                        rolesOptions.put(String.valueOf(role.getId()), role.getName());
                    }
                }
                options.put(department, rolesOptions);
            }
        }
        return options;
    }

    public static Map<String, String> groupOptions(String department) {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        if (StringUtils.isEmpty(department)) {
            return options;
        }
        List<String> departments = new ArrayList<String>();
        departments.add(department);
        List<Role> roles = dao.findByDepartments(departments);
        for (Role role : roles) {
            if (role.getDepartment().equals(department)) {
                options.put(String.valueOf(role.getId()), role.getName());
            }
        }
        return options;
    }

    // ---------------------------------------------------
    // DataAccess
    // ---------------------------------------------------

}
