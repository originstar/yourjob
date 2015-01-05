package cn.originstar.yourjob.system.organization.daos;

import java.util.List;

import cn.originstar.yourjob.base.statable.StatableDAO;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.user.models.User;

public interface OrganizationDAO extends StatableDAO<Organization> {

    /**
     * @param parent
     * @return The organizations under the given parent
     */
    List<Organization> findByParent(Organization parent);

    /**
     * @param organizationId
     * @return The Organizations whose parent.id matches the given id
     */
    List<Organization> findByParentId(Long parentId);

    List<Organization> findByRoot(Organization root);

    /**
     * @param organizationId
     * @return The Organizations whose root.id matches the given id
     */
    List<Organization> findByRootId(Long rootId);

    Organization findByName(String name);

    List<Organization> findInternalByLevel(Integer level);

    List<Organization> findByRootAndLevel(Organization root, Integer level, boolean cycle);

    List<Organization> findOwnByUserId(Long userId);

    List<Organization> findClientsByUserId(Long userId);

    List<Organization> findClientsAndApplicableByUserId(Long userId);

    List<Organization> findOwnAndClientsByUserId(Long userId);

    List<Organization> findByNameOrAbbr(String keywords);

    List<Organization> findTrashed();

    List<Organization> findPurged();

    List<Organization> findByType(Organization.Type type);

    List<Organization> findByRootAndLevelAndType(Organization root, Integer level, boolean cycle, Organization.Type type);

    List<Organization> findByCompany(String company);

    List<Organization> findByCompanyAndType(String company, Organization.Type type);

    List<Organization> findByDepartment(String department);

    List<Organization> findByDepartmentAndType(String department, Organization.Type type);

    List<String> findCompanies();

    List<String> findCompaniesByType(Organization.Type type);

    List<Organization> findTopLevel();

    /**
     * 查找所有的organization，即返回所有parent为null的数据
     * parent不是null的为department
     * 
     * @return
     */
    List<Organization> findOrganizationsByType(Organization.Type type);

    List<Organization> findByTypeAndLevel(Organization.Type type, Integer level);

    List<Organization> findDepartments(Long organizationId);

    Organization findOwnerOrganization();

    List<Long> findVisibleIds(User user);

}
