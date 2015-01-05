package cn.originstar.yourjob.system.permission.daos;

import java.util.List;

import cn.originstar.yourjob.core.dao.DAO;
import cn.originstar.yourjob.system.permission.models.ACLPermission;
import cn.originstar.yourjob.system.user.models.User;

public interface ACLPermissionDAO extends DAO<ACLPermission, Long> {

    List<ACLPermission> findByPermissionNameAndUser(String name, User user);

}
