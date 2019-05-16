package com.book.manager.domain.security;


import com.book.manager.base.BaseEntity;

import java.util.Date;

/**
 *
 * TODO(对应表pub_resources).
 * @ClassName: ResourceEntity
 * @author Johnny_L_Q
 */
public class ResourceEntity extends BaseEntity {
    // 资源名称
    private String resourceName;

    // 资源 ID
    private Integer id ;

    // 资源地址
    private String resourceUrl;

    // 资源描述
    private String resourceDesc;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 资源优先权
     */
    private Integer priority;

    /**
     * 是否被禁用0 禁用 1正常
     */
    private Integer enabled;

    /**
     * 是否是超级权限 0不是 1是
     */
    private Integer isSys;

    /**
     * 父级菜单 id
     */
    private Integer parentId ;

    /**
     * 菜单级别
     */
    private Integer resourceLevel ;

    // 权限对象
    private AuthorityEntity authorityEntity ;

    // 权限资源对象
    private AuthorityResourceEntity authorityResourceEntity ;

    private Date createDate; // 创建日期

    //非数据库字段
    private Integer[] ids;
    private Boolean isChecked;
    private Integer authorityId ; // 权限id
    private String authorityDesc ; // 权限描述


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
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setResourceLevel(Integer resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    public Integer getResourceLevel() {
        return resourceLevel;
    }

    public void setAuthorityEntity(AuthorityEntity authorityEntity) {
        this.authorityEntity = authorityEntity;
    }

    public AuthorityEntity getAuthorityEntity() {
        return authorityEntity;
    }

    public AuthorityResourceEntity getAuthorityResourceEntity() {
        return authorityResourceEntity;
    }

    public void setAuthorityResourceEntity(AuthorityResourceEntity authorityResourceEntity) {
        this.authorityResourceEntity = authorityResourceEntity;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "resourceName='" + resourceName + '\'' +
                ", id=" + id +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", resourceDesc='" + resourceDesc + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", priority=" + priority +
                ", enabled=" + enabled +
                ", isSys=" + isSys +
                ", parentId=" + parentId +
                ", resourceLevel=" + resourceLevel +
                ", createDate=" + createDate +
                '}';
    }
}
