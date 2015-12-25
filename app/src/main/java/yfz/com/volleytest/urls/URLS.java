package yfz.com.volleytest.urls;

/**
 * ***************************************************************************
 * E城到家 Home 页面，这里主要是实现了底部菜单栏
 * ****************************************************************************
 * Authors:chris on 12/25/15 10:30
 * Email：zhangyanlongcodec@gmail.com
 */
public class URLS {

    /**
     * 当前APK的版本号
     */
    public static String APK_CODE = "";
    /**
     * 当前APK的编号
     */
    public static String APK_VERSION = "";
    /**
     * 请求服务的前缀
     */
    public static String SERVER_PRE = "http://";
    /**
     * 请求socket的IP地址
     */
    public static String SERVER_IP = "183.196.130.112";// 服务器地址(外网)
    // public static String SERVER_IP="10.37.144.253";//服务器地址(内网)
    // public static String SERVER_IP="10.2.64.8";//服务器地址(东哥)
//	 public static String SERVER_IP = "ennewapp.enn.cn";// 服务器地址(生产)
    // public static String SERVER_IP="10.2.65.254";//服务器地址(东哥)
    /**
     * 请求socket的端口号
     */
    public static int PORT = 4567;// 服务器地址（测试）
    // public static int PORT = 8088;// 服务器地址（153卡测试端口）
//	 public static int PORT = 6688;// 服务器地址(生产)
    /**
     * 请求的端口号
     */
    public static String SERVER_PORT = "8080";// 服务器地址
    // public static String SERVER_PORT = "8081";// 服务器地址（153卡测试端口）
//	 public static String SERVER_PORT = "6677";// 服务器地址(生产)

    /**
     * 拼接第三方支付服务器地址的方法
     */
    public static String getServerUrl() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(SERVER_PRE);
        buffer.append(SERVER_IP);
        buffer.append(":");
        buffer.append(SERVER_PORT);
        buffer.append("/");
        return buffer.toString();
    }
    /**
     * 上门服务及水费url主链接
     */
    //是否显示log ENN
//	public static final boolean LOGSHOW=false;
//	public static final String HOME_SERVER_INDEX_URL = "http://ennewapp.enn.cn:8081/";
//	public static final String HOME_SERVER_INDEX_M_URL = "http://ennewapp.enn.cn:8082/";
//	public static final String mMode="00";//正式
    //测试地址
    /**
     * 判断当前城市是否有邀请码活动
     */
    public static final boolean LOGSHOW = true;
    public static final String HOME_SERVER_INDEX_URL = "http://183.196.130.125:8085/";//上门服务及水费url主链接测试
    public static final String HOME_SERVER_INDEX_M_URL = "http://183.196.130.125:8099/";//上门师傅的主地址测试
    //	public static final String HOME_SERVER_INDEX_URL = "http://10.37.144.19:8080/";//内网
