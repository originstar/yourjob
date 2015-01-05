package cn.originstar.yourjob.system.permission.daos;

import java.util.List;

import cn.originstar.yourjob.core.dao.DAO;
import cn.originstar.yourjob.system.permission.models.Role;

public interface RoleDAO extends DAO<Role, Long> {

    List<Role> findAllByOrder(String order);

    List<Role> findByName(String name);

    List<Role> findByDepartments(List<String> departments);

    List<String> findDepartments();

    List<Role> findByKeywords(String keywords);

    List<String> findDepartmentsByKeywords(String keywords);

    List<Role> findCreative(boolean isCreative);

    List<Role> findRolesBykeyWords(String keywords);
    
    Role findOneByNameAndSysName(String name, String sysName);
}
