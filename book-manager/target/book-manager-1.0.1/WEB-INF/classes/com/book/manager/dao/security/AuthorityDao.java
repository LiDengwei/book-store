package com.book.manager.dao.security;

import com.book.manager.domain.security.AuthorityEntity;
import com.book.manager.domain.security.AuthorityResourceEntity;
import com.book.manager.domain.security.RoleAuthorityEntity;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;


/**
 *
 * TODO(权限管理).
 * @ClassName: AuthorityDAO
 * @author Johnny_L_Q
 */

public interface AuthorityDao {

    /**
     *
     * TODO(获取所有权限列表).
     * @return 权限集合
     */
    List<AuthorityEntity> getAllAuthoritys();

    /**
     *
     * TODO(根据权限ID获取权限).
     * @return 权限
     */
    AuthorityEntity getById(Integer authorityId);

    /**
     *
     * TODO(选择性获取权限).
     * @return 权限集合
     */
    List<AuthorityEntity> selectByPage(AuthorityEntity authorityEntity, RowBounds rowBounds);

    /**
     *
     * TODO(根据用户ID获取权限).
     * @return 权限集合
     */
    List<AuthorityEntity> getAuthoritiesByUserId(String userAccount);

    List<AuthorityEntity> getAuthoritiesByRoleId(Map<String, Integer> param);

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
     * TODO(根据角色ID删除权限信息).
     * @return
     */
    int deleteByRoleId(Integer roleId);

    /**
     *
     * TODO(插入一条角色权限关系到角色权限表).
     * @return
     */
    int insertRoleAuth(RoleAuthorityEntity entity);

    /**
     * 获取所以角色公共的权限
     * @return
     */
    public List<AuthorityEntity> getAuthorityPublic() ;

    /**
     * 添加角色权限
     * @return
     */
    public int batchInsertRoleAuthority(List<RoleAuthorityEntity> roleAuthorityEntities) ;

    /**
     * 跟新角色权限
     * @return
     */
    public int updateRoleAuthority(RoleAuthorityEntity roleAuthorityEntity) ;

    public RoleAuthorityEntity getRoleAuthority(RoleAuthorityEntity roleAuthorityEntity) ;

    /**
     * TODO(给权限添加资源）
     * @param list
     * @return
     */
    public int giveResourcesToAuth(List<AuthorityResourceEntity> list) ;

    /**
     * 修改权限资源（取消选中）
     * @param updateResource
     * @return
     */
    public int updateAuthoritiesResourcesNotChecked(List<AuthorityResourceEntity> updateResource) ;

    /**
     * 修改权限资源（选中）
     * @param updateResource
     * @return
     */
    public int updateAuthoritiesResourcesChecked(List<AuthorityResourceEntity> updateResource) ;

    /**
     * TODO(查询权限资源）
     * @param list
     * @return
     */
    public List<Integer> queryResourceId(List<Integer> list) ;

}
