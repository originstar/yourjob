package cn.originstar.yourjob.core.cache;

import java.util.Set;

/**
 * Enhanced cache utility class
 * 
 * <p>
 * Works as a supplement to {@link play.cache.Cache}, more powerful.
 * </p>
 * 
 * @author Yonggang Yuan
 */
public class CacheUtil {

    private static EnhancedCache cache = null;

    /**
     * Set the cache implementation, done by the plugin
     * 
     * @param cache
     */
    static void setCache(EnhancedCache cache) {
        CacheUtil.cache = cache;
    }

    /**
     * Get keys from cache which matches the given pattern
     * 
     * @param pattern regex
     * @return
     */
    public Set<String> keys(String pattern) {
        return cache.keys(pattern);
    }

}
