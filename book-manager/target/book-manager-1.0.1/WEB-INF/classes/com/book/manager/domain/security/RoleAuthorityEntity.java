package com.book.manager.domain.security;

import com.book.manager.base.BaseEntity;

import java.util.Date;

/**
 * 角色-权限关系表
 * Created by teela on 2017/4/17.
 */
public class RoleAuthorityEntity extends BaseEntity {
    /**
     * 角色权限id
     */
    private Integer id ;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 权限ID
     */
    private Integer authorityId;

    /**
     * 是否开启角色权限
     */
    private boolean issy ;

    private Date createDate; // 创建日期

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public boolean isIssy() {
        return issy;
    }

    public void setIssy(boolean issy) {
        this.issy = issy;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
