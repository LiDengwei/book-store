package com.book.service.impl;//package com.offical.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.offical.exceptions.APIException;
//import com.offical.page.PageBean;
//import com.offical.service.dao.user.UserAccountLogMapper;
//import com.offical.service.dao.user.UserAccountMapper;
//import com.offical.service.dao.user.UserMapper;
//import com.offical.service.daoSlave.user.UserAccountSlaveMapper;
//import com.offical.service.model.user.User;
//import com.offical.service.model.user.UserAccount;
//import com.offical.service.model.user.UserAccountLog;
//import com.offical.util.redis.JedisClient;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by OWk on 2015/9/7.
// */
//
//@Service("baseService")
//public class BaseService {
//
//    @Autowired
//    private JedisClient jedisClient;
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private UserAccountSlaveMapper userAccountSlaveMapper;
//    @Autowired
//    private UserAccountMapper userAccountMapper;
//    @Autowired
//    private UserAccountLogMapper userAccountLogMapper;
//
//    private static final Logger logger = Logger.getLogger(BaseService.class);
//
//    public  void checkAccount(String account){
//        User user = userMapper.queryUserByUserAddress(account);
//        if(user==null){
//            throw new APIException(APIException.ErrorCode.NOT_YOU,"用户不存在");
//        }
//    }
//
//    public UserAccount queryUserAccount(String address,String currency){
//        try {
//            UserAccount param=new UserAccount();
//            param.setCurrency(currency);
//            param.setUserAddress(address);
//            PageBean pageBean=new PageBean();
//            pageBean.setData(param);
//            pageBean.setPageNo(1);
//            pageBean.setPageSize(1);
//            logger.info("currency=====" + currency);
//            logger.info("address====="+address);
//            List<UserAccount> uas=userAccountSlaveMapper.selectByPage(pageBean);
//            logger.info("uas====="+ JSON.toJSONString(uas));
//            UserAccount userAccount=null;
//            if (uas!=null && uas.size()>0){
//                userAccount=uas.get(0);
//            }
//            return userAccount;
//        }catch(Exception ex){
//            logger.error("queryUserAccount error.", ex);
//        }
//        return null;
//    }
//
//    public void checkEntrustBalance(String currency,String address,BigDecimal amount) throws APIException {
//        //查询账户余额
//
//
//        UserAccount userAccount = queryUserAccount(address, currency);
//        if (userAccount!=null){
//            BigDecimal balance = userAccount.getBalanceEntrustFreeze();
//            if(balance.subtract(amount).compareTo(BigDecimal.ZERO)<0){
//                throw new APIException(APIException.ErrorCode.PARAM_ERROR,userAccount.getUserAddress()+" 余额不足");
//            }
//        }else {
//            throw new APIException(APIException.ErrorCode.PARAM_ERROR,"账户不存在");
//        }
//
//
//    }
//    public void checkBalance(String currency,String address,BigDecimal amount,BigDecimal freezeAmount) throws APIException {
//        //查询账户余额
//
//
//        UserAccount userAccount = queryUserAccount(address, currency);
//        if (userAccount!=null){
//            BigDecimal balance = userAccount.getBalance();//余额
//            BigDecimal freezeBalance = userAccount.getBalanceFreeze();//冻结余额
//
//
//            if(balance.subtract(amount).compareTo(BigDecimal.ZERO)<0){
//                throw new APIException(APIException.ErrorCode.PARAM_ERROR,userAccount.getUserAddress()+" 余额不足");
//            }
//            if(freezeBalance.subtract(freezeAmount).compareTo(BigDecimal.ZERO)<0){
//                throw new APIException(APIException.ErrorCode.PARAM_ERROR,userAccount.getUserAddress()+" 冻结余额不足");
//            }
//        }else {
//            throw new APIException(APIException.ErrorCode.PARAM_ERROR,"账户不存在");
//        }
//
//
//    }
//
//    /**
//     * 账户金额变动
//     * @param address 账户地址
//     * @param currency 币种
//     * @param changeAmount 变动的余额
//     * @param changeFreezeAmount 变动的冻结余额
//     * @param orderId 订单ID
//     * @param remark 描述
//     * @return
//     * @throws APIException
//     */
//    public boolean modifyAccountMoney(String address,String  currency,BigDecimal changeAmount,BigDecimal changeFreezeAmount,String orderId,String remark,Short changeType) throws APIException {
//        //查询账户余额
//        UserAccount userAccount = queryUserAccount(address, currency);
//        BigDecimal balance = userAccount.getBalance();
//        BigDecimal freezeBalance = userAccount.getBalanceFreeze();
//        if(balance.add(changeAmount).compareTo(BigDecimal.ZERO)<0){
//            throw new APIException(APIException.ErrorCode.PARAM_ERROR,"余额不足");
//        }
//        int changeAccountResult = changeBalance(address, userAccount.getId(), changeAmount, changeFreezeAmount, currency);
//
//        if(changeAccountResult<=0){
//            throw new APIException(APIException.ErrorCode.PARAM_ERROR,"更新账户失败");
//        }
//
//        userAccount = queryUserAccount(address,currency);
//        logger.info("userAccount==="+JSON.toJSONString(userAccount));
//        //写入Log
//        UserAccountLog userAccountLog = new UserAccountLog();
//        userAccountLog.setAddress(address);
//        userAccountLog.setChangeType(changeType);
//        userAccountLog.setOrderId(orderId);
//        userAccountLog.setRemark(remark);
//        userAccountLog.setCurrency(currency);
//        userAccountLog.setCreateAt(new Date());
//        userAccountLog.setUpdateAt(new Date());
//
//        //设置变动金额
//        userAccountLog.setChangeAmount(changeAmount);
//        userAccountLog.setChangeAmountFreeze(changeFreezeAmount);
//        //变动前余额
//        userAccountLog.setBeforeChangeBalance(balance);
//        userAccountLog.setBeforeChangeFreezeBalance(freezeBalance);
//        //
//        userAccountLog.setEntrustChangeAmountFreeze(BigDecimal.ZERO);
//        userAccountLog.setEntrustFinalFreezeBalance(BigDecimal.ZERO);
//        userAccountLog.setEntustBeforeChangeFreezeBalance(BigDecimal.ZERO);
//        userAccountLog.setPackageBeforeChangeFreezeBalance(BigDecimal.ZERO);
//        userAccountLog.setPackageChangeAmountFreeze(BigDecimal.ZERO);
//        userAccountLog.setPackageFinalFreezeBalance(BigDecimal.ZERO);
//        userAccountLog.setFinalBalance(balance.add(changeAmount));
//        userAccountLog.setFinalFreezeBalance(freezeBalance.add(changeFreezeAmount));
//        userAccountLogMapper.insert(userAccountLog);
//        return true;
//    }
//
//    private int changeBalance(String address,Long accountId,BigDecimal changeAmount,BigDecimal changeFreezeAmount,String currency){
//        Map<String,Object> param = new HashMap<String,Object>();
//        param.put("address",address);
//        param.put("changeAmount",changeAmount);
//        param.put("changeFreezeAmount",changeFreezeAmount);
//        param.put("accountId",accountId);
//        param.put("updateAt", new Date());
//        param.put("currency",currency);
//        return userAccountMapper.changeBalance(param);
//    }
//
//
//}
