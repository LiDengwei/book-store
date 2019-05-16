package com.book.manager.api.service;


import com.book.manager.domain.security.RoleEntity;

import java.io.Serializable;
import java.util.List;

/**
 * TODO(角色控制).
 * @ClassName: RoleService
 * @author Johnny_L_Q
 */
public interface RoleService{

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
     * TODO(根据角色名称获取到角色集合).
     * @param roleName 角色名称
     * @return 角色
     */
    RoleEntity getByRoleName(String roleName);

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
     * TODO(赋角色信息给用户).
     * @param userId 用户ID
     * @param roleIds 角色ID集合字符串
     * @return 是否成功
     */
    Boolean giveRoleToUser(Integer userId, String roleIds);

    Integer judgeUserRole(String userName) ;
}
