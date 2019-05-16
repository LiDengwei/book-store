package com.book.manager.domain.security;

import java.util.Date;

/**
 * 修改日志
 */
public class LogEntity {

    private Long id ; //  id
    private String userName ; // 用户名
    private String actionType ; // 方式  1.普通  2.回调
    private String menu ; // 菜单
    private String actionName ; // 名称  (新增，修改，冻结，解冻)
    private String url ; //  url
    private String sqlType ; // sql 方式 update  insert
    private Integer dataType ; //类型 1.用户   2.系统配置  3.权限
    private Long dataId ; // 数据 id
    private String dataSource ; // 数据源
    private String remark ; // 备注
    private Date createdTime ; // 创建时间

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

}
