package com.book.exceptions;

import java.io.Serializable;

/**
 * 统一异常处理
 * Created by liulian
 */
public class APIException extends RuntimeException implements Serializable {


    public static String ERROR_CODE_00000 = "00000";//成功
    public static String ERROR_CODE_10001 = "10001";//必填参数为空
    public static String ERROR_CODE_10002 = "10002";//白名单为空
    public static String ERROR_CODE_10004 = "10004";//IP不在白名单
    public static String ERROR_CODE_10005 = "10005";//用户不存在
    public static String ERROR_CODE_10006 = "10006";//双方都没有激活
    public static String ERROR_CODE_10007 = "10007";//双方都有上级了
    public static String ERROR_CODE_10008 = "10008";//不能互为上级
    public static String ERROR_CODE_10009 = "10009";//系统错误
    public static String ERROR_CODE_10010 = "10010";//用户名不能含有中文和特殊符号，长度2-21
    public static String ERROR_CODE_10011 = "10011";//用户名已存在
    public static String ERROR_CODE_10012 = "10012";//邮箱已存在
    public static String ERROR_CODE_10013 = "10013";//手机号已被注册
    public static String ERROR_CODE_10014 = "10014";//未知错误
    public static String ERROR_CODE_10015 = "10015";//传参有误
    public static String ERROR_CODE_10016 = "10016";//上级账号是老板
    public static String ERROR_CODE_10017 = "10017";//未达到构成关系要求
    public static String ERROR_CODE_10018 = "10018";//上级用户不存在
    public static String ERROR_CODE_10019 = "10019";//上级用户未绑定上级
    public static String ERROR_CODE_10020 = "10020";//请填写区号
    public static String ERROR_CODE_10031 = "10031";//扣款订单不存在
    public static String ERROR_CODE_10032 = "10032";//金额必须大于0
    public static String ERROR_CODE_10033 = "10033";//Email对应用户不存在
    public static String ERROR_CODE_10034 = "10034";//系统账户不存在
    public static String ERROR_CODE_10035 = "10035";//转账失败
    public static String ERROR_CODE_10036 = "10036";//操作频繁请稍后重试
    public static String ERROR_CODE_10037 = "10037";//打款余额不足（打款总额超过扣款订单金额）
    public static String ERROR_CODE_10038 = "10038";//退款用户和发红包用户不一致
    public static String ERROR_CODE_10039 = "10039";//账户余额不足
    public static String ERROR_CODE_10040 = "10040";//红包退款无可退款金额(已经领完 或者 已经退款)
    public static String ERROR_CODE_10041 = "10041";//转出金额必须大于系统配置金额
    public static String ERROR_CODE_10042 = "10042";//不能自己给自己转
    public static String ERROR_CODE_10043 = "10043";//未配置以太坊转出手续费比例
    public static String ERROR_CODE_10044 = "10044";//转出地址错误
    public static String ERROR_CODE_10045 = "10045";//不能转到该地址
    public static String ERROR_CODE_10046 = "10046";//无效的转出类型
    public static String ERROR_CODE_10047 = "10047";//此功能暂未开放
    public static String ERROR_CODE_99999 = "99999";//系统繁忙请重试

    public static enum ErrorCode{
        NOT_YOU,
        PARAM_NOT_EMPTY,
        PARAM_ERROR,
        UPDATE_FAIL,
        GET_DATA_FAIL,//数据获取失败
        INSERT_FAIL,//添加数据失败
        API_REQUEST_FAIL,//接口请求失败
        CANCEL_FAIL,
        DELETE_FAIL,
        NO_NETWORK,//网络没有响应
        SIGN_ERROR,//签名错误
        REGIStER_FAILED,//签名错误
        ORDER_EXIST, //订单已存在
        SWITCH_ERROR, //开关
        DATA_EXIST, //数据已存在
        INSUFFICIENT_MONEY, //金额不足
        PAYED, //订单已支付成功
        UN_SUPPORT_DRAW,
        UN_SUPPORT_DEPOSIT,

        INTERNAL_ERROR,
        USER_NOT_FOUND,//用户不存在
        UNKNOWN_ERROR,
        ADDRESS_NOT_FOUND,
        ADDRESS_FORMAT_MALFORMED,
        ADDRESS_ERROR,
        UNSUPPORTED_CURRENCY,
        NOT_LOGGEDIN,
        INCORRECT_PASSWORD,
        NICK_EXISTS,
        NUMBER_EXISTS,
        ACCOUNT_LOCK,
        IS_GATEWAY,
        EMIAL_EXISTS,
        NICK_INVALID,
        TOO_MANY_REQUESTS,
        PAYPASSWORD_ERROR,
        MOBILE_VERIFY_FAILED,
        USER_HAS_NOT_BIND_MOBILE,
        USER_ACCOUNT_BALANCE_NOT_ENOUGH,
        NOT_FOUND,
        INCORRECT_PHONE_CODE,
        PHONE_CODE_TOO_FREQUENCY,
        OPEN_ORDER_NOT_FOUND,
        ORDER_HAS_BEEN_PAYED,
        OPEN_SIGN_INVALID,
        EMIAL_TOO_FREQUENCY,
        INVALID_URL_TOKEN,
        PAY_PASSWORD_MATCH_LOGIN,
        USER_MOBILE_FORMAT_ERROR,
        SEED_VERIFY_FAILED,
        RESET_PASSWORD_ERROR,
        LOGIN_TIMES_LIMIT,

        REGISTER_FAILED,//注册失败

        TRANSACTION_ERROR,//转账失败
        ENTRUST_ERROR,//转账失败
        ENTRUST_UPDATE_ERROR,//更新委托失败
        AMOUNT_ERROR,//金额错误

        ORDER_FINISH,//订单已经完成

        USER_ACCOUNT_LOCKED,//账户锁定
        USER_ACCOUNT_NOT_ACTIVE,//账户锁定
        DRAW_ERROR ,//提现失败
        ASSET_NOT_FOUND,
        USER_HAS_NOT_SET_GOOGLEAUTH,
        USER_HAS_SET_PHONE_VERIFY,
        USER_HAS_SET_GOOGLE_VERIFY,
        INCORRECT_GOOGLE_VERIFY_CODE,
        PHONE_HAS_EXIST,
        PHONE_NOT_NULL,
        INVALID_PARAMS,
        TOKEN_NOT_FOUND,
        REMOTE_SERVER_BUSY,
        PAYPASSWORD_EQUAL_PASSWORD,
        INVALID_PASSWORD,
        APP_SESSION_INVALID,
        EMAIL_NOT_NULL,//邮箱不能为空
        EMAIL_NOT_EQUAL,//邮箱不一致
        USER_NOT_BIND_EMAIL,//未绑定邮箱
        USER_NOT_BIND_MOBILE,//未绑定手机
        OPEN_ORDER_NONPAYMENT,
        INCORRECT_RANDOM_CODE,
        PASSWORD_ERROR_TIMES_LIMIT,
        USER_HAS_OPEN_DYNAMIC_VERIFY,
        APP_LOGIN_TOKEN_INVALID,
        SESSION_TIMEOUT,
        AUTH_LIMITED,
        NOT_JOIN_SESSION,    //没有加入会话,
        VERSION_OLD         //旧版本

    }

    public ErrorCode code;
    public String message;

    public APIException(ErrorCode code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public APIException(ErrorCode code, String message, Exception ex){
        super(message);
        this.code = code;
        this.message = message;
        this.setStackTrace(ex.getStackTrace());
    }

    public APIException(String message){
        super(message);
        this.message = message;
    }
}
