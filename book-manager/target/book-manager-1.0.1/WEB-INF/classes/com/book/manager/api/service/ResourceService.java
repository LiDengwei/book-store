package com.book.manager.api.service;

import com.book.manager.domain.security.ResourceEntity;
import com.book.manager.domain.security.ResourceTreeEntity;

import java.util.List;


/**
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: ResourceService
 * @author Johnny_L_Q
 */
public interface ResourceService{


	/**
	 * TODO(查询所有的资源地址).
	 * @return 集合
	 */
	List<ResourceEntity> getAllResource();

	/**
	 *
	 * 获取所有资源用来后台权限生成树
	 * @return URL集合
	 */
	List<ResourceTreeEntity> getAllResourceTreeEntity();


	/**
	 * TODO(根据用户ID查询相对应的资源地址).
	 * @param userId 用户ID
	 * @return 集合
	 */
	List<ResourceEntity> getUrlByUserId(Integer userId);


	/**
	 * TODO(根据主键ID查询资源).
	 * @param resourceId 用户ID
	 * @return 集合
	 */
	ResourceEntity getById(Integer resourceId);


	/**
	 * TODO(根据主键ID查询资源).
	 * @param resourceUrl 资源URL
	 * @return 集合
	 */
	ResourceEntity getByUrl(String resourceUrl);

	/**
	 *
	 * TODO(根据权限名称获取到资源列表).
	 * @param authName 权限名称
	 * @return 资源集合
	 */
	List<ResourceEntity> getResourcesByAuthName(String authName);

	/**
	 *
	 * TODO(查询资源集合).
	 * @param resourceEntity 资源
	 * @return 资源集合
	 */
	List<ResourceEntity> selectByPage(ResourceEntity resourceEntity);

	/**
	 *
	 * TODO(查询资源记录数).
	 * @param resourceEntity 资源
	 * @return
	 */
	int countByPage(ResourceEntity resourceEntity);

	/**
	 *
	 * TODO(插入一条资源数据到库).
	 * @param resourceEntity 资源
	 * @return
	 */
	Integer insert(ResourceEntity resourceEntity);

	/**
	 *
	 * TODO(选择性修改资源信息).
	 * @param resourceEntity 资源
	 * @return
	 */
	int updateByPrimaryKeySelective(ResourceEntity resourceEntity);

	/**
	 *
	 * TODO(删除资源信息).
	 * @param resourceEntity 资源
	 * @return
	 */
	int delete(ResourceEntity resourceEntity);

	/**
	 *
	 * TODO(给权限赋资源信息).
	 * @param authId 权限ID
	 * @param resourceIds 资源ID集合字符串
	 * @return
	 */
	Boolean giveResourceToAuthority(Integer authId, String resourceIds);

	/**
	 *TODO(通过用户名查找用户所具有的权限)
	 * @param userName 用户名
	 * @return
	 */
	public List<ResourceEntity> getUserMenuByUserName(String userName) ;

	/**
	 * 查找根菜单下的所有子菜单
	 * @param parentId
	 * @return
	 */
	public List<ResourceEntity> getSubMenu(Integer parentId) ;

	/**
	 * TODO(查询权限拥有的资源)
	 * @param authId 权限 ID
	 * @return
	 */
	public List<ResourceEntity> getResourceByAuthId(Integer authId) ;

	/**
	 * 查询权限下的资源，不管是否被选中
	 * @param authId 权限 ID
	 * @return
	 */
	public List<ResourceEntity> getResourceByAuthIdDoNotCareChecked(Integer authId);

	/**
	 * TODO(查询所有权限及权限对应的资源)
	 */
	public List<ResourceEntity> getAllAuthorityAndResource() ;

	/**
	 * TODO(查询资源的父资源)
	 * @param parentId 父级ID
	 * @return
	 */
	public ResourceEntity getResourceFather(Integer parentId) ;

}
