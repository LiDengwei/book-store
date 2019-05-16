package com.book.manager.domain.security;


import com.book.manager.base.BaseEntity;

/**
 * TODO(角色实体).
 * @ClassName: Role
 * @author Johnny_L_Q
 */
public class RoleEntity extends BaseEntity {

    // 角色名称
    private String roleName;

    /**
     * 是否启用
     */
    private Integer enabled;
    
    /**
     * 是否管理权限
     */
    private Integer isSys;
    
    // 角色描述
    private String desc;

    //非数据库字段
    private Boolean isChecked=false;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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
    /**
     * TODO(这里用一句话描述这个方法的作用).
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * TODO(这里用一句话描述这个方法的作用).
     * @param roleName 角色名称.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * TODO(这里用一句话描述这个方法的作用).
     * @return 角色描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * TODO(这里用一句话描述这个方法的作用).
     * @param desc  
     */
    public void setDesc(String desc) {
        this.desc = desc;
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

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}
