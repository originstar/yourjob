package cn.originstar.yourjob.framework.multitenancy;

import java.io.File;

import org.hibernate.Session;

import play.Play;

import cn.originstar.yourjob.core.db.jpa.JPA;
import cn.originstar.yourjob.system.common.models.Tenant;

/**
 * Helper class for MultiTenancy operations
 * 
 * @author Yonggang Yuan
 */
public class MultiTenancyHelper {

    static ThreadLocal<Tenant> currentTenant = new ThreadLocal<Tenant>();

    public static boolean isMultiTenancyEnabled() {
        return Play.application().configuration().getBoolean("yourjob.multitenancy.enabled", false);
    }

    public static void setCurrentTenant(Tenant tenant) {
        currentTenant.set(tenant);
    }

    public static Tenant getCurrentTenant() {
        Tenant tenant = currentTenant.get();
        if (tenant == null) {
            throw new RuntimeException("No tenant bound to this thread.");
        }
        return tenant;
    }

    public static boolean isCurrentTenantSet() {
        return currentTenant.get() != null;
    }

    public static Long getCurrentTenantId() {
        return getCurrentTenant().getId();
    }

    public static void enableTenantFilter() {
        ((Session) JPA.em().getDelegate()).enableFilter("tenant").setParameter("tenantId", getCurrentTenantId());
    }

    public static void disableTenantFilter() {
        ((Session) JPA.em().getDelegate()).disableFilter("tenant");
    }

    /**
     * delete folder
     * 
     * @param folder
     */
    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

}
