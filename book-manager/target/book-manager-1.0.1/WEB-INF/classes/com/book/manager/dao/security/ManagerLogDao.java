package com.book.manager.dao.security;

import com.book.manager.domain.security.ManagerLogEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by teela on 2018/5/12.
 */
public interface ManagerLogDao {

    int insert(ManagerLogEntity managerLogEntity);

    int updateByPrimaryKeySelective(ManagerLogEntity managerLogEntity);

    int delete(Integer id);

    ManagerLogEntity getById(Integer id);

    List<ManagerLogEntity> selectByPage(ManagerLogEntity managerLogEntity, RowBounds rowBounds);

    int countByPage(ManagerLogEntity managerLogEntity);

}
