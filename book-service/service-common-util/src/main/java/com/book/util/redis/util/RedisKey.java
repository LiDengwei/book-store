package com.book.util.redis.util;

/**
 * Created by OWk on 2015/9/10.
 */
public class RedisKey {

    //JFB-CNY 行情价格
    public static final String HQ_PI_USD_KEY = "HQ_PI_USD_KEY";
    public static final String HQ_PI_USD_KEY_PRICE = "HQ_PI_USD_KEY_PRICE";
    public static final int HQ_PI_USD_TTL = 3;

    //注册时的rediskey
    public static final String REGISTER_USER_KEY = "REGISTER_USER_KEY";



    //单点登录
    public static final String SSO_KEY = "SSO_KEY";
    public static final int SSO_TTL = 60;
    //redis锁过期时间 60秒
    public static final int REDIS_TIME_OUT = 60;

    //单点登录
    public static final String NICK_KEY = "NICK_KEY";
    public static final int NICK_TTL = 60;
    public static final String LOGIN_KEY="LOGIN_KEY";


    //账户解锁
    public static final String ACCOUNT_UNLOCK_KEY = "ACCOUNT_UNLOCK_KEY";
    public static final int ACCOUNT_UNLOCK_TTL = 600;

    //挂单登录
    public static final String BOOK_OFFERS = "BOOK_OFFERS";
    public static final String BOOK_OFFERS_CHECK = "BOOK_OFFERS_CHECK";
    public static final String BOOK_OFFERS_BEST_PRICE = "BOOK_OFFERS_BEST_PRICE";
    public static final int BOOK_OFFERS_TTL = 2;



    //通知
    public static final String NOTICE_KEY = "NOTICE_KEY";
    public static final String NOTICE_KEY_ANDROID = "NOTICE_KEY_ANDROID";
    public static final String NOTICE_KEY_IOS = "NOTICE_KEY_IOS";
    public static final String NOTICE_PUSH_QUEUE = "NOTICE_PUSH_QUEUE";
    public static final int NOTICE_TTL = 3600*24;


    public static final String ANDROID_DEVICETOKEN = "ANDROID_DEVICETOKEN";
    public static final int ANDROID_DEVICETOKEN_TTL = 3600*24;


    public static final String IOS_DEVICETOKEN = "IOS_DEVICETOKEN";
    public static final int IOS_DEVICETOKEN_TTL = 3600*24;


    //App 版本
    public static final String APP_VERSION_KEY = "APP_VERSION_KEY_";
    public static final int APP_VERSION_TTL = 60;


    public static final int NORMAL_DATE_TTL = 600;
    public static final int VALIDATE_CODE_TTL = 60;


    //Package 红包
    public static final String PACK_QUEUE = "PACK_QUEUE";



    //Open数据
    public static final String OPEN_CHARTDATA = "OPEN_CHARTDATA_";
    public static final int OPEN_CHARTDATA_TTL = 600;

    //未支付交易
    public static final String TRANS_PAYORDER="TRANS_PAYORDER";


    //短息验证码
    public static final String SEND_SMS_CODE="TRANS_PAYORDER";
    //发送短信验证码，过期时间
    public static final int SEND_SMS_CODE_TTL=600;


    //短息验证码
    public static final String KLINE_DATA="KLINE_DATA";
    //发送短信验证码，过期时间
    public static final int KLINE_DATA_TTL=3;
    //PI充值汇率
    public static final String PI_DEPOSIT_RATE="PI_DEPOSIT_RATE";

    //派克游戏接口地址
    public static final String PI_POST_URL="PI_POST_URL";

    //派克帝国是否维护的开关1:否,2:是
    public static final String PAIKE_MAINTAIN_SWITCH="PAIKE_MAINTAIN_SWITCH";

