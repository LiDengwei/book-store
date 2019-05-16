package com.book.manager.api.service;

import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.ResourceTreeEntity;
import com.book.manager.domain.security.RoleAuthorityEntity;

import java.util.List;


/**
 *
 * TODO(这里用一句话描述这个类的职责).
 * @ClassName: AuthorityService
 * @author Johnny_L_Q
 */
public interface AuthorityService {

    /**
     *
     * TODO(获取所有权限列表).
     * @return 权限集合
     */
    List<AuthorityEntity> getAllAuthoritys();

    /**
     *
     * TODO(根据权限ID获取权限).
     * @return 权限集合
     */
    AuthorityEntity getById(Integer authorityId);


    /**
     *
     * TODO(根据权限名称获取权限).
     * @return 权限集合
     */
    AuthorityEntity getByAuthName(String authorityName);

    /**
     *
     * TODO(选择性获取权限).
     * @return 权限集合
     */
    List<AuthorityEntity> selectByPage(AuthorityEntity authorityEntity);

    /**
     *
     * TODO(根据用户ID获取权限).
     * @return 权限集合
     */
    List<AuthorityEntity> getAuthoritiesByUserId(String userAccount);


    /**
     *
     * TODO(根据角色ID获取权限).
     * @return 权限集合
     */
    List<AuthorityEntity> getAuthoritiesByRoleId(Integer roleId, Integer issy);

    /**
     *
     * TODO(计算权限记录数).
     * @return 权限集合
     */
    int countByPage(AuthorityEntity authorityEntity);

    /**
     *
     * TODO(插入一条权限数据到库).
     * @return
     */
    Integer insert(AuthorityEntity authorityEntity);

    /**
     *
     * TODO(选择性修改权限信息).
     * @return
     */
    int updateByPrimaryKeySelective(AuthorityEntity authorityEntity);

    /**
     *
     * TODO(删除权限信息).
     * @return
     */
    int delete(AuthorityEntity authorityEntity);

    /**
     *
     * TODO(赋权限信息给某个角色).
     * @param roleId 角色ID
     * @param authIds 权限ID数组字符串
     * @return
     */
    Boolean giveAuthToRole(Integer roleId, String authIds);

    /**
     * 获取所以角色公共的权限
     * @return
     */
    public List<AuthorityEntity> getAuthorityPublic() ;

    /**
     *添加角色权限
     * @return
     */
    public int batchInsertRoleAuthority(List<RoleAuthorityEntity> roleAuthorityEntities) ;

    /**
     * 更新角色权限
     * @param roleId 角色 id
     * @param authorityId 权限 id
     * @return
     */
    public int updateRoleAuthority(Integer roleId, Integer authorityId, boolean issy) ;

    /**
     * 查找角色是否拥有权限
     * @return
     */
    public RoleAuthorityEntity getRoleAuthority(Integer roleId, Integer autorityId) ;

    /**
     * TODO(给权限添加资源）
     * @param list
     * @return
     */
    public boolean giveResourcesToAuth(List<ResourceTreeEntity> list, Integer authId) ;

    /**
     * TODO(查询权限资源）
     * @param list
     * @return
     */
    public List<Integer> queryResourceId(List<Integer> list) ;

}
