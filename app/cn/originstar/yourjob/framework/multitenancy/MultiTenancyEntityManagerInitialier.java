package cn.originstar.yourjob.framework.multitenancy;

import cn.originstar.yourjob.core.db.jpa.EntityManagerInitializer;

public class MultiTenancyEntityManagerInitialier implements EntityManagerInitializer {

    private static MultiTenancyEntityManagerInitialier instance = new MultiTenancyEntityManagerInitialier();

    public static EntityManagerInitializer instance() {
        return instance;
    }

    @Override
    public void initialize() {
        if (MultiTenancyHelper.isCurrentTenantSet()) {
            MultiTenancyHelper.enableTenantFilter();
        }
    }

}
