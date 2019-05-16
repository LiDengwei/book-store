package com.book.service.model.user;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息表
 */
public class User implements Serializable{
    private Long id;  //id

    private String userName;  //用户名，也是book号，用户可修改一次

    private String userSystemCode;  //'用户系统识别码'

    private Integer leverl;//用户等级（VIP1）

    private Short userSource;  //'用户来源 1:本地用户'

    private Short userStatus;   //账户状态 0:注册未认证 1:正常 2:密码错误次数过多冻结 3:系统冻结

    private String salt;    //'账户随机密码'

    private String userPhoneCode;   //手机国家区号

    private String userContactWay;  //联系方式

    private String userNickName;   //昵称

    private String userMotto;   //用户签名

    private String userPhoneNumber;   //手机号码,用户可修改

    private Short userGender;  //性别 1:男,2:女

    private Integer userAge;   //用户年龄

    private String userArea;   //用户地区

    private String loginPassword;   //登录密码

    private String tradePassword;  //交易密码

    private String userHeadImage;  //用户头像

    private String loginToken;//登录token

    private String userEmail;//邮箱

    private Date createTime;   //创建时间

    private Date updateTime;  //修改时间

    private String invitePeopleUserName;   //邀请人用户名

    private Short userType;  //用户类型1:普通用户,4:系统用户

    private String myLabel;   //1,2,3,存的id是用户标签表的id

    private String remarks;  //备注

    private Integer backup1;  //备用字段1

    private String backup2;   //备用字段2

    private BigDecimal backup3;  //备用字段3



    //非数据库字段
    private String orderById;//排序
    private String createAtStr;//用户后台页面展示
    private String updateAtStr;
    private Date startCreateAt; //开始 创建时间
    private Date endCreateAt;   //结束 创建时间
    private String startAtStr; //后台查询时间 需用到时分秒
    private String endAtStr;




