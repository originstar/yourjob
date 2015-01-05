package cn.originstar.yourjob.system.organization.daos.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cn.originstar.yourjob.core.dao.AbstractDAO;
import cn.originstar.yourjob.system.organization.daos.OrganizationPrivateNoteDAO;
import cn.originstar.yourjob.system.organization.models.OrganizationPrivateNote;

@Repository
public class OrganizationPrivateNoteDAOImpl extends AbstractDAO<OrganizationPrivateNote, Long> implements OrganizationPrivateNoteDAO {

    /**
     * 根据createBy 和 target 查询
     */
    @Override
    public List<OrganizationPrivateNote> findByTarget(Long targetId) {
        return createQuery("from OrganizationPrivateNote where target.id = :targetId").setParameter("targetId", targetId).getResultList();
    }

    /**
     * 根据createBy 和 target 查询
     */
    @Override
    public OrganizationPrivateNote findByCreatedByAndTarget(Long createdById, Long targetId) {
        String hql = "from OrganizationPrivateNote where createdBy.id = :createdById and target.id = :targetId";
        TypedQuery<OrganizationPrivateNote> query = createQuery(hql).setParameter("createdById", createdById).setParameter("targetId", targetId);
        return getSingleResultOrNull(query);
    }

}
