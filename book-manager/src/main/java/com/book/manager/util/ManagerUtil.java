package com.book.manager.util;

import com.book.manager.base.LoginContextHolder;
import com.book.manager.domain.security.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

/**
 * Created by Administrator on 2017/7/13.
 */
public class ManagerUtil {



    // 菜单类型
    public enum MenuEnum{
        QUANTUMUSER(1,"用户记录") , BILLRECORD(2,"账单记录") , MESSAGE(3,"个人信息") , SYSTEM(4,"系统配置") ,
        USERMESSAGE(5,"用户信息") , ROLE(6,"角色列表") , AUTHORITY(7,"权限信息") ,
        RESOURCE(8,"资源信息") ,  NOTICE_INFO(9,"信息公告"),
        USERACCOUNT(10,"用户账户"),USDPUSH(11,"推送"),
        HELP_CENTER(12,"帮助中心"),LOCK_USER(13,"密码错误冻结用户");

        private Integer value ;
        private String remark;
        private MenuEnum(Integer value,String remark){
            this.value  = value ;
            this.remark = remark ;
        }

        public Integer getValue() {
            return value;
        }

        public String getRemark() {
            return remark;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }

    public static String getManagerName(){
        UserEntity userEntity = LoginContextHolder.getLoginUser() ; // 登录用户信息
        UserDetails userDetails = null ;
        if(Objects.isNull(userEntity)){
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return  userDetails.getUsername() ;
        }
        else{
            return  userEntity.getUserName() ;
        }
    }


}
