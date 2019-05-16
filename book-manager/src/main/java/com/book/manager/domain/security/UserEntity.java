package com.book.manager.domain.security;

import com.book.manager.api.service.RoleService;
import com.book.manager.api.service.UserEntityService;
import com.book.manager.base.BaseEntity;
import com.book.manager.controller.WebContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


/** 
 * TODO(用户实体类).
 * @ClassName: UserEntity
 * @author Johnny_L_Q
 */
public class UserEntity extends BaseEntity implements UserDetails {

    @Autowired
    private UserEntityService userService;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 密码.
     */
    private String password;

    /**
     * 备注.
     */
    private String userDesc;

    /**
     * 登录用户名(登录号).
     */
    private String userAccount;


    /**
     * 状态 0 ：禁用  1 ：正常.
     */
    private Integer status;

    
    /**
     * 标识是不是超级用户 0不是 1是
     */
    private Integer isSys;

    /**u
     * 用户的语言
     */
    private String userLanguage;
    
    //非数据库字段
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();
    private Set<GrantedAuthority> grantedAuthorities;
    private String confirmPassword;

    public enum StatusEnum{
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private StatusEnum(String i, String remark){
            this.value=Integer.parseInt(i);
            this.remark = remark ;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }

    public enum UserLangrageEnum{
        //状态 CN ：中文  EN ：英文.
        CN("CN","中文"),
        EN("EN","英文");
        String value;
        String remark ;
        public String getValue(){
            return value;
        }
        public void setValue(String value){
            this.value=value;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private UserLangrageEnum(String i, String remark){
            this.value=i;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        private IsSysEnum(String i, String remark){
            this.value=Integer.parseInt(i);
            this.remark = remark ;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }
    /**
     * 
     * TODO(所有权限).
     * @return grantedAuthorities
     */
    public Set<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @param grantedAuthorities 
     */
    public void setGrantedAuthorities(Set<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }


    /**
     * 获取用户权限集合,权限使用GrantedAuthority接口表示，框架中有它的实现类 
     * GrantedAuthorityImpl,只需要把角色的名称放入即可 (non-Javadoc).
     * @return list 集合
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//        for (RoleEntity role : roles) {
//            list.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        //得到用户权限
        RoleService roleService = WebContextUtils.getBean("roleService") ;
        List<RoleEntity> list = roleService.getRolesByUserId(getId());
        for (int i = 0; i < list.size(); i++) {
            auths.add(new GrantedAuthorityImpl(list.get(i).getRoleName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    //直接返回true，表示没有过期 (non-Javadoc)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //直接返回true，表示没有锁定 (non-Javadoc) 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否禁用 (non-Javadoc) 
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @return roles
     */
    public List<RoleEntity> getRoles() {
        return roles;
    }

    /**
     * 
     * TODO(这里用一句话描述这个方法的作用).
     * @param roles 
     */
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return super.equals(arg0);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }


    public UserEntityService getUserService() {
        return userService;
    }


    public void setUserService(UserEntityService userService) {
        this.userService = userService;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }
}