    /**
     * 用户来源1:app注册的用户,2:官网注册的用户'
     */
    public enum UserSourceEnum{
        APP_REGIS_USER(1,"APP注册的用户"),WEB_REGIS_USER(2,"官网注册的用户"),PLATFORM_REGIS_USER(3,"第三方平台登录注册的用户");
        private Short value;
        private String remark ;
        UserSourceEnum(int num,String remark){
            this.value = (short)num;
            this.remark = remark ;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Short getValue(){
            return value;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }

    /**
     * 账户状态 0:注册未认证,1:正常,2:登录密码错误次数超限冻结,3:安全密码错误次数超限冻结,4:用户违规冻结账号,5:系统后台其他原因冻结,6:信誉积分低于50冻结
     */
    public enum UserStatusEnum{
        REG_NOT_AUTH(0,"注册未认证"),NORMAL(1,"正常"),LOGIN_PASS_ERROR_FREEZE(2,"登录密码错误次数过多冻结"),SECURITY_PASS_ERROR_FREEZE(3,"安全密码错误次数过多冻结")
        ,USER_VIOLATION_FREEZE(4,"用户违规冻结"),SYSTEM_FREEZE(5,"系统后台其他原因冻结"),SCORE_LESS_FREEZE(6,"信誉积分低于50冻结");
        private Short value;
        private String remark ;
        UserStatusEnum(int num,String remark){
            this.value = (short)num;
            this.remark = remark ;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Short getValue(){
            return value;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
    }

    /**
     * 用户性别 1:男,2:女
     */
    public enum GenderEnum{
        MALE(1,"男"),FEMALE(2,"女");

        private Short value;
        private String remark ;
        GenderEnum(int num,String remark){
            this.value = (short)num;
            this.remark = remark ;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public short getValue(){
            return value;
        }

        @Override
        public String toString() {
            return this.getValue() + ":" + this.getRemark() ;
        }
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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserSystemCode() {
        return userSystemCode;
    }

    public void setUserSystemCode(String userSystemCode) {
        this.userSystemCode = userSystemCode == null ? null : userSystemCode.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUserPhoneCode() {
        return userPhoneCode;
    }

    public void setUserPhoneCode(String userPhoneCode) {
        this.userPhoneCode = userPhoneCode;
    }

    public String getUserContactWay() {
        return userContactWay;
    }

    public void setUserContactWay(String userContactWay) {
        this.userContactWay = userContactWay == null ? null : userContactWay.trim();
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public String getUserMotto() {
        return userMotto;
    }

    public void setUserMotto(String userMotto) {
        this.userMotto = userMotto == null ? null : userMotto.trim();
    }


    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea == null ? null : userArea.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword == null ? null : tradePassword.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getInvitePeopleUserName() {
        return invitePeopleUserName;
    }

    public void setInvitePeopleUserName(String invitePeopleUserName) {
        this.invitePeopleUserName = invitePeopleUserName == null ? null : invitePeopleUserName.trim();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(String myLabel) {
        this.myLabel = myLabel == null ? null : myLabel.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getBackup1() {
        return backup1;
    }

    public void setBackup1(Integer backup1) {
        this.backup1 = backup1;
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2 == null ? null : backup2.trim();
    }

    public BigDecimal getBackup3() {
        return backup3;
    }

    public void setBackup3(BigDecimal backup3) {
        this.backup3 = backup3;
    }

    public String getCreateAtStr() {
        return createAtStr;
    }

    public void setCreateAtStr(String createAtStr) {
        this.createAtStr = createAtStr;
    }

    public String getUpdateAtStr() {
        return updateAtStr;
    }

    public void setUpdateAtStr(String updateAtStr) {
        this.updateAtStr = updateAtStr;
    }

    public Date getStartCreateAt() {
        return startCreateAt;
    }

    public void setStartCreateAt(Date startCreateAt) {
        this.startCreateAt = startCreateAt;
    }

    public Date getEndCreateAt() {
        return endCreateAt;
    }

    public void setEndCreateAt(Date endCreateAt) {
        this.endCreateAt = endCreateAt;
    }

    public String getStartAtStr() {
        return startAtStr;
    }

    public void setStartAtStr(String startAtStr) {
        this.startAtStr = startAtStr;
    }

    public String getEndAtStr() {
        return endAtStr;
    }

    public void setEndAtStr(String endAtStr) {
        this.endAtStr = endAtStr;
    }

    public String getUserHeadImage() {
        return userHeadImage;
    }

    public void setUserHeadImage(String userHeadImage) {
        this.userHeadImage = userHeadImage;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Short getUserSource() {
        return userSource;
    }

    public void setUserSource(Short userSource) {
        this.userSource = userSource;
    }

    public Short getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Short userStatus) {
        this.userStatus = userStatus;
    }

    public Short getUserGender() {
        return userGender;
    }

    public void setUserGender(Short userGender) {
        this.userGender = userGender;
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Integer getLeverl() {
        return leverl;
    }

    public void setLeverl(Integer leverl) {
        this.leverl = leverl;
    }

    public String getOrderById() {
        return orderById;
    }

    public void setOrderById(String orderById) {
        this.orderById = orderById;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSystemCode='" + userSystemCode + '\'' +
                ", leverl=" + leverl +
                ", userSource=" + userSource +
                ", userStatus=" + userStatus +
                ", salt='" + salt + '\'' +
                ", userPhoneCode='" + userPhoneCode + '\'' +
                ", userContactWay='" + userContactWay + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userMotto='" + userMotto + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userGender=" + userGender +
                ", userAge=" + userAge +
                ", userArea='" + userArea + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", tradePassword='" + tradePassword + '\'' +
                ", userHeadImage='" + userHeadImage + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", invitePeopleUserName='" + invitePeopleUserName + '\'' +
                ", userType=" + userType +
                ", myLabel='" + myLabel + '\'' +
                ", remarks='" + remarks + '\'' +
                ", backup1=" + backup1 +
                ", backup2='" + backup2 + '\'' +
                ", backup3=" + backup3 +
                ", orderById='" + orderById + '\'' +
                ", createAtStr='" + createAtStr + '\'' +
                ", updateAtStr='" + updateAtStr + '\'' +
                ", startCreateAt=" + startCreateAt +
                ", endCreateAt=" + endCreateAt +
                ", startAtStr='" + startAtStr + '\'' +
                ", endAtStr='" + endAtStr + '\'' +
                '}';
    }
}