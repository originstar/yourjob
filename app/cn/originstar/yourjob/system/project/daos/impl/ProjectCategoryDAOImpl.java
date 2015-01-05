package cn.originstar.yourjob.system.project.daos.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import cn.originstar.yourjob.core.db.dao.AbstractDAO;
import cn.originstar.yourjob.system.project.daos.ProjectCategoryDAO;
import cn.originstar.yourjob.system.project.models.ProjectCategory;

public class ProjectCategoryDAOImpl extends AbstractDAO<ProjectCategory, Long> implements ProjectCategoryDAO {

    public List<ProjectCategory> findByOrganizationId(Long organizationId) {
        String hql = "from ProjectCategory where organization.id = :organizationId order by name asc";
        TypedQuery<ProjectCategory> query = createQuery(hql).setParameter("organizationId", organizationId);
        return query.getResultList();
    }

}
