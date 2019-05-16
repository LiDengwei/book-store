package com.book.manager.domain.security;

import com.book.manager.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员日志
 * Created by teela on 2018/5/12.
 */
public class ManagerLogEntity extends BaseEntity implements Serializable {

    private Integer id;
    private String manager;//管理员
    private String username;//用户
    private String info;//日志信息
    private Integer logType;//日志类型 1 信息新增 2 信息修改 3 用户冻结 4 用户解冻
    private Date createAt;//日志创建时间
    private Date updateAt;//日志创建时间

    // 非数据库字段
    private String createAtStr ; // 创建时间字符串
    private String updateAtStr ; // 修改时间字符串

    public enum LogTypeEnum{
        //日志类型 1 信息新增 2 信息修改 3 用户冻结 4 用户解冻
        INSERT(1,"新增"),UPDATE(2,"修改"),LOCKED(3,"用户冻结"),UNLOCKED(4,"用户解冻"),
        PROBLEM_FEED_BACK(5,"问题回复"),DELETE(6,"删除"),MANAGER_TRANSACTION(7,"后台转账");
        private Integer value;
        private String remark ;
        LogTypeEnum(int value,String remark){
            this.value = value;
            this.remark = remark ;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRemark() {
            return remark;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateAtStr() {
        return createAtStr;
    }

    public String getUpdateAtStr() {
        return updateAtStr;
    }

    public void setCreateAtStr(String createAtStr) {
        this.createAtStr = createAtStr;
    }

    public void setUpdateAtStr(String updateAtStr) {
        this.updateAtStr = updateAtStr;
    }
}
