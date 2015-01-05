package cn.originstar.yourjob.system.common.daos.impl;

import cn.originstar.yourjob.core.db.dao.AbstractDAO;
import cn.originstar.yourjob.system.common.daos.TenantDAO;
import cn.originstar.yourjob.system.common.models.Tenant;

public class TenantDAOImpl extends AbstractDAO<Tenant, Long> implements TenantDAO {

    @Override
    public Tenant findBySubDomain(String subDomain) {
        return getSingleResultOrNull(createQuery("from Tenant where subDomain = :subDomain").setParameter("subDomain", subDomain));
    }

}
