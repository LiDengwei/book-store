package com.book.manager.api.service;

import com.book.manager.domain.security.ManagerLogEntity;

import java.util.List;

/**
 * Created by teela on 2018/5/12.
 */
public interface ManagerLogService {

    int insert(ManagerLogEntity managerLogEntity);

    int updateByPrimaryKeySelective(ManagerLogEntity managerLogEntity);

    int delete(Integer id);

    ManagerLogEntity getById(Integer id);

    List<ManagerLogEntity> selectByPage(ManagerLogEntity managerLogEntity);

    int countByPage(ManagerLogEntity managerLogEntity);

}
