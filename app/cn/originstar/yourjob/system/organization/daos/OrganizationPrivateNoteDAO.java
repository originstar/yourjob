package cn.originstar.yourjob.system.organization.daos;

import java.util.List;

import cn.originstar.yourjob.core.dao.DAO;
import cn.originstar.yourjob.system.organization.models.OrganizationPrivateNote;

public interface OrganizationPrivateNoteDAO extends DAO<OrganizationPrivateNote, Long> {

    List<OrganizationPrivateNote> findByTarget(Long targetId);

    OrganizationPrivateNote findByCreatedByAndTarget(Long createdById, Long targetId);

}
