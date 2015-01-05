package cn.originstar.yourjob.framework.statable;

import cn.originstar.yourjob.core.db.jpa.EntityManagerInitializer;

public class StatableEntityManagerInitialier implements EntityManagerInitializer {

    private static StatableEntityManagerInitialier instance = new StatableEntityManagerInitialier();

    public static EntityManagerInitializer instance() {
        return instance;
    }

    @Override
    public void initialize() {
        StatableHelper.enableStatbleFilter();
    }

}
