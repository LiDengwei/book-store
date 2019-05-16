package com.book.manager.domain.security;

import com.book.manager.base.BaseEntity;

import java.util.Date;

/**
 * 权限-资源关系表
 * Created by teela on 2017/4/17.
 */
public class AuthorityResourceEntity extends BaseEntity {
    /**
     * 权限ID
     */
    private Integer authorityId;
    /**
     * 资源ID
     */
    private Integer resourceId;

    private Date createDate; // 创建日期

    private Integer ischecked; //是否被选中 0：未被选中 1：被选中

    public enum ISCHECKEDENUM{
        //0：未被选中 1：被选中
        not_checked(0,"未被选中"),checked(1,"被选中");

        Integer value;
        String remark;
        private ISCHECKEDENUM(Integer ischecked,String remarkStr){
            value = ischecked;
            remark = remarkStr;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIschecked() {
        return ischecked;
    }

    public void setIschecked(Integer ischecked) {
        this.ischecked = ischecked;
    }
}
