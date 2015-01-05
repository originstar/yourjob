package cn.originstar.yourjob.system.user.daos.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cn.originstar.yourjob.core.dao.AbstractDAO;
import cn.originstar.yourjob.system.user.daos.UserPrivateNoteDAO;
import cn.originstar.yourjob.system.user.models.UserPrivateNote;

@Repository
public class UserPrivateNoteDAOImpl extends AbstractDAO<UserPrivateNote, Long> implements UserPrivateNoteDAO {

    @Override
    public List<UserPrivateNote> findByTargetId(Long targetId) {
        return createQuery("from UserPrivateNote where target.id = :targetId").setParameter("targetId", targetId).getResultList();
    }

    @Override
    public UserPrivateNote findByCreatedByIdAndTargetId(Long createdById, Long targetId) {
        String hql = "from UserPrivateNote where createdBy.id = :createdById and target.id = :targetId";
        TypedQuery<UserPrivateNote> query = createQuery(hql).setParameter("createdById", createdById).setParameter("targetId", targetId);
        return getSingleResultOrNull(query);
    }

    @Override
    public void deleteByTarget(Long targetId) {
        remove(findByTargetId(targetId));
    }

}
