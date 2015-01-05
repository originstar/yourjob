package cn.originstar.yourjob.system.user.daos;

import java.util.List;

import cn.originstar.yourjob.core.dao.DAO;
import cn.originstar.yourjob.system.user.models.User;
import cn.originstar.yourjob.system.user.models.UserSocial;

public interface UserSocialDAO extends DAO<UserSocial, Long> {

    List<UserSocial> findByUser(User user);

}