    public static final String RELATIONSHIP_REDIS_KEY_PREFIX="userRelationshipList_"; //用户关系redis缓存key前缀
    public static final String SYSCONFIG_REDIS_KEY_PREFIX="sysConfig_"; //系统参数redis缓存key前缀
    public static final String SYSCONFIG_REDIS_KEY_EXP_60="SMS_SEND_PLATFORM"; //需要缓存过期时间为1分钟的系统配置

    //PI世界地块拍卖
    public static final String PAI_WORLD_AUCTION_BID_REDIS_KEY_PREFIX="paiWorldAuctionBid_"; //PAI世界地块拍卖出价redis缓存key前缀
    public static final String PAI_WORLD_AUCTION_USERS_REDIS_KEY="paiWorldAuctionUsers_"; //PAI世界当前拍卖地块出价用户redis缓存key

    public static final String OREINCOME_WORK_TIME="OREINCOME:WORKTIME";
    public static final String BANNER="BANNER";

    //Radar用户信息
    public static final String USER_RADAR_SESSION_COOKIE = "USER_RADAR_SESSION_COOKIE";
    public static final int USER_RADAR_SESSION_COOKIE_TTL = 3600*3;


    //Radar收益信息
    public static final String USER_RADAR_DIVIDEND_KEY = "USER_RADAR_DIVIDEND";
    public static final int USER_RADAR_DIVIDEND_TTL = 3600*3;

    //Radar 账户资产信息
    public static final String USER_RADAR_ACCOUNT_KEY = "USER_RADAR_ACCOUNT_KEY";
    public static final int USER_RADAR_ACCOUNT_TTL = 30;


    //Radar 账户信息
    public static final String USER_RADAR_INFO_KEY = "USER_RADAR_INFO_KEY";
    public static final int USER_RADAR_INFO_TTL = 20;


    //Radar 账户信息Check
    public static final String USER_RADAR_INFO_CHECK_KEY = "USER_RADAR_INFO_CHECK_KEY";
    public static final int USER_RADAR_INFO_CHECK_TTL = 60;

    //deviceId
    public static final String USER_DEVICEID_KEY = "USER_DEVICEID_KEY";
    public static final int USER_DEVICEID_TTL = 30;


    //trade volum
    public static final String RADAR_TRADE_VOLUME_KEY = "RADAR_TRADE_VOLUME_KEY";
    public static final int RADAR_TRADE_VOLUME_TTL = 4;


    //network volum
    public static final String RADAR_NET_WORK_DATA_KEY = "RADAR_NET_WORK_DATA_KEY";
    public static final int RADAR_NET_WORK_DATA_TTL = 1200;


    //acount tx
    public static final String RADAR_ACCOUNT_TX_DATA_KEY = "RADAR_ACCOUNT_TX_DATA_KEY";
    public static final int RADAR_ACCOUNT_TX_DATA_TTL = 30;



    //acount coin address
    public static final String RADAR_ACCOUNT_COIN_ADDRESS_KEY = "RADAR_ACCOUNT_TX_DATA_KEY";
    public static final int RADAR_ACCOUNT_COIN_ADDRESS_TTL = 30;



    //acount tx
    public static final String RADAR_ACCOUNT_NICK_KEY = "RADAR_NICK";
    public static final int RADAR_ACCOUNT_NICK_TTL = 3600;


    //queue
    public static final String QUEUE_KEY = "QUEUE_KEY";
    public static final int QUEUE_TTL = 900;


    //委托
    public static final String ENTRUST = "ENTRUST_";
    public static final String ENTRUST_CANCEL = "ENTRUST_CANCEL_";

    //转账
    public static final String TRANSLATION = "TRANSLATION_";

    //tradePwd tx
    public static final String TRADE_PASSWORD_KEY = "TRADE_PASSWORD_KEY_";
    public static final int TRADE_PASSWORD_TTL = 24 * 60 * 60;

    // 发送推送消息任务
    public static final String PUSH_NOTICE_KEY_PREFIX = "PUSH_USER_MSG_KEY_";

