package cn.originstar.yourjob.system.permission.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.originstar.yourjob.core.dao.AbstractDAO;
import cn.originstar.yourjob.system.permission.daos.ACLPermissionDAO;
import cn.originstar.yourjob.system.permission.models.ACLPermission;
import cn.originstar.yourjob.system.user.models.User;

@Repository
public class ACLPermissionDAOImpl extends AbstractDAO<ACLPermission, Long> implements ACLPermissionDAO {

    @Override
    public List<ACLPermission> findByPermissionNameAndUser(String name, User user) {
        if (name == null || user == null) {
            return new ArrayList<ACLPermission>();
        }
        return createQuery("from ACLPermission where permissionName = :name and user.id = :userId",
                ACLPermission.class).setParameter("name", name).setParameter("userId", user.getId()).getResultList();
    }

}
