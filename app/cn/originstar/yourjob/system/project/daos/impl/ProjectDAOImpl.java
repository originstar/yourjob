package cn.originstar.yourjob.system.project.daos.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import cn.originstar.yourjob.framework.statable.AbstractStatableDAO;
import cn.originstar.yourjob.system.common.models.StatableModel.State;
import cn.originstar.yourjob.system.project.daos.ProjectDAO;
import cn.originstar.yourjob.system.project.models.Project;
import cn.originstar.yourjob.system.project.models.Project.Status;
import cn.originstar.yourjob.system.project.models.ProjectCategory;
import cn.originstar.yourjob.system.user.models.User;

@Repository
public class ProjectDAOImpl extends AbstractStatableDAO<Project> implements ProjectDAO {

    /**
     * findCampaigns by status
     * 
     * @return
     */
    public List<Project> findCampaignsByStatus(Status status) {
        return createQuery("from Project where status = :status and parent is null").setParameter("status", status).getResultList();
    }

    public List<Project> findTemplates() {
        return createQuery("from Project where status = :status").setParameter("status", Project.Status.TEMPLATE).getResultList();
    }

    public List<Project> findTemplatesByKeywords(String keywords) {
        return createQuery("from Project where status = :status and name like :keywords").setParameter("status", Project.Status.TEMPLATE)
                .setParameter("keywords", "%" + keywords + "%").getResultList();
    }

    public List<Project> findOwnDrafts(User user) {
        if (user == null) {
            return new ArrayList<Project>();
        }
        return createQuery("from Project where status = :status and createdById = :createdById and parent is null").setParameter("status", Project.Status.DRAFT)
                .setParameter("createdById", user.getId())
                .getResultList();
    }

    @Override
    public List<Project> findByCodeOrName(String keywords) {
        return createQuery("from Project where code like :keywords or name like :keywords", Project.class).setParameter("keywords",
                "%" + keywords.trim() + "%").getResultList();
    }

    @Override
    public List<Project> findByCode(String code) {
        return createQuery("from Project where lower(code) = :code", Project.class).setParameter("code", code.toLowerCase()).getResultList();
    }

    @Override
    public List<Project> findCampaignByConditions(List<Status> status, Long projectId, List<Long> projectIds, List<Long> projectTemplateIds) {
        if (projectIds != null && projectIds.size() == 0) {
            return new ArrayList<Project>();
        }
        // 所有符合条件的一级project的结果集
        List<Project> allCampaign = new ArrayList<Project>();
        String hql = "from Project where 1=1 ";
        if (projectId != null && projectId != 0) {
            // keywords 的格式是：project.name(p.code )
            hql = hql + " and id = :projectId";
        }
        if (projectIds != null && projectIds.size() > 0) {
            hql = hql + " and id in (:projectIds)";
        }
        if (projectTemplateIds != null && projectTemplateIds.size() > 0) {
            hql = hql + " and projectTemplate is not null and projectTemplate.id in (:projectTemplateIds)";
        }
        if (status != null && status.size() > 0) {
            hql = hql + " and status in ( :status )";
        } else {
            hql = hql + " and status != :status1 and status != :status2";
        }
        TypedQuery<Project> query = createQuery(hql, Project.class);
        // set keywords
        if (projectId != null && projectId != 0) {
            query.setParameter("projectId", projectId);
        }
        // set organization ids
        if (projectIds != null && projectIds.size() > 0) {
            query.setParameter("projectIds", projectIds);
        }
        // set projectTypes
        if (projectTemplateIds != null && projectTemplateIds.size() > 0) {
            query.setParameter("projectTemplateIds", projectTemplateIds);
        }
        // set status
        if (status != null && status.size() > 0) {
            query.setParameter("status", status);
        } else {
            query.setParameter("status1", Status.ACTIVE).setParameter("status2", Status.DRAFT);
        }
        // 所有符合条件的结果集，不区分级别
        List<Project> allProject = query.getResultList();
        // 如果符合条件的是第一级project，直接添加进allCampaign，如果是第二级则把parent添加进allCampaign
        // TODO: 应该支持无限级project的查询，现在只支持两级
        for (Project project : allProject) {
            if (project.getParent() == null && !allCampaign.contains(project)) {
                allCampaign.add(project);
            } else if (project.getStatus() == Project.Status.TEMPLATE && !allCampaign.contains(project.getParent())) {
                allCampaign.add(project);
            } else if (project.getParent() != null && project.getParent().getStatus() != Project.Status.DRAFT && !allCampaign.contains(project.getParent())) {
                allCampaign.add(project.getParent());
            }
        }
        return allCampaign;
    }

    /**
     * 
     * TODO 目前实现的只是查找前5条，需要按实际情况查找
     */
    @Override
    public List<Project> findStarred(User user) {
        if (user == null) {
            return new ArrayList<Project>();
        }
        return createQuery("from Project where status != :status and id in (select project.id From ProjectMember where user.id = :userId) order by name")
                .setParameter("status", Project.Status.TEMPLATE).setParameter("userId", user.getId()).getResultList();
    }