    // 同步消息任务
    public static final String SYNC_MESSAGE_KEY_PREFIX = "SYNC_MESSAGE_KEY_";

    //用户操作ip限制
    public static final String IP_LIMIT_KEY_PREFIX = "IP_LIMIT_KEY_";
    public static final int IP_LIMIT_TTL = 60;

    //发送短信验证码 手机号限制
    public static final String PHONE_LIMIT_KEY_PREFIX = "PHONE_LIMIT_KEY_";
    public static final int PHONE_LIMIT_TTL = 60;

    // elastic job redis 锁key前缀
    public static final String ELASTIC_JOB_KEY_PREFIX = "ELASTIC_JOB_KEY_";

    //获取排单币前缀
    public static final String PDB_KEY_PREFIX = "PDB_KEY_";
    public static final int PDB_LIMIT_TTL = 60 * 60;

    //获取关系网络前缀
    public static final String RELATION_NETWORK_KEY_PREFIX = "RELATION_NETWORK_";
    public static final int RELATION_NETWORK_LIMIT_TTL = 2 * 60;

    //messageCount
    public static  final  String  UNREAD_FORA = "UNREAD_FORA_KEY_";       //转发
    public static  final  String  UNREAD_COMMENT = "UNREAD_COMMENT_KEY_"; //评论
    public static  final  String  UNREAD_PRAISE = "UNREAD_PRAISE_KEY_";   //点赞
    public static  final  String  UNREAD_FOLLOW = "UNREAD_FOLLOW_KEY_";   //关注

    //blog cache
    public static final String BLOG_STAT_KEY = "BLOG_STAT_KEY_";  //微博统计
    public static final String BLOG_STAT_IDS_KEY = "BLOG_STAT_IDS_KEY_"; //微博统计集合

    public static final String USER_CONFIRM_GOLD_TICKET_REDIS_KEY_PREFIX="USER_CONFIRM_GOLD_TICKET_";//用户确认彩金券

    public static final int BLOG_TTL = 300;  //微博数据缓存时间

    public static final int UNREAD_TTL = 1200;

    //地块收益
    public static final String WORLD_MAP_INCOME_REDIS_KEY_PREFIX = "WORLD_MAP_INCOME_REDIS_KEY_PREFIX_";
    public static final int WORLD_MAP_INCOME_LIMIT_TTL = 60;

    //行情前缀
    public static final String TRADE_VOLUME_REDIS_PREFIX = "TRADE_VOLUME_REDIS_PREFIX_";
    public static final int TRADE_VOLUME_TTL = 10;

    // 同步行情
    public static final String SYNC_TRADE_VOLUME_KEY = "SYNC_TRADE_VOLUME_KEY";

    //深度图key
    public static final String SHEN_DU_KEY_PREFIX = "SHEN_DU_KEY_PREFIX_";
    public static final int SHEN_DU_TTL = 15;

    //总价以及均价key
    public static final String PRICE_AND_TOTAL_PREFIX = "PRICE_AND_TOTAL_PREFIX_";
    public static final int PRICE_AND_TOTAL_TTL = 15;

    //总价以及均价key
    public static final String K_LINE_PREFIX = "K_LINE_PREFIX_";
    public static final int K_LINE_PREFIX_TTL = 15;

    //转出SIEkey
    public static final String TRANSFER_OUT = "TRANSFER_OUT";
    public static final int TRANSFER_OUT_TTL = 15;

    //转出SIEkey
    public static final String TRANSFER_OUT_REQUEST_ETH = "TRANSFER_OUT_REQUEST_ETH";
    public static final int TTRANSFER_OUT_REQUEST_ETH_TTL = 15;

    //处理转出SIE失败订单key
    public static final String TRANSFER_OUT_FAILED_DEAL = "TRANSFER_OUT_FAILED_DEAL";
    public static final int TRANSFER_OUT_FAILED_DEAL_TTL = 15;

