package cn.originstar.yourjob.system.organization.daos.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import cn.originstar.yourjob.base.statable.AbstractStatableDAO;
import cn.originstar.yourjob.system.organization.daos.OrganizationDAO;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.user.models.User;

@Repository
public class OrganizationDAOImpl extends AbstractStatableDAO<Organization> implements OrganizationDAO {

    // ------------------------------------------------------------------------
    // OrganizationDAO implementation
    // ------------------------------------------------------------------------

    @Override
    public List<Organization> findByParent(Organization parent) {
        if (parent == null) {
            return findByParentId(null);
        }

        return findByParentId(parent.getId());
    }

    @Override
    public List<Organization> findByParentId(Long organizationId) {
        String parentIdCondition = organizationId != null ? "parent.id = :parentId" : "parent.id is null";
        String hql = "from Organization where " + parentIdCondition + " order by type desc, name asc";
        TypedQuery<Organization> query = createQuery(hql);

        if (organizationId != null) {
            query.setParameter("parentId", organizationId);
        }

        return query.getResultList();
    }

    @Override
    public List<Organization> findByRoot(Organization root) {
        return findByRootId(root.getId());
    }

    @Override
    public List<Organization> findByRootId(Long organizationId) {
        // bypass the null check, rootId should never be null

        String hql = "from Organization where root.id = :rootId order by name asc";
        TypedQuery<Organization> query = createQuery(hql).setParameter("rootId", organizationId);

        return query.getResultList();
    }

    // ------------------------------------------------------------------------
    // Override AbstractStatableDAO implementation
    // ------------------------------------------------------------------------

    @Override
    public Organization findByName(String name) {
        return getSingleResultOrNull(createQuery("from Organization where name = :name").setParameter("name", name));
    }

    @Override
    public List<Organization> findInternalByLevel(Integer level) {
        String hql = "from Organization where type = :type ";
        if (level == null) {
            hql += "order by name";
            return createQuery(hql).setParameter("type", Organization.Type.INTERNAL).getResultList();
        } else {
            hql += "and level = :level order by name";
            return createQuery(hql).setParameter("type", Organization.Type.INTERNAL).setParameter("level", level).getResultList();
        }
    }

    @Override
    public List<Organization> findByRootAndLevel(Organization root, Integer level, boolean cycle) {
        if (root == null) {
            return new ArrayList<Organization>();
        }
        String hql = "from Organization where level";
        hql += cycle ? " >= :level " : " = :level ";
        hql += "and root.id = :rootId ";
        return createQuery(hql).setParameter("rootId", root.getId()).setParameter("level", level).getResultList();
    }

    @Override
    public List<Organization> findOwnByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<Organization>();
        }
        return createQuery("from Organization where root.id = (select root.id from Organization where id in (select organization.id from User where id = :userId))",
                Organization.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Organization> findClientsByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<Organization>();
        }
        return createQuery("from Organization where id = (select client.id from Project where id in (select project.id from ProjectMember where user.id = :userId))",
                Organization.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Organization> findClientsAndApplicableByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<Organization>();
        }
        return createQuery(
                "from Organization where id = (select client.id from Project where id in (select project.id from ProjectMember where user.id = :userId)) or id in (select a.id u join u.applicableOrganizations a where u.id = :userId)",
                Organization.class).setParameter("userId",
                userId).getResultList();
    }

    @Override
    public List<Organization> findOwnAndClientsByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<Organization>();
        }
        return createQuery(
                "from Organization where root.id = (select root.id from Organization where id in (select organization.id from User where user.id = :userId)) or id in (select client.id from Project where id in (select project.id from ProjectMember where user.id = :userId))",
                Organization.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Organization> findByNameOrAbbr(String keywords) {
        return createQuery("from Organization where name like :keywords or abbreviation like :keywords", Organization.class).setParameter("keywords",
                "%" + keywords.trim() + "%").getResultList();
    }

    @Override
    public List<Organization> findByType(Organization.Type type) {
        return createQuery("from Organization where type = :type order by name", Organization.class).setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Organization> findByRootAndLevelAndType(Organization root, Integer level, boolean cycle, Organization.Type type) {
        if (root == null) {
            return new ArrayList<Organization>();
        }
        String hql = "from Organization where level";
        hql += cycle ? " >= :level " : " = :level ";
        hql += "and root.id = :rootId ";
        hql += "and type = :type";
        return createQuery(hql).setParameter("rootId", root.getId()).setParameter("level", level).setParameter("type", type).getResultList();
    }

    @Override
    public List<Organization> findByCompany(String company) {
        return createQuery("from Organization where company = :company and level = 1 order by type, company").setParameter("company", company).getResultList();
    }

    @Override
    public List<Organization> findByCompanyAndType(String company, Organization.Type type) {
        return createQuery("from Organization where company = :company and level = 1  and type = :type order by type, company").setParameter("company", company)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Organization> findByDepartment(String department) {
        return createQuery("from Organization where department = :department and level > 1 order by type, company, department").setParameter("department", department)
                .getResultList();
    }

    @Override
    public List<Organization> findByDepartmentAndType(String department, Organization.Type type) {
        return createQuery("from Organization where department = :department and level > 1  and type = :type order by type, company").setParameter("department", department)
                .setParameter("type", type).getResultList();
    }

    @Override
    public List<String> findCompanies() {
        return createQuery("select distinct company from Organization where company is not null order by type, company", String.class).getResultList();
    }

    @Override
    public List<String> findCompaniesByType(Organization.Type type) {
        return createQuery("select distinct company from Organization where company is not null and type = :type order by type, company", String.class).setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Organization> findTopLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Organization> findOrganizationsByType(Organization.Type type) {
        return createQuery("from Organization where root is null and type = :type order by type desc,name asc").setParameter("type", type).getResultList();
    }

    @Override
    public List<Organization> findDepartments(Long organizationId) {
        return createQuery("from Organization where parent.id = :parentId order by type desc,name asc").setParameter("parentId", organizationId).getResultList();
    }

    @Override
    public Organization findOwnerOrganization() {
        return createQuery("from Organization where type = :type and parent is null order by id", Organization.class).setParameter("type", Organization.Type.INTERNAL)
                .getResultList().get(0);
    }

    @Override
    public List<Organization> findTrashed() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Organization> findPurged() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Organization> findByTypeAndLevel(cn.originstar.yourjob.system.organization.models.Organization.Type type, Integer level) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Long> findVisibleIds(User user) {
        // TODO Auto-generated method stub
        return null;
    }

}
