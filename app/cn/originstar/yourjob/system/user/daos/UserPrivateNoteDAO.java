package cn.originstar.yourjob.system.user.daos;

import java.util.List;

import cn.originstar.yourjob.core.dao.DAO;
import cn.originstar.yourjob.system.user.models.UserPrivateNote;

public interface UserPrivateNoteDAO extends DAO<UserPrivateNote, Long> {

    List<UserPrivateNote> findByTargetId(Long targetId);

    UserPrivateNote findByCreatedByIdAndTargetId(Long createdById, Long targetId);

    void deleteByTarget(Long targetId);

}