    //公共链转入SIE资产记录
    public static final String TRANSFER_TO="TRANSFER_TO";

    //后台转账确认是否成功
    public static final String MANAGER_TRANSFER_ACCOUNT_CONFIRM = "MANAGER_TRANSFER_ACCOUNT_CONFIRM_";

    //后台转账提交到以太坊
    public static final String MANAGER_TRANSFER_ACCOUNT_POST = "MANAGER_TRANSFER_ACCOUNT_POST_";

    //后台转账到以太坊任务建立
    public static final String MANAGER_TRANSFER_ACCOUNT_CREATE = "MANAGER_TRANSFER_ACCOUNT_CREATE_";

    //后台转账记录重新处理数据
    public static final String MANAGER_TRANSFER_ACCOUNT_CHECK = "MANAGER_TRANSFER_ACCOUNT_CHECK_";

    // 后台重启提醒
    public static final String MANAGER_SYSTEM_RESTART = "MANAGER_SYSTEM_RESTART" ;
    // 订单提醒
    public static final String MANAGER_RECORD_USER = "MANAGER_RECORD_" ;

    //解锁账单余额
    public static final String ACCOUNT_UNLOCK = "ACCOUNT_UNLOCK";




    public static final String USER_INFO_REDIS_KEY_PREFIX="user_info_"; //用户信息redis缓存key前缀

    public static final String USER_UNREAD_MSG_COUNT_KEY_PREFIX = "USER_UNREAD_MSG_COUNT_KEY_";
    public static final int USER_UNREAD_MSG_COUNT_TTL = 60*60*24*10;//未读消息条数redis有效期  10天

    // 购买SIEKEY
    public static final String BUY_SIE_USE_USD_KEY = "BUY_SIE_USE_USD_KEY_ADDRESS_";

    // 购买SIE业绩累积KEY
    public static final String GROUP_TOTAL_TASK_KEY = "ELASTIC_JOB_GROUP_TOTAL_TASK_KEY_";
    // 余额业绩累积任务
    public static final String BALANCE_TOTAL_TASK_KEY_PREFIX = "ELASTIC_JOB_BALANCE_TOTAL_TASK_KEY_";
    // 余额业绩累积任务
    public static final String BALANCE_TOTAL_REWARD_TASK_KEY_PREFIX = "ELASTIC_JOB_BALANCE_TOTAL_REWARD_TASK_KEY_";


    //公共链转入ETH
    public static final String ETH_TO_USD="ETH_TO_USD";
    public static final String BTC_TO_USERBTC="BTC_TO_USERBTC";
    // 购买达尔文累计业绩
    public static final String ORDERSERVICEAPIIMPL_SAVEGROUPTOTALBYORDER="ORDERSERVICEAPIIMPL_SAVEGROUPTOTALBYORDER";

    //达尔文累计业绩定时器
    public static final String ORDERACCUMULATIVE_TASK="ORDERACCUMULATIVE_TASK";

    //达尔文优惠值兑换sie
    public static final String DARWINDISCOUNT_TO_SIE="DARWINDISCOUNT_TO_SIE";

    //问题反馈
    public static final String APPEAL_PROBLEM = "APPEAL_PROBLEM";
    //问题反馈图片
    public static final String PROBLEM_IMAGES = "PROBLEM_IMAGES";

    public static final String ETH_TRANSFER_SUCCESS_UPDATE_ERROR = "ETH_TRANSFER_SUCCESS_UPDATE_ERROR_";
    public static final String TASK_QUERY_ETH_BALANCE_IS_ZERO = "TASK_QUERY_ETH_BALANCE_IS_ZERO_";

    // SIE市场价格缓存key
    public static final String SIE_MARKET_QUOTATIONS_PRICE_REDIS_KEY = "SIE_MARKET_QUOTATIONS_PRICE_REDIS_KEY";

