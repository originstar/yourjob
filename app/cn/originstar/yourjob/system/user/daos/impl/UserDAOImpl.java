package cn.originstar.yourjob.system.user.daos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import play.Play;
import cn.originstar.yourjob.base.statable.AbstractStatableDAO;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.permission.models.Role;
import cn.originstar.yourjob.system.user.daos.UserDAO;
import cn.originstar.yourjob.system.user.models.User;

@Repository
public class UserDAOImpl extends AbstractStatableDAO<User> implements UserDAO {

    @Override
    public User authenticate(String emailOrUsername, String password) {
        String hql = "from User where (email = :emailOrUsername or username = :emailOrUsername) and password = :password";
        TypedQuery<User> query = createQuery(hql).setParameter("emailOrUsername", emailOrUsername).setParameter("password", password);
        return getSingleResultOrNull(query);
    }

    @Override
    public User findByEmailOrUsername(String emailOrUsername) {
        String hql = "from User where email = :emailOrUsername or username = :emailOrUsername";
        TypedQuery<User> query = createQuery(hql).setParameter("emailOrUsername", emailOrUsername);
        return getSingleResultOrNull(query);
    }

    @Override
    public User findByUsername(String username) {
        String hql = "from User where username = :username";
        TypedQuery<User> query = createQuery(hql).setParameter("username", username);
        return getSingleResultOrNull(query);
    }

    @Override
    public List<User> findByOrganizationId(Long organizationId) {
        if (organizationId == null) {
            return new ArrayList<User>();
        }
        String hql = "from User where organization.id = :organizationId order by firstName asc, lastName asc, username asc, email asc";
        TypedQuery<User> query = createQuery(hql).setParameter("organizationId", organizationId);
        return query.getResultList();
    }

    @Override
    public List<User> findByOrganizationIds(List<Long> organizationIds) {
        if (organizationIds == null || organizationIds.size() == 0) {
            return new ArrayList<User>();
        }
        String hql = "from User where organization.id in (:organizationIds) order by firstName asc, lastName asc, username asc, email asc";
        TypedQuery<User> query = createQuery(hql).setParameter("organizationIds", organizationIds);
        return query.getResultList();
    }

    @Override
    public List<User> findBykeyWords(String keyWord) {
        if (keyWord == null) {
            return new ArrayList<User>();
        }
        return createQuery("from User where firstName like :keywords or lastName like :keywords ", User.class).setParameter("keywords", "%" + keyWord.trim() + "%").getResultList();
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.equals("")) {
            return null;
        }
        return getSingleResultOrNull(createQuery("from User where email = :email", User.class).setParameter("email", email));
    }

    @Override
    public List<User> findCreativeBykeyWords(String keyWord, List<Long> roleIds) {
        if (keyWord == null || roleIds == null || roleIds.size() == 0) {
            return new ArrayList<User>();
        }

        return createQuery("from User where (firstName like :keywords or lastName like :keywords) and systemRole.id in (:roleIds) order by systemRole.id, organization.id",
                User.class).setParameter("keywords", "%" + keyWord.trim() + "%").setParameter("roleIds", roleIds).getResultList();
    }

    @Override
    public List<User> findProducers() {
        String keyWord = "Producer";
        return createQuery("from User where systemRole.name like :keywords order by lastName",
                User.class).setParameter("keywords", "%" + keyWord + "%").getResultList();
    }

    @Override
    public List<User> findOnline() {
        return createQuery("from User where timediff(NOW(), lastActivityTime) <= Time(:onlineTime)", User.class).setParameter("onlineTime",
                Play.application().configuration().getString("yourjob.online.time")).getResultList();
    }

    @Override
    public List<User> findAutoAssignByClient(Organization organization) {
        if (organization == null) {
            return new ArrayList<User>();
        }
        return createQuery(
                "select u from User u join u.applicableOrganizations o where o.id = :organizationId order by u.updatedOn desc, u.firstName asc, u.lastName asc, u.username asc, u.email asc",
                User.class).setParameter("organizationId", organization.getId()).getResultList();
    }

    @Override
    public List<User> findByOrder(List<Long> ids, String order) {
        if (ids != null && ids.size() == 0) {
            return new ArrayList<User>();
        }
        if (ids == null) {
            return createQuery("from User order by " + order, User.class).getResultList();
        } else {
            return createQuery("from User where id in (:ids) order by " + order, User.class).setParameter("ids", ids).getResultList();
        }
    }

    @Override
    public List<User> findByRole(Role role) {
        if (role == null) {
            return new ArrayList<User>();
        }
        return createQuery("from User where systemRole.id = :roleId order by firstName asc, lastName asc, username asc, email asc", User.class)
                .setParameter("roleId", role.getId()).getResultList();
    }

    @Override
    public User findSystemAdmin() {
        return findByUsername(Play.application().configuration().getString("yourjob.admin.username", "Yonggang Yuan"));
    }

    @Override
    public List<User> findBykeyWordsAndOrganizations(String keyWord, List<Organization> orgs) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findTrashed() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findPurged() {
        // TODO Auto-generated method stub
        return null;
    }

}
