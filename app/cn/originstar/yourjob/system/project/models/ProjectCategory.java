package cn.originstar.yourjob.system.project.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import play.Logger;

import cn.originstar.yourjob.framework.common.models.BaseModel;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.project.daos.ProjectCategoryDAO;
import cn.originstar.yourjob.system.project.daos.impl.ProjectCategoryDAOImpl;

@Entity
public class ProjectCategory extends BaseModel {

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Organization organization;

    /**
     * Generic DAO for ProjectCategory model
     */
    public static ProjectCategoryDAO dao = new ProjectCategoryDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    /**
     * list all ProjectCategory by organization id
     * 
     * @param organizationId
     * @return
     */
    // TODO 该方法将被更名和移入到合适的地方
    public static List<ProjectCategory> listForOptions(Long organizationId) {
        Logger.debug("ProjectCategory.listForOptions");
        List<ProjectCategory> projectCategories = dao.findByOrganizationId(organizationId);
        // List<ProjectCategory> projectCategories = ProjectCategory.find.where().eq("organization.id", organizationId).findList();
        return projectCategories;
    }

    /**
     * change list to map to show categories select when add a project
     * 
     * @param organizationId
     * @return
     */
    // TODO 该方法将被更名和移入到合适的地方
    public static Map<String, String> mapForOptions(Long organizationId) {
        List<ProjectCategory> projectCategories = ProjectCategory.listForOptions(organizationId);
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (ProjectCategory projectCategory : projectCategories) {
            options.put(projectCategory.getId() + "", projectCategory.getName());
        }
        return options;
    }

}