    // 达尔文相关 SIE市场价格缓存key
    public static final String SIE_MARKET_QUOTATIONS_DARWIN_PRICE_REDIS_KEY = "SIE_MARKET_QUOTATIONS_DARWIN_PRICE_REDIS_KEY";

    // 训练记录处理
    public static final String TRAIN_LOG_ADD_TASK_KEY_PREFIX = "ELASTIC_JOB_TRAIN_LOG_ADD_TASK_KEY_";
    // 训练师奖励处理
    public static final String TRAINER_REWARD_TASK_KEY_PREFIX = "ELASTIC_JOB_TRAINER_REWARD_TASK_KEY_";

    public static final String ETH_BLOCK_SCAN_TASK="ETH_BLOCK_SCAN_TASK";
    public static final String BTC_BLOCK_SCAN_TASK="BTC_BLOCK_SCAN_TASK";



    //训练师奖励记录查询缓存key
    public static final String WEB_TRAINER_REWARD_LIST_KEY_PREFIX = "WEB_TRAINER_REWARD_LIST_KEY_";

    //克莱因瓶机器人奖励记录查询缓存key
    public static final String WEB_DAN_ROBOT_REWARD_LIST_KEY_PREFIX = "WEB_DAN_ROBOT_REWARD_LIST_KEY_";

    //查询K线图缓存key
    public static final String QueryLine_USE_USD_KEY="QueryLine_USE_USD_KEY";

    // 训练记录处理
    public static final String DAN_REWARD_LOG_ADD_TASK_KEY_PREFIX = "ELASTIC_JOB_DAN_REWARD_LOG_ADD_TASK_KEY_";

    //微信uuid key 缓存5分钟
    public static final String WEBCHAT_UUID_PREFIX = "WEBCHAT_UUID_PREFIX_";
    public static final int WEBCHAT_UUID_TTL = 5 * 60;

    //微信好友 key 缓存1天
    public static final String WEBCHAT_FRIEND_PREFIX = "WEBCHAT_FRIEND_PREFIX_";
    public static final int WEBCHAT_FRIEND_TTL = 24 * 60 * 60;

    //BET_CLEARING_TASK
    public static final String BET_CLEARING_TASK = "BET_CLEARING_TASK";

    //BET_REFUND_TASK
    public static final String BET_REFUND_TASK = "BET_REFUND_TASK";

    //BET_GROUP_TOTAL
    public static final String BET_GROUP_TOTAL = "BET_GROUP_TOTAL";
    public static final String BET_SYN = "BET_SYN";
    public static final String BET_PSUH = "BET_PSUH";

    //同步微信最新消息防止重复提交锁
    public static final String SYS_CHECK_WECHAT_FOR_DAN_ROBOT_NEW_MESSAGE_KEY = "SYS_CHECK_WECHAT_FOR_DAN_ROBOT_NEW_MESSAGE_KEY_";

    //克莱因瓶机器人训练模式写入信息标记
    public static final String DAN_ROBOT_TRAIN_MODE_RECEIVE_MESSAGE_KEY= "DAN_ROBOT_TRAIN_MODE_RECEIVE_MESSAGE_KEY_";