    @Override
    public List<Project> findByOrganizationIds(List<Long> organizationIds, boolean onlyCampaign) {
        if (organizationIds == null || organizationIds.size() < 1) {
            return new ArrayList<Project>();
        }
        if (onlyCampaign) {
            return createQuery("from Project where parent is null and client.id in (:organizationIds)").setParameter("organizationIds", organizationIds).getResultList();
        } else {
            return createQuery("from Project where client.id in (:organizationIds)").setParameter("organizationIds", organizationIds).getResultList();
        }
    }

    @Override
    public List<Project> findByOrganizationIdsAndTypeIds(List<Long> organizationIds, List<Long> projectTypeIds) {
        if (organizationIds == null || organizationIds.size() < 1 || projectTypeIds == null || projectTypeIds.size() < 1) {
            return new ArrayList<Project>();
        }
        return createQuery("from Project where parent.id > 0 and client.id in (:organizationIds) and projectType.id in (:projectTypeIds)")
                .setParameter("organizationIds", organizationIds).setParameter("projectTypeIds", projectTypeIds).getResultList();
    }

    @Override
    public List<Project> findByOrder(List<Long> ids, String order) {
        if (ids != null && ids.size() == 0) {
            return new ArrayList<Project>();
        }
        if (ids == null) {
            return createQuery("from Project order by " + order, Project.class).getResultList();
        } else {
            return createQuery("from Project where id in (:ids) order by " + order, Project.class).setParameter("ids", ids).getResultList();
        }
    }

    @Override
    public List<Project> findByProjectCategory(ProjectCategory category) {
        return createQuery("from Project where category.id = :categoryId")
                .setParameter("categoryId", category.getId()).getResultList();
    }

    @Override
    public List<Project> findTrashed() {
        return createQuery("from Project where state = :state1 or state = :state2 order by updatedOn desc, name", Project.class).setParameter("state1", State.TRASHED)
                .setParameter("state2", State.CASCADING_TRASHED).getResultList();
    }

    @Override
    public List<Project> findPurged() {
        return createQuery("from Project where state = :state1 or state = :state2 order by updatedOn desc, name", Project.class).setParameter("state1", State.PURGED)
                .setParameter("state2", State.CASCADING_PURGED).getResultList();
    }

    @Override
    public List<Project> findActiveByCilentId(Long clientId) {
        if (clientId == null) {
            return new ArrayList<Project>();
        }
        return createQuery("from Project where status = :status and client.id = :clientId order by IFNULL(category.id,0), category.id")
                .setParameter("status", Project.Status.ACTIVE)
                .setParameter("clientId", clientId).getResultList();
    }

    @Override
    public List<Project> findByConditions(List<Status> status, Long projectId, List<Long> projectIds, List<Long> projectTemplateIds, List<Long> organizationIds, boolean campaignOnly) {
        if (projectIds != null && projectIds.size() == 0) {
            return new ArrayList<Project>();
        }
        // 所有符合条件的一级project的结果集
        String hql = "from Project where 1=1 ";
        if (projectId != null) {
            // keywords 的格式是：project.name(p.code )
            hql = hql + " and id = :projectId";
        }
        if (projectIds != null && projectIds.size() > 0) {
            hql = hql + " and id in (:projectIds)";
        }
        if (projectTemplateIds != null && projectTemplateIds.size() > 0) {
            hql = hql + " and projectTemplate is not null and projectTemplate.id in (:projectTemplateIds)";
        }
        if (status != null && status.size() > 0) {
            hql = hql + " and status in ( :status )";
        } else {
            hql = hql + " and status != :status1 and status != :status2";
        }
        if(organizationIds!=null && organizationIds.size()>0){
            hql += " and client.id in (:clientIds)";
        }
        if(campaignOnly)
            hql += " and parent is null";
        TypedQuery<Project> query = createQuery(hql, Project.class);
        // set keywords
        if (projectId != null) {
            query.setParameter("projectId", projectId);
        }
        // set organization ids
        if (projectIds != null && projectIds.size() > 0) {
            query.setParameter("projectIds", projectIds);
        }
        // set projectTypes
        if (projectTemplateIds != null && projectTemplateIds.size() > 0) {
            query.setParameter("projectTemplateIds", projectTemplateIds);
        }
        // set status
        if (status != null && status.size() > 0) {
            query.setParameter("status", status);
        } else {
            query.setParameter("status1", Status.ACTIVE).setParameter("status2", Status.DRAFT);
        }
        if(organizationIds!=null && organizationIds.size()>0){
            query.setParameter("clientIds", organizationIds);
        }
        return query.getResultList();
    }

    @Override
    public List<Project> findApplicable(Long userId) {
        if (userId == null) {
            return new ArrayList<Project>();
        }
        return createQuery("from Project where client.id in (select a.id from User u join u.applicableOrganizations a where u.id = :userId)").setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Project> findTemplatesByOrganizationId(Long organizationId, boolean isForProductionJob) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> findChildren(Long projectId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> findByParentAndTemplates(Project project, List<Long> templateIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Project> findByParent(Project project) {
        // TODO Auto-generated method stub
        return null;
    }
}
