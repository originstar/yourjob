package cn.originstar.yourjob.system.permission.daos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cn.originstar.yourjob.core.dao.AbstractDAO;
import cn.originstar.yourjob.system.permission.daos.RoleDAO;
import cn.originstar.yourjob.system.permission.models.Role;

@Repository
public class RoleDAOImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public List<Role> findAllByOrder(String order) {
        String hql = "from Role order by " + order;
        return createQuery(hql).getResultList();
    }

    @Override
    public List<Role> findByName(String name) {
        return createQuery("from Role where name = :name").setParameter("name", name).getResultList();
    }

    @Override
    public List<Role> findByDepartments(List<String> departments) {
        if (departments == null || departments.size() == 0) {
            return new ArrayList<Role>();
        }
        return createQuery("from Role where department in (:departments) order by position , department").setParameter("departments", departments).getResultList();
    }

    @Override
    public List<String> findDepartments() {
        return createQuery("select distinct department from Role order by position, department", String.class).getResultList();
    }

    @Override
    public List<Role> findByKeywords(String keywords) {
        return createQuery("from Role where name like :keywords order by position, department", Role.class).setParameter("keywords", "%" + keywords + "%").getResultList();
    }

    @Override
    public List<String> findDepartmentsByKeywords(String keywords) {
        return createQuery("select distinct department from Role where department like :keywords order by position, department", String.class).setParameter("keywords",
                "%" + keywords + "%").getResultList();
    }

    @Override
    public List<Role> findCreative(boolean isCreative) {
        if (isCreative) {
            return createQuery("from Role where name like '%Creative%' and name not like '%Develop%' order by position, department", Role.class).getResultList();
        } else {
            return createQuery("from Role where name like '%Develop%' order by position, department", Role.class).getResultList();
        }
    }

    @Override
    public List<Role> findRolesBykeyWords(String keywords) {
        String hql = "from Role where name like :keywords order by name";
        return createQuery(hql).setParameter("keywords", "%" + keywords + "%").getResultList();
    }

    @Override
    public Role findOneByNameAndSysName(String name, String sysName) {
        TypedQuery<Role> query = createQuery("from Role where name = ? and sysName = ?", Role.class)
                .setParameter(1, name)
                .setParameter(2, sysName)
                .setMaxResults(1);
        return getSingleResultOrNull(query);
    }

}