    //克莱因瓶奖励往期项目列表
    public static final String DAN_ROBOT_REWARD_HISTORY_LIST_KEY= "DAN_ROBOT_REWARD_HISTORY_LIST_KEY_";
    //克莱因瓶奖励往期项目详情
    public static final String DAN_ROBOT_REWARD_HISTORY_DETAIL_KEY = "DAN_ROBOT_REWARD_HISTORY_DETAIL_KEY_";
    //克莱因瓶奖励活动详情
    public static final String DAN_ROBOT_REWARD_PLAYING_DETAIL_KEY= "DAN_ROBOT_REWARD_PLAYING_DETAIL_KEY_";
    //克莱因瓶奖励参与者列表
    public static final String DAN_ROBOT_REWARD_PLAYER_LIST_KEY= "DAN_ROBOT_REWARD_PLAYER_LIST_KEY_";
    //克莱因瓶用户获取奖励列表
    public static final String DAN_ROBOT_REWARD_USER_REWARD_LIST_KEY= "DAN_ROBOT_REWARD_USER_REWARD_LIST_KEY_";
    //克莱因瓶用户获取奖励列表 活动
    public static final String DAN_ROBOT_REWARD_USER_REWARD_ROUND_LIST_KEY= "DAN_ROBOT_REWARD_USER_REWARD_ROUND_LIST_KEY_";
    //克莱因瓶用户获取循环奖励奖励列表 活动
    public static final String DAN_ROBOT_REWARD_USER_LOOP_REWARD_DETAIL_KEY= "DAN_ROBOT_REWARD_USER_LOOP_REWARD_DETAIL_KEY_";
    //克莱因瓶活动推广奖励详情
    public static final String DAN_ROBOT_REWARD_USER_REWARD_DETAIL_KEY= "DAN_ROBOT_REWARD_USER_REWARD_DETAIL_KEY_";
    //克莱因瓶活动用户排位奖励数据
    public static final String DAN_ROBOT_REWARD_USER_SORT_REWARD_LIST_KEY= "DAN_ROBOT_REWARD_USER_SORT_REWARD_LIST_KEY_";


    //克莱因瓶奖励参与者记录初始化定时任务
    public static final String DAN_REWARD_PLAYER_ADD_TASK_KEY_PREFIX = "ELASTIC_JOB_DAN_REWARD_PLAYER_ADD_TASK_KEY_";

    //克莱因瓶奖励参与者机器人定时任务
    public static final String DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_TASK_KEY_PREFIX = "ELASTIC_DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_TASK_KEY_";

    //克莱因瓶奖励参与者机器人定时任务锁
    public static final String DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_LOCK_PREFIX = "ELASTIC_DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_LOCK_";

    //克莱因瓶奖励参与者机器人参赛次数
    public static final String DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_SUM_AMOUNT_PREFIX = "ELASTIC_DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_SUM_AMOUNT_";

    //克莱因瓶奖励参与者机器人上次参赛用户
    public static final String DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_LAST_USER_PREFIX = "ELASTIC_DAN_REWARD_ROBOT_AUTO_PLAYER_ADD_LAST_USER_";



    //克莱因瓶奖励参与者记录初始化错误次数记录
    public static final String DAN_REWARD_PLAYER_ADD_ERROR_SAVE = "DAN_REWARD_PLAYER_ADD_ERROR_SAVE_";

    //克莱因瓶参与奖励处理定时任务
    public static final String DAN_REWARD_PLAYER_LOOP_TASK_KEY_PREFIX = "DAN_REWARD_PLAYER_LOOP_TASK_KEY_";

    //克莱因瓶活动奖励处理定时任务
    public static final String DAN_REWARD_FOR_ROUND_TASK_KEY_PREFIX = "DAN_REWARD_FOR_ROUND_TASK_KEY_";

    //克莱因瓶奖励发放处理定时任务
    public static final String DAN_REWARD_GRAND_TASK_KEY_PREFIX = "DAN_REWARD_GRAND_TASK_KEY_";

    //克莱因瓶微信消息发送 记录错误次数
    public static  final String DAN_ROBOT_SEND_RELAY_MESSAGE_ERROR_COUNT_KEY = "DAN_ROBOT_SEND_RELAY_MESSAGE_ERROR_COUNT_KEY_";

    //克莱因乐透分红奖励任务新建锁
    public static final String KLEIN_LOTTO_REWARD_BONUS_JOB_CREATE_KEY= "KLEIN_LOTTO_REWARD_BONUS_JOB_CREATE_KEY_";

    //克莱因乐透活动主页缓存
    public static final String LOTTO_REWARD_ROUND_INDEX_PUBLIC_DETAIL_KEY= "LOTTO_REWARD_ROUND_INDEX_PUBLIC_DETAIL_KEY_";