//	public static final String HOME_SERVER_INDEX_M_URL = "http://10.37.144.19:8099/";//内网
    public static final String mMode = "01";//银联支付的环境测试

    //请求url版本号
    public static final String REQVERSION = "1.0";

    /******************************* 请求服务的模块名以及方法名 ******************************/
    /**
     * 分享使用的图标网站路径
     **/
    public static String SHARE_PIC_URL = "http://ennewapp.enn.cn:8085/EnnewFiles/images/ennewlog.png";

    /**
     * 新增首页通知接口 2015.12.17 康康
     **/
    public static String GET_MSG_INFO_URL = HOME_SERVER_INDEX_URL + "comeservice/notice/getnotice";

    /**
     * 修改获取业务权限 接口 2015.12.10 景辉
     **/
    public static String GET_ROLER_INFO_URL = HOME_SERVER_INDEX_URL + "common/config/cityConfig/";

    /**
     * 获取钱包余额 2015.11.17 钱包支付需要
     */
    public static String GET_WALLET_INFO_URL = HOME_SERVER_INDEX_URL + "Activity/getActivitylist";

    /**
     * 锁钱包状态 2015.11.17 钱包支付需要
     */
    public static String LOCK_WALLET_STATUS_URL = HOME_SERVER_INDEX_URL + "Activity/WalletConsume";

    /**
     * 获取分享码
     */
    public static String SHARED_APP_CODE = getServerUrl() + "api/activities/invitation.json";
    /**
     * 分享的下载链接
     */
    public static String SHARED_APP_DOWNLOAD = getServerUrl()
            + "assets/download/download.html";
    /**
     * 判断当前城市是否有邀请码活动
     */
    public static String APP_CODE_TRACE = getServerUrl() + "api/activities/trace.json";

    /**
     * 检查APK当前版本是否是最新版本
     */
    public static String CHECK_APK_VERSION = "api/upgrade/version.json";
    /**
     * 分享时头像 链接
     */
    public static String SHARE_ICON = "assets/img/share.png";
    /**
     * 用户登录接口
     */
    public static String USER_LOGIN = "api/user/login.json";
    /**
     * 验证手机接口
     */
    public static String CHECK_PHONE = "api/user/check.json";
    /**
     * 获取验证码接口
     */
    public static String GET_CHECKCODE = "api/user/captcha.json";
    /**
     * 用户注册接口
     */
    public static String USER_REGISTER = "api/user/register.json";
    /**
     * 意见反馈
     */
    public static String IDEA_NOTICE = "api/suggestion/saveSuggest.json";
    /**
     * 关于我们
     */
    public static String ABOUT_US = "api/about/about.json";
    /**
     * 添加 燃气账号
     */
    public static String ADD_ACCOUNTNUMBER = "api/ordinary/bind.json";
    /**
     * 获取燃气 CNG Blue账号
     */
    public static String GET_ACCOUNTNUMBER = "api/ordinary/list.json";
    /**
     * 删除燃气账号
     */
    public static String DETELE_ACCOUNTNUMBER_GAS = "api/ordinary/remove.json";
    /**
     * 修改手机号码 验证
     */
    public static String CELL_PHONE = "api/user/checkCaptcha.json";
    /**
     * 验证通过 修改手机
     */
    public static String CELL_SUCCESS = "api/user/modifyTelPhone.json";
    /**
     * 修改 分组
     */
    public static String UPDATE_GROUP = "api/ordinary/update.json";
    /**
     * 修改昵称
     */
    public static String UPDATE_NICKNAME = "api/user/modifyNickName.json";
    /**
     * 忘记密码接口
     */
    public static String FORGET_PASSWORD = "api/user/find.json";
    /**
     * 忘记密码（用户检验）接口
     */
    public static String CHECKUSERNAME = "api/user/forgetPasswd.json";
    /**
     * 上传头像
     */
    public static String UPLOADAVATAR = "api/user/uploadPhoto.json";
    /**
     * 获取头像
     */
    public static String GET_HEAD = "api/user/getUserPhotoUrl.json";
    /**
     * 获取头像文件流
     */
    public static String GET_HEAD_FILE = "api/user/userPhotoUrl.json";
    /**
     * 获取分享图片
     */
    public static String SHARE_HEAD = "/api/user/share.json";
    /**
     * 获取名称
     */
    public static String GET_BASIC = "api/user/getUserAccount.json";
    /**
     * 用户修改昵称接口
     */
    public static String NICK_NAME = "api/user/nickname.json";
    /**
     * 用户修改密码接口
     */
    public static String CHANGE_PASSWORD = "api/user/passwd.json";
    /**
     * 购气历史接口
     */
    public static String HISTORY_ORDER = "api/orderform/history.json";
    /**
     * 订单列表获取接口
     */
    public static String ORDER_LIST = "api/orderform/request.json";
    /**
     * 未支付订单删除接口
     */
    public static String ORDER_CANCEL = "api/orderform/remove.json";
    /**
     * 消息接口
     */
    public static String MESSAGE_LIST = "api/message/request.json";
    /**
     * 获取公司接口
     */
    public static String COMPANY_TASK = "api/company/list.json";
    /**
     * 获取城市接口
     */
    public static String CITY_TASK = "api/company/cities.json";
    /**
     * 申请表信息提交接口
     */
    public static String CARDAPPLY_TASK = "api/proposer/request.json";
    /**
     * 获取申请列表信息接口
     */
    public static String GET_CARDAPPLY_LIST_TASK = "api/proposer/list.json";
    /**
     * 修改申请状态接口
     */
    public static String CHANGE_CARDAPPLY_STATUE_TASK = "api/proposer/status.json";
    /**
     * 设备信息查询接口
     */
    public static String DEVICE_FIND_TASK = "api/equipment/find.json";
    /**
     * 设备信息提交接口
     */
    public static String DEVICE_TASK = "api/equipment/add.json";
    /**
     * 设备信息删除接口
     */
    public static String DEVICE_DELETE_TASK = "api/equipment/remove.json";
    /**
     * 查询合同号小区信息接口
     */
    public static String QUERY_CONTRACT_ADDRESS_TASKS = "api/ordinary/getArea.json";

    /**
     * 查询合同号楼栋号信息接口
     */
    public static String QUERY_CONTRACT_BANCODE_TASKS = "api/ordinary/getBuilding.json";
    /**
     * 查询合同号单元号信息接口
     */
    public static String QUERY_CONTRACT_UNITCODE_TASKS = "api/ordinary/getUnit.json";
    /**
     * 查询合同号房间号信息接口
     */
    public static String QUERY_CONTRACT_ROOMCODE_TASKS = "api/ordinary/getRoom.json";
    /**
     * 查询合同号信息接口
     */
    public static String QUERY_CONTRACT_TASKS = "api/ordinary/search.json";
    /**
     * 查询菜单权限接口
     */
    public static String GET_ROLEINFOR_TASKS = "api/citySales/index.json";
    /**
     * 查询支付方式接口
     */
    public static String QUERY_PAYMENT_TYPE_TASKS = "api/config/index.json";

    /**
     * 获取支付宝支付订单信息
     */
    public static String ZFB_PAYMENT_TASKS = "api/alipay/httpUrl.json";

    /**
     * 获取银联支付TN号
     */
    public static String UPPPAY_GETTN_TASKS = "api/unionpay/gettn.json";

    /**
     * 根据业务类型获取交易额度范围
     */
    public static String PAYLIMIE_BY_TYPE_TASKS = "api/subpay/getByType.json";

    /**
     * 获取谱表的历史缴费号
     */
    public static String HIS_ACCOUT_I_TASKS = "api/orderhistory/history.json";

    /******************************* CNG START ******************************/

    /**
     * 绑定的卡列表
     */
    public static final String CNG_BADING_LIST_URL = getServerUrl()
            + "api/cng/list.json";

    /**
     * 最近使用的卡列表
     */
    public static final String CNG_LAST_LIST_URL = getServerUrl()
            + "api/cng/lastest.json";

    /**
     * 卡详情
     */
    public static final String CNG_CARD_INFO_URL = getServerUrl()
            + "api/cng/cardinfo.json";

    /**
     * 卡绑定
     */
    public static final String CNG_BIND_URL = getServerUrl()
            + "api/cng/bind.json";

    /**
     * 卡解绑
     */
    public static final String CNG_REMOVE_URL = getServerUrl()
            + "api/cng/remove.json";

    /**
     * 统计数据
     */
    public static final String CNG_SUNMMARY_URL = getServerUrl()
            + "api/cng/summary.json";

    /**
     * 获取促销活动接口
     */
    public static String QUERY_ACTIVITYS_TASKS = "api/activities/list.json";
    /**
     * 获取CNG交易账单接口
     */
    public static String QUERY_BUYDETAILSLIST_TASKS = "api/cng/detail.json";

    /******************************* CNG END ******************************/

    /**
     * 我的钱包
     */
    public static final String WALLET_LIST_URL = "api/wallet/wallet.json";
    /**
     * 索取红包接口
     */
    public static String GET_REDPAPER_TASKS = "api/activities/gift.json";
    /**
     * 打开红包接口
     */
    public static String OPEN_REDPAPER_TASKS = "api/activities/open.json";

    /*******************************
     * Water Start
     ******************************/

    public static final String WATER_INDEX_URL = HOME_SERVER_INDEX_URL;

    /**
     * 查询水费的出账单位
     */
    public static final String WATER_FIND_COPYRIGHTLIST_URL = WATER_INDEX_URL
            + "company/findCopyrightList";

    /**
     * 查询水费欠费详情
     */
    public static final String WATER_PAYDETAILS_URL = WATER_INDEX_URL
            + "water/findPaymentDetail";

    /**
     * 水费历史缴费账号列表
     */
    public static final String WATER_PAYHISACCOUNT_URL = WATER_INDEX_URL
            + "payhistory/hisPaymentList";

    /**
     * 创建支付订单
     */
    public static final String ORDER_CREATE_PAYMENT_URL = WATER_INDEX_URL
            + "comeservice/order/createPaymentOrder";

    /**
     * 获取tn号
     */
    public static final String ORDER_GET_TNCODE_URL = WATER_INDEX_URL
            + "unionpay/getTnCode";

    /**
     * 获取支付宝订单信息
     */
    public static final String ORDER_GET_ALIINFO_URL = WATER_INDEX_URL
            + "alipay/getAliPayInfo";

    /**
     * 获取订单结果
     */
    public static final String ORDER_GET_RESULT_URL = WATER_INDEX_URL
            + "comeservice/order/findOrderResult";

    /**
     * 获取历史订单记录
     */
    public static final String ORDER_HIS_LIST_URL = WATER_INDEX_URL
            + "comeservice/order/payallorderlist";

    /******************************* Water END ******************************/

    /******************************* 上门服务 START ******************************/
    /**
     * 商城地址 &companyCode=0061 1是安卓
     */
    public static final String HOME_SERVER_SHOP_URL = HOME_SERVER_INDEX_URL
            + "shopping/index?client=1";

    /**
     * 主页顶部轮播图
     */
    public static final String HOME_SERVER_BANNER_URL = HOME_SERVER_INDEX_URL
            + "comeservice/banner/getBannerList";

    /**
     * 上门服务地区(11.11更新新接口)
     */
    public static final String HOME_SERVER_AERO_URL = HOME_SERVER_INDEX_URL
            + "comeservice/address/getCopyrightListnew";

    /**
     * 查询服务业务(11.11更新新接口)
     */
    public static final String HOME_SERVER_BUSSINESS_URL = HOME_SERVER_INDEX_URL
            + "comeservice/bussiness/findnew";

    /**
     * 新增查询楼栋号 2015-11-02
     */
    public static String QUERY_CONTRACT_BANCODE_URL = HOME_SERVER_INDEX_URL
            + "comeservice/address/getBuildinglist";
    /**
     * WyuMer 我的订单列表
     */
    public static final String HOME_SERVER_ORDER_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/orderList";
    /**
     * WyuMer 我的订单详情
     */
    public static final String HOME_SERVER_ORDER_Details_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/orderInfo";
    /**
     * WyuMer 历史地址
     */
    public static final String HOME_SERVER_HISTORY_ADDRESS_URL = HOME_SERVER_INDEX_URL
            + "comeservice/address/getHisaddressList";
    /**
     * WyuMer服务介绍
     */
    public static final String HOME_SERVER_INTRODUCE_URL = HOME_SERVER_INDEX_URL
            + "comeservice/server/introduce";

    /**
     * WyuMer录音上传
     */

    // public static final String
    // HOME_SERVER_VOICE_URL="http://10.4.44.195:8080/utilities-customer/common/resource/uploadresource";
    // public static final String
    // HOME_SERVER_VOICE_URL=HOME_SERVER_INDEX_URL+"common/uploadresource/upload";

    // public static final String
    // HOME_SERVER_VOICE_URL="http://10.4.44.195:8080/utilities-customer/common/resource/uploadresource";
    // public static final String
    // HOME_SERVER_VOICE_URL=HOME_SERVER_INDEX_URL+"common/uploadresource/upload";

    // public static final String
    // HOME_SERVER_VOICE_URL="http://10.37.144.19:8080/common/uploadresource/upload";
    // http://10.37.144.19:8080/
    public static final String HOME_SERVER_VOICE_URL = HOME_SERVER_INDEX_URL
            + "common/resource/uploadresource";

    /**
     * WyuMer弹出时间
     */
    // public static final String
    // HOME_SERVER_SHOW_TIME_URL="http://10.4.45.144:8080/utilities-customer/homeserver/time/getTime?debug=true";
    public static final String HOME_SERVER_SHOW_TIME_URL = HOME_SERVER_INDEX_URL
            + "comeservice/worktime/getTime";
    /**
     * WyuMer提交订单
     */
    public static final String HOME_SERVER_ORDER_UP_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/createServiceOrder";

    /*
     * WyuMer查询订单列表
     */
    public static final String HOME_SERVER_ORDER_LIST_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/ServiceOrderList";
    /**
     * 获取师傅列表 http://10.37.144.19:8099/dispatch/api/home/master/list.do
     */
    public static final String HOME_SERVER_MASTER_LIST_URL_NEW = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/list.do";
    /**
     * 核算价格
     */
    public static final String HOME_SERVER_CHARG_LIST_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/priceTotal";
    /**
     * 等待付款
     */
    public static final String HOME_SERVER_WAIT_PAY_LIST_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/payorderInfo";
    /**
     * WyuMer查询订单详情
     */
    public static final String HOME_SERVER_ORDER_DETAILS_URL = HOME_SERVER_INDEX_URL
            + "comeservice/order/orderInfo";

    /**
     * WyuMer取消订单
     */
    public static final String HOME_SERVER_FAILS_ORDER_DETAILS_URL = HOME_SERVER_INDEX_URL
            + "homeserver/order/cancleOrder";
    /**
     * 取消订单
     */
    public static final String HOME_SERVER_ORDER_CANCLE_URL = HOME_SERVER_INDEX_URL
            + "comeservice/orderattached/cancelorder";
    /**
     * d得到工作站坐标
     */
    public static final String HOME_GET_POSITION_CANCLE_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/getStationStationpositionList.json";

    /**
     * 设备列表
     */
    public static final String HOME_SERVER_EQUIPMENT_LIST_URL = "comeservice/service/find";

    /**
     * 历史联系人
     */
    public static final String HOME_SERVER_HISTORY_CONTACT_URL = "comeservice/address/getHisContants";
    /**
     * 师傅详情
     */
    public static final String HOME_SERVER_MASTER_DETAIL_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/info.json";
    /**
     * 师傅列表
     */
    public static final String HOME_SERVER_MASTER_LIST_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/list.do";
    /**
     * 师傅列表
     */
    public static final String HOME_SERVER_MASTER_LIST_URL_2 = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/listByStationId.do";
    /**
     * 施工队列表
     */
    public static final String HOME_SERVER_CONSTRUCTION_LIST_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/constructionList.json";
    /**
     * 施工队详情
     */
    public static final String HOME_SERVER_CONSTRUCTION_DETAIL_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/constructionInfo.do";
    /**
     * 施工队排班表
     */
    public static final String HOME_SERVER_CONSTRUCTION_TABLE_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/conStructionSchedule.do";
    /**
     * 师傅排班表
     */
    public static final String HOME_SERVER_SCHEDULING_TABLE_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/schedule.json";
    /**
     * 评价列表
     */
    public static final String HOME_SERVER_EVALUATION_LIST_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/commentList.json";
    /**
     * 施工队评价列表
     */
    public static final String HOME_SERVER_CONSTRUCTION_EVALUATION_LIST_URL = HOME_SERVER_INDEX_M_URL
            + "dispatch/api/home/master/conCommentList.json";
    /**
     * 自主排障问题列表
     */
    public static final String HOME_SERVER_REPAIR_TROUBLE_LIST_URL = "comeservice/repair/troubleList";
    /**
     * 自主排障问题详情
     */
    public static final String HOME_SERVER_REPAIR_TROUBLE_INFO_URL = "comeservice/repair/troubleInfo";
    /**
     * 自主排障类型
     */
    public static final String HOME_SERVER_REPAIR_ALLSERVICE_LIST_URL = "comeservice/service/allServiceList";
    /**
     * 判断燃气卡类型
     */
    public static final String HOME_SERVER_WHETHER_GAS_URL = HOME_SERVER_INDEX_URL
            + "comeservice/open/isBlueCard";

    /**
     * 常见故障
     */
    public static final String HOME_COMMON_MALFUNCTION_URL = "comeservice/repair/faults";

    /**
     * 开通条件
     */
    public static final String HOME_OPEN_CONDITION_URL = "comeservice/open/opencondition";
    /**
     * 订单评价
     */
    public static final String HOME_SERVER_ORDER_VALUATION_URL = HOME_SERVER_INDEX_URL
            + "comeservice/orderattached/addevaluation";

    /******************************* 上门服务 END ******************************/

    /******************************* 暖气费 START ******************************/
    /**
     * 查询欠费详情
     */
    public static final String HOT_FIND_PAYMENT_URL = HOME_SERVER_INDEX_URL
            + "heat/findPaymentDetail";

    /**
     * 创建缴费订单
     */
    public static final String HOT_CREATE_PAYMENT_URL = HOME_SERVER_INDEX_URL
            + "heat/createPaymentOrder";
    /**
     * 缴费历史账号
     */
    public static final String HOT_HIS_PAYMENT_URL = HOME_SERVER_INDEX_URL
            + "payhistory/hisPaymentList";

    /******************************* 暖气费 END ******************************/

    /******************************* 一些常量信息 ******************************/
    /**
     * 请求成功
     */
    public static String REQUEST_SUCCESS = "S";

    /**
     * 请求失败
     */
    public static String REQUEST_FAILED = "E";

    /**
     * 数据库路径
     */
    public static String DB_PATH = "/data/data/" + "com.enn.blueapp"
            + "/databases/";
}
