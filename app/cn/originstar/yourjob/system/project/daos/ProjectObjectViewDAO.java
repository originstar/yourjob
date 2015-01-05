package cn.originstar.yourjob.system.project.daos;

import java.util.List;

import cn.originstar.yourjob.core.db.dao.DAO;
import cn.originstar.yourjob.system.project.models.ProjectObject;
import cn.originstar.yourjob.system.project.models.ProjectObjectView;
import cn.originstar.yourjob.system.user.models.User;

public interface ProjectObjectViewDAO extends DAO<ProjectObjectView, Long> {

    /**
     * find By Subject
     * 
     * @param subjectId
     * @return
     */
    List<ProjectObjectView> findBySubject(ProjectObject projectObject);

    /**
     * find By Subject and user
     * 
     * @param subjectId
     * @param userId
     * @return
     */
    List<ProjectObjectView> findBySubjectAndUser(ProjectObject projectObject, User user);

}
