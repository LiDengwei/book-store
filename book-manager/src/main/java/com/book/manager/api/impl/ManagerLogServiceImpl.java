package com.book.manager.api.impl;

import com.book.manager.api.service.ManagerLogService;
import com.book.manager.dao.security.ManagerLogDao;
import com.book.manager.domain.security.ManagerLogEntity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by teela on 2018/5/12.
 */
@Service("managerLogService")
public class ManagerLogServiceImpl implements ManagerLogService {

    @Resource
    private ManagerLogDao managerLogDao;


    @Override
    public int insert(ManagerLogEntity managerLogEntity) {
        return managerLogDao.insert(managerLogEntity);
    }

    @Override
    public int updateByPrimaryKeySelective(ManagerLogEntity managerLogEntity) {
        return managerLogDao.updateByPrimaryKeySelective(managerLogEntity);
    }

    @Override
    public int delete(Integer id) {
        return managerLogDao.delete(id);
    }

    @Override
    public ManagerLogEntity getById(Integer id) {
        return managerLogDao.getById(id);
    }

    @Override
    public List<ManagerLogEntity> selectByPage(ManagerLogEntity managerLogEntity) {
        if (managerLogEntity==null){
            managerLogEntity=new ManagerLogEntity();
            managerLogEntity.setPage(1);
            managerLogEntity.setPageSize(20);
        }
        int offset=(managerLogEntity.getPage()-1)*managerLogEntity.getPageSize();
        return managerLogDao.selectByPage(managerLogEntity,new RowBounds(offset,managerLogEntity.getPageSize()));
    }

    @Override
    public int countByPage(ManagerLogEntity managerLogEntity) {
        return managerLogDao.countByPage(managerLogEntity);
    }
}
