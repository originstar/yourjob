package cn.originstar.yourjob.core.cache;

import java.util.Set;

/**
 * Enhanced cache interface
 * 
 * <p>
 * This interface doesn't extend Play Cache API, it works as a supplement to Play Cache API. And it should only be used when it's beyond Play Cache API.
 * </p>
 * 
 * @author Yonggang Yuan
 */
public interface EnhancedCache {

    /**
     * Get keys from cache which matches the given pattern
     * 
     * @param pattern regex
     * @return
     */
    Set<String> keys(String pattern);

}
