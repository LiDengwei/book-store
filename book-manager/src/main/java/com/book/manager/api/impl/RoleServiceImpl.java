package com.book.manager.api.impl;

import com.book.manager.api.service.RoleService;
import com.book.manager.dao.security.RoleDao;
import com.book.manager.domain.security.RoleEntity;
import com.book.manager.domain.security.UserRoleEntity;
import com.book.manager.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * TODO(角色管理).
 * @ClassName: RoleServiceImpl
 * @author Johnny_L_Q
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
	private RoleDao roleDao;

	@Override
	public List<RoleEntity> getRolesByUserId(Integer userId) {
		return roleDao.getRolesByUserId(userId);
	}

	@Override
	public RoleEntity getById(Integer roleId) {
		return roleDao.getById(roleId);
	}

	@Override
	public RoleEntity getByRoleName(String roleName) {
		RoleEntity temp=new RoleEntity();
		temp.setRoleName(roleName);
		List<RoleEntity> entities=roleDao.selectByPage(temp);
		if (entities!=null && entities.size()>0){
			return entities.get(0);
		}
		return null;
	}

	@Override
	public List<RoleEntity> selectByPage(RoleEntity roleEntity) {
		return roleDao.selectByPage(roleEntity);
	}

	@Override
	public int countByPage(RoleEntity roleEntity) {
		return roleDao.countByPage(roleEntity);
	}

	@Override
	public Integer insert(RoleEntity roleEntity) {
		return roleDao.insert(roleEntity);
	}

	@Override
	public Serializable insertSelective(RoleEntity roleEntity) {
		return roleDao.insertSelective(roleEntity);
	}

	@Override
	public int updateByPrimaryKeySelective(RoleEntity roleEntity) {
		return roleDao.updateByPrimaryKeySelective(roleEntity);
	}

	@Override
	public int delete(RoleEntity roleEntity) {
		return roleDao.delete(roleEntity);
	}

	@Override
	public Boolean giveRoleToUser(Integer userId, String roleIds) {
		if (userId!=null && userId>0){
			if (roleIds!=null && roleIds!=""){
				Integer[] ids= CommonUtil.stringToInteger(roleIds);
				if (ids!=null && ids.length>0){
					int delRows=roleDao.deleteByUserIdFromRelation(userId);
					if (delRows>=0){
						UserRoleEntity entity=null;
						for (Integer id : ids) {
							entity=new UserRoleEntity();
							entity.setUserId(userId);
							entity.setRoleId(id);
							roleDao.insertToRelation(entity);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Integer judgeUserRole(String userName) {
		Map<String,String> param = new HashMap<>() ;
		param.put("userName",userName) ;
		return roleDao.judgeUserRole(param) ;
	}
}
