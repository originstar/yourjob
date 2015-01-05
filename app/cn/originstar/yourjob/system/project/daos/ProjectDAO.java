package cn.originstar.yourjob.system.project.daos;

import java.util.List;

import cn.originstar.yourjob.framework.statable.StatableDAO;
import cn.originstar.yourjob.system.project.models.Project;
import cn.originstar.yourjob.system.project.models.Project.Status;
import cn.originstar.yourjob.system.project.models.ProjectCategory;
import cn.originstar.yourjob.system.user.models.User;

public interface ProjectDAO extends StatableDAO<Project> {

    /**
     * findCampaigns by status
     * 
     * @return
     */
    List<Project> findCampaignsByStatus(Status status);

    List<Project> findTemplates();

    List<Project> findOwnDrafts(User user);

    /**
     * find Templates for campaign or project
     * 
     * @param organizationId
     * @param isForProductionJob
     * @return
     */
    List<Project> findTemplatesByOrganizationId(Long organizationId, boolean isForProductionJob);

    /**
     * 根据code 查询列表集，主要用于页面的根据关键字查询
     * 
     * @param keywords
     * @return
     */
    List<Project> findByCodeOrName(String keywords);

    List<Project> findByCode(String code);

    /**
     * 根据条件查询所有state是Normal的一级project（只用于homepage 的activeProject tab 和 inActiveProject tab）
     * 
     * @return
     */
    List<Project> findCampaignByConditions(List<Status> status, Long projectId, List<Long> projectIds, List<Long> projectTemplateIds);

    /**
     * 根据指定id查询子级project
     * 
     * @return
     */
    List<Project> findChildren(Long projectId);

    /**
     * 根据指定id和type查询子级project
     * 
     * @return
     */
    List<Project> findByParentAndTemplates(Project project, List<Long> templateIds);

    List<Project> findByParent(Project project);

    /**
     * 查找星标的project
     * 
     * @return
     */
    List<Project> findStarred(User user);

    List<Project> findByOrganizationIds(List<Long> organizationIds, boolean onlyCampaign);

    List<Project> findByOrganizationIdsAndTypeIds(List<Long> organizationIds, List<Long> projectTypeIds);

    List<Project> findByOrder(List<Long> ids, String order);

    List<Project> findByProjectCategory(ProjectCategory category);

    List<Project> findTrashed();

    List<Project> findPurged();

    List<Project> findActiveByCilentId(Long clientId);

    /**
     * 查询所有符合条件的project
     * 
     * @param status
     * @param projectId
     * @param projectIds
     * @param projectTemplateIds
     * @return
     */
    List<Project> findByConditions(List<Status> status, Long projectId, List<Long> projectIds, List<Long> projectTemplateIds, List<Long> organizationIds, boolean campaignOnly);

    List<Project> findTemplatesByKeywords(String keywords);

    List<Project> findApplicable(Long userId);

}
