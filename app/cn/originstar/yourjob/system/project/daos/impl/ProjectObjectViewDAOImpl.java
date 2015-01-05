package cn.originstar.yourjob.system.project.daos.impl;


import java.util.List;

import cn.originstar.yourjob.core.db.dao.AbstractDAO;
import cn.originstar.yourjob.system.project.daos.ProjectObjectViewDAO;
import cn.originstar.yourjob.system.project.models.ProjectObject;
import cn.originstar.yourjob.system.project.models.ProjectObjectView;
import cn.originstar.yourjob.system.user.models.User;

public class ProjectObjectViewDAOImpl extends AbstractDAO<ProjectObjectView, Long> implements ProjectObjectViewDAO {

    @Override
    public List<ProjectObjectView> findBySubject(ProjectObject projectObject) {
        return createQuery("from ProjectObjectView where subject.id = :subjectId", ProjectObjectView.class).setParameter("subjectId", projectObject.getId()).getResultList();
    }

    @Override
    public List<ProjectObjectView> findBySubjectAndUser(ProjectObject projectObject, User user) {
        return createQuery("from ProjectObjectView where subject.id = :subjectId and user.id = :userId", ProjectObjectView.class).setParameter("subjectId", projectObject.getId())
                .setParameter("userId", user.getId()).getResultList();
    }

}
