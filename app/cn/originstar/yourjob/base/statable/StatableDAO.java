package cn.originstar.yourjob.base.statable;

import cn.originstar.yourjob.core.dao.DAO;


public interface StatableDAO<T extends StatableModel> extends DAO<T, Long> {


}