    //克莱因克莱因瓶SIE交易参与者记录初始化错误次数记录
    public static final String LOTTO_REWARD_PLAYER_ADD_ERROR_SAVE = "LOTTO_REWARD_PLAYER_ADD_ERROR_SAVE_";

    //克莱因克莱因瓶SIE交易参与者记录初始化定时任务
    public static final String LOTTO_REWARD_PLAYER_ADD_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_REWARD_PLAYER_ADD_TASK_KEY_";

    //克莱因克莱因瓶SIE交易派别业绩累计定时任务
    public static final String LOTTO_REWARD_PLAYER_GROUP_TOTAL_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_REWARD_PLAYER_GROUP_TOTAL_TASK_KEY_";

    //克莱因克莱因瓶SIE交易分红任务建立定时任务
    public static final String LOTTO_REWARD_BONUS_JOB_CREATE_TASK_KEY_PREFIX = "LOTTO_REWARD_BONUS_JOB_CREATE_TASK_KEY_PREFIX_";

    //克莱因克莱因瓶SIE交易分红任务执行定时任务
    public static final String LOTTO_REWARD_BONUS_JOB_HANDEL_TASK_KEY_PREFIX = "LOTTO_REWARD_BONUS_JOB_HANDEL_TASK_KEY_PREFIX_";

    //克莱因克莱因瓶SIE交易分红任务完成
    public static final String LOTTO_REWARD_BONUS_JOB_HANDEL_FINISH_LOCK = "LOTTO_REWARD_BONUS_JOB_HANDEL_FINISH_LOCK_";

    //克莱因克莱因瓶SIE交易分红池金额用完
    public static final String LOTTO_REWARD_BONUS_POOL_USED_EMPTY_LOCK = "LOTTO_REWARD_BONUS_POOL_USED_EMPTY_LOCK_";

    //克莱因克莱因瓶SIE交易活动结束奖金池奖励定时任务
    public static final String LOTTO_REWARD_END_POOL_HANDEL_TASK_KEY_PREFIX = "LOTTO_REWARD_END_POOL_HANDEL_TASK_KEY_PREFIX_";

    //克莱因克莱因瓶SIE交易奖金池任务完成
    public static final String LOTTO_REWARD_END_POOL_HANDEL_FINISH_LOCK = "LOTTO_REWARD_END_POOL_HANDEL_FINISH_LOCK_";

    //克莱因克莱因瓶SIE交易奖励发放处理定时任务
    public static final String LOTTO_REWARD_GRAND_TASK_KEY_PREFIX = "LOTTO_REWARD_GRAND_TASK_KEY_";

    //克莱因克莱因瓶SIE交易活动冻结sie返还定时任务
    public static final String LOTTO_REWARD_FREEZE_RETURN_TASK_KEY_PREFIX = "LOTTO_REWARD_FREEZE_RETURN_TASK_KEY_";

    //克莱因克莱因瓶交易奖励缓冲任务处理定时任务
    public static final String LOTTO_REWARD_CACHE_DATA_HANDLE_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_REWARD_CACHE_DATA_HANDLE_TASK_KEY_PREFIX_";

    //克莱因交易所锁单key
    public static final String LOCK_ORDER = "LOCK_ORDER";
    public static final String NEW_LOCK_ORDER = "NEW_LOCK_ORDER";
    //自动撮合引擎定时器key
    public static final String MATCHCOINTRADECHECKTASK = "MATCHCOINTRADECHECKTASK";
    public static final String NEWMATCHCOINTRADECHECKTASK = "NEWMATCHCOINTRADECHECKTASK";
    //克莱因k线图key
    public static final String KLEIN_KLINE = "KLEIN_KLINE";
    public static final String NEW_KLEIN_KLINE = "NEW_KLEIN_KLINE";
    public static final String REAL_TIME_DATA = "REAL_TIME_DATA";

