package cn.originstar.yourjob.system.common.daos;

import cn.originstar.yourjob.core.db.dao.DAO;
import cn.originstar.yourjob.system.common.models.Tenant;

public interface TenantDAO extends DAO<Tenant, Long> {

    Tenant findBySubDomain(String subDomain);

}
