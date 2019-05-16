package com.book.manager.dao.security;

import com.book.manager.domain.security.RoleEntity;
import com.book.manager.domain.security.UserRoleEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * TODO(角色管理).
 * @ClassName: IRoleDAO
 * @author Johnny_L_Q
 */
public interface RoleDao {


    /**
     *
     * TODO(根据用户ID获取到角色集合).
     * @param userId 用户ID
     * @return 角色集合
     */
    List<RoleEntity> getRolesByUserId(Integer userId);

    /**
     *
     * TODO(根据角色ID获取到角色集合).
     * @param roleId 角色ID
     * @return 角色
     */
    RoleEntity getById(Integer roleId);

    /**
     *
     * TODO(根据角色获取到角色集合).
     * @param roleEntity 角色
     * @return 角色集合
     */
    List<RoleEntity> selectByPage(RoleEntity roleEntity);

    /**
     *
     * TODO(计算角色记录数).
     * @param roleEntity 角色
     * @return 角色集合
     */
    int countByPage(RoleEntity roleEntity);

    /**
     *
     * TODO(插入一条角色数据到库).
     * @param roleEntity 角色
     * @return Serializable
     */
    Integer insert(RoleEntity roleEntity);

    /**
     *
     * TODO(插入一条角色数据到库).
     * @param roleEntity 角色
     * @return Serializable
     */
    Serializable insertSelective(RoleEntity roleEntity);

    /**
     *
     * TODO(根据角色主键ID修改数据).
     * @param roleEntity 角色
     * @return 影响条数
     */
    int updateByPrimaryKeySelective(RoleEntity roleEntity);

    /**
     *
     * TODO(删除角色信息).
     * @param roleEntity 角色
     * @return 影响条数
     */
    int delete(RoleEntity roleEntity);

    /**
     *
     * TODO(按用户ID删除用户角色关系信息).
     * @param userId 用户ID
     * @return 影响条数
     */
    int deleteByUserIdFromRelation(Integer userId);

    /**
     *
     * TODO(插入一条用户角色关系信息).
     * @param userRoleEntity 用户角色关系pojo
     * @return 影响条数
     */
    int insertToRelation(UserRoleEntity userRoleEntity);

    Integer judgeUserRole(Map<String, String> param) ;
}