    //克莱因克莱因瓶SIE交易打赏队列生成定时任务
    public static final String LOTTO_AWARD_QUEUE_ADD_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_AWARD_QUEUE_ADD_TASK_KEY_";

    //克莱因克莱因瓶SIE交易打赏队列生成错误次数记录
    public static final String LOTTO_AWARD_QUEUE_ADD_ERROR_SAVE = "LOTTO_AWARD_QUEUE_ADD_ERROR_SAVE_";

    //克莱因克莱因瓶SIE交易打赏队列无待处理数据
    public static final String LOTTO_AWARD_QUEUE_ADD_NO_DATA = "LOTTO_AWARD_QUEUE_ADD_NO_DATA_";

    //克莱因克莱因瓶SIE交易打赏队列处理错误次数记录
    public static final String LOTTO_AWARD_QUEUE_HANDEL_ERROR_SAVE = "LOTTO_AWARD_QUEUE_HANDEL_ERROR_SAVE_";

    //克莱因克莱因瓶SIE交易用户团队分红记录总额
    public static final String LOTTO_REWARD_REF_BONUS_AMOUNT_SUM = "LOTTO_REWARD_REF_BONUS_AMOUNT_SUM_";

    //克莱因克莱因瓶SIE交易打赏处理定时任务
    public static final String LOTTO_AWARD_QUEUE_HANDEL_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_AWARD_QUEUE_HANDEL_TASK_KEY_";

    //克莱因克莱因瓶SIE交易推广分红发放执行定时任务
    public static final String LOTTO_REWARD_REF_BONUS_HANDEL_TASK_KEY_PREFIX = "LOTTO_REWARD_REF_BONUS_HANDEL_TASK_KEY_PREFIX_";

    //克莱因克莱因瓶SIE交易小时奖励定时任务
    public static final String LOTTO_REWARD_HOUR_JOB_HANDEL_TASK_KEY_PREFIX = "ELASTIC_JOB_LOTTO_REWARD_HOUR_JOB_HANDEL_TASK_KEY_";

    //克莱因瓶奖励参与者机器人定时任务
    public static final String LOTTO_REWARD_ROBOT_AUTO_PLAYER_ADD_TASK_KEY_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_PLAYER_ADD_TASK_KEY_";

    //克莱因瓶交易奖励机器人是否随机参赛 满3小时
    public static final String LOTTO_REWARD_ROBOT_RANDOM_PLAYER_ADD_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_RANDOM_PLAYER_ADD_";

    //克莱因瓶交易奖励机器人是否随机参赛 不满3小时
    public static final String LOTTO_REWARD_ROBOT_RANDOM_MIN_PLAYER_ADD_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_RANDOM_MIN_PLAYER_ADD_";

    //克莱因瓶奖励参与者机器人上次参赛用户
    public static final String LOTTO_REWARD_ROBOT_AUTO_PLAYER_ADD_LAST_USER_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_PLAYER_ADD_LAST_USER_";

    //克莱因瓶奖励参与者机器人是否刚刚参赛
    public static final String LOTTO_REWARD_ROBOT_AUTO_PLAYER_JOIN_SOON = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_PLAYER_JOIN_SOON";


    //克莱因瓶奖励结束前机器人上次参赛用户
    public static final String LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_ADD_LAST_USER_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_ADD_LAST_USER_";

    //克莱因瓶奖励结束前机器人是否刚刚参赛
    public static final String LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_JOIN_SOON = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_JOIN_SOON";

    //克莱因瓶奖励参与者机器人定时任务
    public static final String LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_ADD_TASK_KEY_PREFIX = "ELASTIC_LOTTO_REWARD_ROBOT_AUTO_END_PLAYER_ADD_TASK_KEY_";


    //克莱因克莱因瓶SIE交易奖励 活动数据更改锁
    public static final String LOTTO_REWARD_ROUND_DATA_LOCK_KEY = "LOTTO_REWARD_ROUND_DATA_LOCK_KEY";

}
