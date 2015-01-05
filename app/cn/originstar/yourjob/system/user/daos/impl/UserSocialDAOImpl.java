package cn.originstar.yourjob.system.user.daos.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.originstar.yourjob.core.dao.AbstractDAO;
import cn.originstar.yourjob.system.user.daos.UserSocialDAO;
import cn.originstar.yourjob.system.user.models.User;
import cn.originstar.yourjob.system.user.models.UserSocial;

@Repository
public class UserSocialDAOImpl extends AbstractDAO<UserSocial, Long> implements UserSocialDAO {

    @Override
    public List<UserSocial> findByUser(User user) {
        return createQuery("from UserSocial where user.id = :userId").setParameter("userId", user.getId()).getResultList();
    }

}
