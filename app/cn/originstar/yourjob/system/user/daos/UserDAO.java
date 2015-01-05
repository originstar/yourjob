package cn.originstar.yourjob.system.user.daos;

import java.util.List;

import cn.originstar.yourjob.base.statable.StatableDAO;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.permission.models.Role;
import cn.originstar.yourjob.system.user.models.User;

public interface UserDAO extends StatableDAO<User> {

    /**
     * Note: we have to make sure that emails and usernames are unique (if user A's email is foo, user B's username
     * cannot be foo again!)
     * 
     * @param emailOrUsername
     * @param password
     * @return The authenticated User object
     */
    User authenticate(String emailOrUsername, String password);

    /**
     * @param emailOrUsername
     * @return The user whose email or username matches the give string
     */
    User findByEmailOrUsername(String emailOrUsername);

    /**
     * @param username
     * @return The user whose username matches the give string
     */
    User findByUsername(String username);

    /**
     * @param organizationId
     * @return The users whose organization.id matches the give long
     */
    List<User> findByOrganizationId(Long organizationId);

    List<User> findByOrganizationIds(List<Long> organizationIds);

    /**
     * @param keyWord
     * @return The user whose firstName or lastName matches the give string
     */
    List<User> findBykeyWords(String keyWord);

    User findByEmail(String email);

    List<User> findBykeyWordsAndOrganizations(String keyWord, List<Organization> orgs);

    List<User> findCreativeBykeyWords(String keyWord, List<Long> roleIds);

    List<User> findProducers();

    List<User> findOnline();

    List<User> findTrashed();

    List<User> findPurged();

    List<User> findAutoAssignByClient(Organization organization);

    List<User> findByOrder(List<Long> ids, String order);

    List<User> findByRole(Role role);
    
    User findSystemAdmin();

}
