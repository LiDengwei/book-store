package com.book.manager.domain.security;


import com.book.manager.base.BaseEntity;

import java.util.Date;

/**
 * TODO(权限实体类  对应SECURITY_AUTHORITY).
 * @ClassName: AuthorityEntity
 * @author Johnny_L_Q
 */
public class AuthorityEntity extends BaseEntity {

    // 权限 id
    private Integer id ;
    // 权限资源
    private ResourceEntity resourceEntity ;

    // 权限描述
    private String authorityDesc;

    //权限名称
    private String authorityName;

    private Integer resourceId ; // 资源id
    private Integer resourceLevel ; // 资源级别

    /**
     * 是否禁用 0 禁用 1正常
     */
    private  Integer enabled;

    /**
     * 是否是超级用户 0不是 1是
     */
    private Integer isSys;

    /**
     * 角色权限
     */
    private RoleAuthorityEntity roleAuthorityEntity ;

    private Date createDate; // 创建日期

    //非数据库字段
    private boolean isChecked=false;

    public enum EnabledEnum{
        //状态 0 ：禁用  1 ：正常.
        NO("0","禁用"),
        YES("1","正常");
        Integer value;
        String remark ;
        public Integer getValue(){
            return value;
        }
        public void setValue(Integer value){
            this.value=value;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return remark;
        }

        private EnabledEnum(String i,String remark){
            this.value=Integer.parseInt(i);
            this.remark = remark ;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }
    public enum IsSysEnum{
        //标识是不是超级用户 0不是 1是
        NO("0","不是"),
        YES("1","是");
        Integer value;
        String remark ;
        public Integer getValue(){
            return value;
        }
        public void setValue(Integer value){
            this.value=value;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return remark;
        }

        private IsSysEnum(String i,String remark){
            this.value=Integer.parseInt(i);
            this.remark = remark ;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }


    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setRoleAuthorityEntity(RoleAuthorityEntity roleAuthorityEntity) {
        this.roleAuthorityEntity = roleAuthorityEntity;
    }

    public RoleAuthorityEntity getRoleAuthorityEntity() {
        return roleAuthorityEntity;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
