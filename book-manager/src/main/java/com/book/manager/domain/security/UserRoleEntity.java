package com.book.manager.domain.security;

import com.book.manager.base.BaseEntity;

/**
 * 用户-角色关系表
 * Created by teela on 2017/4/17.
 */
public class UserRoleEntity extends BaseEntity {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 角色ID
     */
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
