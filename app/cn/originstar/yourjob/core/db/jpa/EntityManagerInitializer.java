package cn.originstar.yourjob.core.db.jpa;


/**
 * Initialize an EntityManager after it is created
 * 
 * @author Yonggang Yuan
 */
public interface EntityManagerInitializer {

    void initialize();

}
