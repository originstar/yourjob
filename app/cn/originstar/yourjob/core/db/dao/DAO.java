package cn.originstar.yourjob.core.db.dao;

import java.io.Serializable;
import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

/**
 * Enhancement of the GenericDAO interface.
 *
 * @author Yonggang Yuan
 *
 * @param <T>
 * @param <ID>
 */
public interface DAO<T, ID extends Serializable> extends GenericDAO<T, ID> {

    List<T> find(List<ID> idList);

    List<T> save(List<T> entityList);

    void remove(List<T> entityList);

    void removeByIds(List<ID> idList);

}
