package cn.originstar.yourjob.system.project.daos;

import java.util.List;

import cn.originstar.yourjob.core.db.dao.DAO;
import cn.originstar.yourjob.system.project.models.ProjectCategory;

public interface ProjectCategoryDAO extends DAO<ProjectCategory, Long> {

    /**
     * @param organizationId
     * @return The users whose organization.id matches the give long
     */
    List<ProjectCategory> findByOrganizationId(Long organizationId);

}
