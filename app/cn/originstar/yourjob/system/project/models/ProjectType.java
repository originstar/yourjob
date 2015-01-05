package cn.originstar.yourjob.system.project.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.originstar.yourjob.framework.common.models.BaseModel;
import cn.originstar.yourjob.system.project.daos.ProjectTypeDAO;
import cn.originstar.yourjob.system.project.daos.impl.ProjectTypeDAOImpl;

@Entity
public class ProjectType extends BaseModel {

    @NotNull
    private Integer level;

    @NotNull
    private String name;

    private String abbreviation;

    private String color;

    /**
     * Generic DAO for ProjectType model
     */
    public static ProjectTypeDAO dao = new ProjectTypeDAOImpl();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    /**
     * 获取所有的projectType的结果集并组织成map的形式
     * 
     * @return
     */
    public static Map<String, String> mapForOptions() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        List<ProjectType> projectTypes = new ArrayList<ProjectType>();

        projectTypes = dao.findAll();

        for (ProjectType pt : projectTypes) {
            options.put(pt.getId() + "", pt.getName());
        }

        return options;
    }

    @JsonIgnore
    public static List<Long> getIdsByProjectTypes(List<ProjectType> projectTypes) {
        List<Long> ids = new ArrayList<Long>();
        if (projectTypes.size() > 0) {
            for (ProjectType projectType : projectTypes) {
                if (!ids.contains(projectType.getId())) {
                    ids.add(projectType.getId());
                }
            }
        }
        return ids;
    }

}
