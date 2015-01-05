package cn.originstar.yourjob.core.spring;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import play.Logger;

/**
 * Auto scans annotated entities in classpath
 * 
 * https://access.redhat.com/site/documentation/en-US/JBoss_Web_Framework_Kit/1.2/html/Spring_Developer_Guide/ch07s02s02.html
 * 
 * @author Yonggang Yuan
 */
public class AutoscanPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

    @Autowired
    private ResourcePatternResolver resourceLoader;

    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo mutablePersistenceUnitInfo) {
        Logger.debug("Scanning entities for " + mutablePersistenceUnitInfo.getPersistenceUnitName() + "...");
        try {
            Resource[] resources = resourceLoader.getResources("classpath*:cn/originstar/yourjob/**/models/*.class");
            for (Resource resource : resources) {
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                if (metadataReader.getAnnotationMetadata().isAnnotated(javax.persistence.Entity.class.getName())) {
                    Logger.trace("+ " + metadataReader.getClassMetadata().getClassName());
                    mutablePersistenceUnitInfo.addManagedClassName(metadataReader.getClassMetadata().getClassName());
                }
            }
            mutablePersistenceUnitInfo.setExcludeUnlistedClasses(true);
            Logger.debug("  " + mutablePersistenceUnitInfo.getManagedClassNames().size() + " entities detected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
