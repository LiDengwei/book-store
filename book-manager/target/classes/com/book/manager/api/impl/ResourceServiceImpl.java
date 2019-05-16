package com.book.manager.api.impl;

import com.book.manager.api.service.ResourceService;
import com.book.manager.dao.security.ResourceDao;
import com.book.manager.domain.security.AuthorityResourceEntity;
import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.ResourceTreeEntity;
import com.book.manager.util.CommonUtil;
import com.book.util.redis.JedisClient;
import com.book.util.redis.util.RedisUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO(资源管理).
 * @ClassName: ResourceServiceImpl
 * @author Johnny_L_Q
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Resource
	private ResourceDao resourceDao;
	@Autowired
	private JedisClient jedisClient;


	@Override
	public List<ResourceEntity> getAllResource() {
		return this.resourceDao.getAllResource();
	}

	@Override
	public List<ResourceTreeEntity> getAllResourceTreeEntity() {
		return resourceDao.getAllResourceTreeEntity();
	}

	@Override
	public List<ResourceEntity> getUrlByUserId(Integer userId) {
		return this.resourceDao.getUrlByUserId(userId);
	}

	@Override
	public ResourceEntity getById(Integer resourceId) {
		return resourceDao.getById(resourceId);
	}

	@Override
	public ResourceEntity getByUrl(String resourceUrl) {
		ResourceEntity temp=new ResourceEntity();
		temp.setResourceUrl(resourceUrl);
		List<ResourceEntity> entities=resourceDao.selectByPage(temp,new RowBounds(0,2));
		if (entities!=null && entities.size()>0){
			return entities.get(0);
		}
		return null;
	}

	/*
     * <p>Title: getResourcesByAuthName</p>
     * <p>Description: </p>
     * @param authName
     * @return
     * @see com.doone.wisdom.service.iface.ResourceService#getResourcesByAuthName(java.lang.String)
     */
	@Override
	public List<ResourceEntity> getResourcesByAuthName(String authName) {
		// TODO Auto-generated method stub
		return resourceDao.getResourcesByAuthName(authName);
	}

	@Override
	public List<ResourceEntity> selectByPage(ResourceEntity resourceEntity) {
		if (resourceEntity==null){
			resourceEntity=new ResourceEntity();
			resourceEntity.setPage(1);
			resourceEntity.setPageSize(20);
		}
		int offset=(resourceEntity.getPage()-1)*resourceEntity.getPageSize();
		return resourceDao.selectByPage(resourceEntity,new RowBounds(offset,resourceEntity.getPageSize()));
	}

	@Override
	public int countByPage(ResourceEntity resourceEntity) {
		return resourceDao.countByPage(resourceEntity);
	}

	@Override
	public Integer insert(ResourceEntity resourceEntity) {
		return resourceDao.insert(resourceEntity);
	}

	@Override
	public int updateByPrimaryKeySelective(ResourceEntity resourceEntity) {
		return resourceDao.updateByPrimaryKeySelective(resourceEntity);
	}

	@Override
	public int delete(ResourceEntity resourceEntity) {
		return resourceDao.delete(resourceEntity);
	}

	@Override
	public Boolean giveResourceToAuthority(Integer authId, String resourceIds) {
		if (authId!=null && authId>0){
			if (resourceIds!=null && resourceIds!=""){
				Integer[] ids= CommonUtil.stringToInteger(resourceIds);
				if (ids!=null && ids.length>0){
					int delRows=resourceDao.deleteByAuthIdFromRelation(authId);
					if (delRows>=0){
						AuthorityResourceEntity entity=null;
						for (Integer id : ids) {
							entity=new AuthorityResourceEntity();
							entity.setAuthorityId(authId);
							entity.setResourceId(id);
							resourceDao.insertToRelation(entity);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public List<ResourceEntity> getUserMenuByUserName(String userName) {
		//如果密码错误后，得到正常的状态，就删除之前key的错误记录
		String key = "MANAGER_" + userName + "_LOGIN_ERROR" ;
		String msgKey = "MANAGER_" + userName + "_MSG" ; // 用户登录异常信息
		RedisUtil.remove(jedisClient ,key);
		RedisUtil.remove(jedisClient ,msgKey);
		return resourceDao.getUserMenuByUserName(userName);
	}

	@Override
	public List<ResourceEntity> getSubMenu(Integer parentId) {
		return resourceDao.getSubMenu(parentId);
	}

	@Override
	public List<ResourceEntity> getResourceByAuthId(Integer authId) {
		return resourceDao.getResourceByAuthId(authId);
	}

	@Override
	public List<ResourceEntity> getResourceByAuthIdDoNotCareChecked(Integer authId) {
		return resourceDao.getResourceByAuthIdDoNotCareChecked(authId);
	}

	@Override
	public List<ResourceEntity> getAllAuthorityAndResource() {
		return resourceDao.getAllAuthorityAndResource();
	}

	@Override
	public ResourceEntity getResourceFather(Integer parentId) {
		return resourceDao.getResourceFather(parentId);
	}
}
